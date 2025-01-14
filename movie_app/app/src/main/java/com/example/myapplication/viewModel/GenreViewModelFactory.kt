package com.example.myapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.repository.GenreRepository

class GenreViewModelFactory(private val genreRepository: GenreRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GenreViewModel::class.java)) {
            return GenreViewModel(genreRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}