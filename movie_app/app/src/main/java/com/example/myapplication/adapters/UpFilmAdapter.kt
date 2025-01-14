package com.example.myapplication.adapters

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.model.Film

class UpFilmAdapter(
    private val context: Context,
    private var upFilmList: ArrayList<Film>
) : RecyclerView.Adapter<UpFilmAdapter.UpFilmViewHolder>() {

    class UpFilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.poster)
        val title: TextView = itemView.findViewById(R.id.titleTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UpFilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_film, parent, false)
        return UpFilmViewHolder(view)
    }

    override fun getItemCount(): Int {
        return upFilmList.size
    }

    override fun onBindViewHolder(holder: UpFilmViewHolder, position: Int) {
        val film = upFilmList[position]

        Log.d("UpFilmAdapter", "Binding film at position $position: ${film.title}")

        // Hiển thị tiêu đề
        holder.title.text = film.title

        // Hiển thị ảnh poster (dùng Glide để tải ảnh từ URL)
        Glide.with(context)
            .load(film.poster) // URL của ảnh
            .placeholder(R.drawable.image_loading) // Ảnh hiển thị trong lúc tải
            .error(R.drawable.image_loading) // Ảnh hiển thị nếu tải thất bại
            .into(holder.poster) // Đặt ảnh vào ImageView
    }

    fun setData(upFilmList: ArrayList<Film>) {
        Log.d("UpFilmAdapter", "New film list: ${upFilmList.size} items") // Log số lượng item
        Log.d("UpFilmAdapter", "Film list content: $upFilmList") // Log nội dung chi tiết
        this.upFilmList = upFilmList
        notifyDataSetChanged()
    }
}