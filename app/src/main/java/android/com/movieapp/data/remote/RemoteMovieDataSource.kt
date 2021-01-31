package android.com.movieapp.data.remote

import android.com.movieapp.application.AppConstants
import android.com.movieapp.data.model.MovieList
import android.com.movieapp.repository.WebService

//llama al servidor mediante retofit para buscar la informacion
class RemoteMovieDataSource (private val webService: WebService){

    suspend fun getUpComingMovie(): MovieList = webService.getUpComingMovies(AppConstants.API_KEY)

    suspend fun getTopRatedMovies(): MovieList = webService.getToRatedMovies(AppConstants.API_KEY)


   suspend fun getPopularMovies(): MovieList = webService.getPopularMovies(AppConstants.API_KEY)


}