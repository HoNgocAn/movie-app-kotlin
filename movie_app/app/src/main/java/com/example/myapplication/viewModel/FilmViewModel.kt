package com.example.myapplication.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.model.Film
import com.example.myapplication.repository.FilmRepository
import kotlinx.coroutines.launch

class FilmViewModel(private val filmRepository: FilmRepository): ViewModel() {
    val filmMutableLiveData: MutableLiveData<List<Film>> = MutableLiveData()
    val upFilmMutableLiveData: MutableLiveData<List<Film>> = MutableLiveData()

    fun getFilm() {
        viewModelScope.launch {
            try {
                // Gọi repository để lấy danh sách phim
                val response = filmRepository.getFilm()
                // Cập nhật dữ liệu mới vào LiveData
                filmMutableLiveData.value = response
            } catch (e: Exception) {
                // Xử lý lỗi nếu có, tránh crash app
                e.printStackTrace()
            }
        }
    }

    fun getUpFilm() {
        viewModelScope.launch {
            try {
                // Gọi repository để lấy danh sách phim
                val response = filmRepository.getUpFilm()
                // Cập nhật dữ liệu mới vào LiveData
                upFilmMutableLiveData.value = response
            } catch (e: Exception) {
                // Xử lý lỗi nếu có, tránh crash app
                e.printStackTrace()
            }
        }
    }

}