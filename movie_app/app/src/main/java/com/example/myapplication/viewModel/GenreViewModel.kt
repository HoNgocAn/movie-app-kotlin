package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Genre
import com.example.myapplication.repository.GenreRepository
import kotlinx.coroutines.launch

class GenreViewModel(private val genreRepository: GenreRepository) : ViewModel() {
    val genreMutableLiveData: MutableLiveData<List<Genre>> = MutableLiveData()

    fun getGenre() {
        viewModelScope.launch {
            try {
                // Gọi repository để lấy danh sách thể loại
                val response = genreRepository.getGenre()
                // Cập nhật dữ liệu mới vào LiveData
                genreMutableLiveData.value = response
            } catch (e: Exception) {
                // Xử lý lỗi nếu có, tránh crash app
                e.printStackTrace()
            }
        }
    }
}