package com.example.myapplication.adapters


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R

class ImageAdapter(
    private val imageList: ArrayList<Int>,
    private val viewPager2: ViewPager2,  // Thêm ViewPager2
) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    // Inner class để đại diện cho mỗi item trong RecyclerView
    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageSlide)
    }

    // Tạo ra một ViewHolder mới cho mỗi item trong RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.slide_item_container, parent, false)
        return ImageViewHolder(view)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.imageView.setImageResource(imageList[position])
        if (position == imageList.size -1) {
            viewPager2.post(runnable)
        }
    }

    // Trả về số lượng item trong slider
    override fun getItemCount(): Int {
        return imageList.size
    }

    private val runnable = Runnable {
        imageList.addAll(imageList)
        notifyDataSetChanged()
    }
}

