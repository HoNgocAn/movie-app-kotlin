package com.example.myapplication.activities

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer

import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.example.myapplication.adapters.GenreAdapter
import com.example.myapplication.adapters.FilmAdapter
import com.example.myapplication.adapters.ImageAdapter
import com.example.myapplication.adapters.UpFilmAdapter
import com.example.myapplication.repository.FilmRepository
import com.example.myapplication.repository.GenreRepository
import com.example.myapplication.viewModel.FilmViewModel
import com.example.myapplication.viewModel.FilmViewModelFactory
import com.example.myapplication.viewModel.GenreViewModel
import com.example.myapplication.viewModel.GenreViewModelFactory

import kotlin.math.abs

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager2: ViewPager2
    private lateinit var slideAdapter: ImageAdapter
    private lateinit var handler: Handler
    private lateinit var imageList: ArrayList<Int>
    private lateinit var filmAdapter: FilmAdapter
    private lateinit var upFilmAdapter: UpFilmAdapter
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var recyclerViewFilm: RecyclerView
    private lateinit var progressBarFilm: ProgressBar
    private lateinit var recyclerViewCategory: RecyclerView
    private lateinit var progressBarCategory: ProgressBar
    private lateinit var recyclerViewUpFilm: RecyclerView
    private lateinit var progressBarUpFilm: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()  // Khởi tạo các thành phần UI
        setUpTransformer()
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                handler.removeCallbacks(runnable)
                handler.postDelayed(runnable, 5000)
            }
        })

        filmAdapter = FilmAdapter(this, ArrayList())
        upFilmAdapter = UpFilmAdapter(this, ArrayList())



        //FILM
        recyclerViewFilm = findViewById(R.id.view1)
        progressBarFilm = findViewById(R.id.progressbar1)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerViewFilm.layoutManager = layoutManager

        recyclerViewFilm.adapter = filmAdapter

        // Khởi tạo ViewModel và repository
        val filmRepository = FilmRepository()
        val viewModelFactory = FilmViewModelFactory(filmRepository)
        val filmViewModel = ViewModelProvider(this, viewModelFactory)[FilmViewModel::class.java]

        // Thiết lập adapter cho RecyclerView
        initFilmView()

        // Bắt đầu trạng thái loading
        recyclerViewFilm.visibility = View.VISIBLE
        recyclerViewFilm.visibility = View.GONE

        // Gọi hàm để lấy danh sách phimd
        filmViewModel.getFilm()
        filmViewModel.filmMutableLiveData.observe(this) { filmList ->
            Log.d("FilmList", "Received film list: $filmList")
            if (filmList != null && filmList.isNotEmpty()) {
                filmAdapter.setData(ArrayList(filmList))
                progressBarFilm.visibility = View.GONE
                recyclerViewFilm.visibility = View.VISIBLE
            } else {
                progressBarFilm.visibility = View.GONE
                recyclerViewFilm.visibility = View.GONE
                Toast.makeText(this, "No movie", Toast.LENGTH_SHORT).show()
            }
        }




        //CATEGORY
        recyclerViewCategory = findViewById(R.id.view2)
        progressBarCategory = findViewById(R.id.progressbar2)

//        val layoutManagerCategory = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
//        recyclerViewCategory.layoutManager = layoutManagerCategory

        filmAdapter = FilmAdapter(this, ArrayList())
        recyclerViewFilm.adapter = filmAdapter

        // Khởi tạo ViewModel và repository
        val categoryRepository = GenreRepository()
        val genreViewModelFactory = GenreViewModelFactory(categoryRepository)
        val genreViewModel = ViewModelProvider(this, genreViewModelFactory)[GenreViewModel::class.java]

        // Thiết lập adapter cho RecyclerView
        initCategoryView()

        // Bắt đầu trạng thái loading
        recyclerViewCategory.visibility = View.VISIBLE
        recyclerViewCategory.visibility = View.GONE

        genreViewModel.getGenre()
        genreViewModel.genreMutableLiveData.observe(this) { categoryList ->
            Log.d("genreList", "Received category list: $categoryList")
            if (categoryList != null && categoryList.isNotEmpty()) {
                genreAdapter.setData(ArrayList(categoryList))
                progressBarCategory.visibility = View.GONE
                recyclerViewCategory.visibility = View.VISIBLE
            } else {
                progressBarCategory.visibility = View.GONE
                recyclerViewCategory.visibility = View.GONE
                Toast.makeText(this, "No category", Toast.LENGTH_SHORT).show()
            }
        }



        //FILM
        recyclerViewUpFilm = findViewById(R.id.view3)
        progressBarUpFilm = findViewById(R.id.progressbar3)


        recyclerViewUpFilm.adapter = upFilmAdapter

        // Khởi tạo ViewModel và repository


        // Thiết lập adapter cho RecyclerView
        initUpFilmView()

        // Bắt đầu trạng thái loading
        recyclerViewUpFilm.visibility = View.VISIBLE
        recyclerViewUpFilm.visibility = View.GONE

        // Gọi hàm để lấy danh sách phimd
        filmViewModel.getUpFilm()
        filmViewModel.upFilmMutableLiveData.observe(this) { filmList ->
            Log.d("UpFilmList", "Received film list: $filmList")
            if (filmList != null && filmList.isNotEmpty()) {
                upFilmAdapter.setData(ArrayList(filmList))
                progressBarUpFilm.visibility = View.GONE
                recyclerViewUpFilm.visibility = View.VISIBLE
            } else {
                progressBarUpFilm.visibility = View.GONE
                recyclerViewUpFilm.visibility = View.GONE
                Toast.makeText(this, "No movie", Toast.LENGTH_SHORT).show()
            }
        }

    }



    private fun initFilmView() {
        // Thiết lập RecyclerView và Adapter
        filmAdapter = FilmAdapter(this, ArrayList())
        recyclerViewFilm.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = filmAdapter
        }
    }

    private fun initUpFilmView() {
        // Thiết lập RecyclerView và Adapter
        upFilmAdapter = UpFilmAdapter(this, ArrayList())
        recyclerViewUpFilm.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = upFilmAdapter
        }
    }

    private fun initCategoryView() {
        // Thiết lập RecyclerView và Adapter
        genreAdapter = GenreAdapter(this, ArrayList())
        recyclerViewCategory.apply {
            setHasFixedSize(true)
            layoutManager= LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = genreAdapter
        }
    }


    private val runnable = Runnable {
        viewPager2.currentItem += 1
    }


    private fun setUpTransformer() {
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{ page , position ->
            val r = 1 - abs(position)
              page.scaleY = 1f + r + 0.14f
        }

        viewPager2.setPageTransformer(transformer)

    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, 2000)

    }

    private fun initView() {
        // Khởi tạo ViewPager2 từ layout
        viewPager2 = findViewById(R.id.viewPager2)
        handler =Handler(Looper.myLooper()!!)
        imageList = ArrayList()

        imageList.add(R.drawable.wide)
        imageList.add(R.drawable.wide1)
        imageList.add(R.drawable.wide3)

        slideAdapter = ImageAdapter(imageList, viewPager2)

        viewPager2.adapter = slideAdapter
        viewPager2.offscreenPageLimit = 3
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    }

}

