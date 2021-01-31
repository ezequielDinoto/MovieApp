package android.com.movieapp.repository

import android.com.movieapp.data.model.MovieList
//Definen los tres metodos que van a ser usados en el datasource

//se pon suspend, que estan a la espera
    interface MovieRepository {
       suspend fun getUpComingMovies(): MovieList
       suspend fun getToRatedMovies(): MovieList
       suspend fun getPopularMovies(): MovieList

    //suspend fun getToRatedMovies(): MovieList
}