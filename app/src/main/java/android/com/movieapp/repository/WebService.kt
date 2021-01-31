package android.com.movieapp.repository

import android.com.movieapp.application.AppConstants
import android.com.movieapp.data.model.MovieList
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//es el encargado de usar retrofit para traer la informacion q esta en el servidor con el metodo @GET
interface WebService {

    @GET("movie/upcoming")
    //la query debe estar igual de escrito que en la url.
    suspend fun getUpComingMovies(@Query("api_key") apiKey: String): MovieList
    @GET("movie/top_rated")
    suspend fun getToRatedMovies(@Query("api_key") apiKey: String): MovieList
    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("api_key") apiKey: String): MovieList

}

object RetroFitClient  {
//el webservise.lazy sd inicializa cuando solo llamo a webservis en el momento de ejecucion
    val webservise by lazy {
        Retrofit.Builder()
                .baseUrl(AppConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                .build().create(WebService::class.java)

    }

}