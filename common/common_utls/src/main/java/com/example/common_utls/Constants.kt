package com.example.common_utls

import androidx.compose.ui.graphics.Color

object Constants {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    //for test purpose only, it's unsafe to use any of the values here in production on code
    const val TOKEN =
        "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI5M2NjYjEzN2I2ZGZkOWY2NTUyMmY2MzhjYzEwN2ZlNCIsIm5iZiI6MTc3MDM3NjMzOC43MjUsInN1YiI6IjY5ODVjYzkyNDczOTk4NDU0YTI4OWNiYiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.FRXj0ow8f9RPPHrdyjfDTRwF9JzBK_wgTbq20y6kMHA"
    const val POSTER_PATH = "https://image.tmdb.org/t/p/w342"
    const val ORIGINAL_SIZE_PATH = "https://image.tmdb.org/t/p/original"
    const val COMPS_PATH = "https://image.tmdb.org/t/p/w154"
}

val Assent = Color(0xFFFFC700)