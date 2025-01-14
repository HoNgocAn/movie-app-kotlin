package com.example.myapplication.adapters


import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R

import com.example.myapplication.model.Genre

class GenreAdapter(
    private val context: Context,
    private var categoryList: ArrayList<Genre>
) : RecyclerView.Adapter<GenreAdapter.CategoryViewHolder>() {

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val category: TextView = itemView.findViewById(R.id.categoryName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.viewholder_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categoryList[position]

        Log.d("FilmAdapter", "Binding film at position $position: ${category.name}")

        // Hiển thị tiêu đề
        holder.category.text = category.name
    }

    fun setData (categoryList: ArrayList<Genre>){
        Log.d("CategoryAdapter", "New film list: ${categoryList.size} items") // Log số lượng item
        Log.d("CategoryAdapter", "Film list content: $categoryList") // Log nội dung chi tiết
        this.categoryList = categoryList
        notifyDataSetChanged()
    }
}
