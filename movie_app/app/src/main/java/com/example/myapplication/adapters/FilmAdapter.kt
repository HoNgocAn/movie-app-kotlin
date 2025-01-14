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

class FilmAdapter (
    private val context:Context,
    private var filmList :ArrayList<Film>
) : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val poster: ImageView = itemView.findViewById(R.id.poster)
        val title: TextView = itemView.findViewById(R.id.titleTxt)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_film, parent, false)
        return FilmViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filmList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = filmList[position]

        Log.d("FilmAdapter", "Binding film at position $position: ${film.title}")

        // Hiển thị tiêu đề
        holder.title.text = film.title

        // Hiển thị ảnh poster (dùng Glide để tải ảnh từ URL)
        Glide.with(context)
            .load(film.poster) // URL của ảnh
            .placeholder(R.drawable.image_loading) // Ảnh hiển thị trong lúc tải
            .error(R.drawable.image_loading) // Ảnh hiển thị nếu tải thất bại
            .into(holder.poster) // Đặt ảnh vào ImageView
    }
    fun setData (filmList: ArrayList<Film>){
        Log.d("FilmAdapter", "New film list: ${filmList.size} items") // Log số lượng item
        Log.d("FilmAdapter", "Film list content: $filmList") // Log nội dung chi tiết
        this.filmList = filmList
        notifyDataSetChanged()
    }
}