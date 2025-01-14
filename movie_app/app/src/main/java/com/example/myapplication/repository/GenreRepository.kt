package com.example.myapplication.repository

import android.util.Log
import com.example.myapplication.model.Genre
import com.example.myapplication.network.RetrofitBuilder

class GenreRepository {
    suspend fun getGenre(): List<Genre> {
        val response = RetrofitBuilder.api.getGenres()
        // Log sau khi nhận được response từ API
        Log.d("GENRE_CALL", "API trả về: $response")
        return response
    }
}