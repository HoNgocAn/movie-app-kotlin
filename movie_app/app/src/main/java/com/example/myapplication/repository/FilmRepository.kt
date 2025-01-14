package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.model.Film
import com.example.myapplication.network.RetrofitBuilder

class FilmRepository {
    suspend fun getFilm(): List<Film> {
        val response = RetrofitBuilder.api.getFilms()
        // Log sau khi nhận được response từ API
        Log.d("API_CALL", "API trả về: $response")
        return response.data
    }
    suspend fun getUpFilm(): List<Film> {
        val response = RetrofitBuilder.api.getUpFilms()
        // Log sau khi nhận được response từ API
        Log.d("API_CALL", "API trả về: $response")
        return response.data
    }
}