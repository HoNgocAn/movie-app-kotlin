package com.example.myapplication.network

import com.example.myapplication.model.ApiResponse
import com.example.myapplication.model.Genre
import retrofit2.http.GET

interface Api {
    @GET(Url.filmsEndpoint)
    suspend fun getFilms(): ApiResponse

    @GET(Url.upFilmsEndpoint)
    suspend fun getUpFilms(): ApiResponse

    @GET(Url.genresEndpoint)
    suspend fun getGenres() : List<Genre>
}