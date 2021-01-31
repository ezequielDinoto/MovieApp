package android.com.movieapp.repository

import android.com.movieapp.data.local.LocalMovieDataSource
import android.com.movieapp.data.remote.RemoteMovieDataSource
import android.com.movieapp.data.model.MovieList
import android.com.movieapp.data.model.toMovieEntity

//implento la interfaz MovieRepository. lugar de implem de interfaces. Estos tres metodos son para traer la info para llevar al DataSou

class MovieRepositoryImpl(private val dataSourceRemote: RemoteMovieDataSource,
                          private val dataSourceLocal: LocalMovieDataSource
): MovieRepository {


    override suspend fun getUpComingMovies(): MovieList {

        dataSourceRemote.getUpComingMovie().results.forEach{ movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("upcoming"))
        }
        return dataSourceLocal.getUpComingMovie()
    }

    override suspend fun getToRatedMovies(): MovieList {
        dataSourceRemote.getTopRatedMovies().results.forEach{ movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("toprated"))
        }
        return dataSourceLocal.getTopRatedMovies()
    }

    override suspend fun getPopularMovies(): MovieList {
        dataSourceRemote.getPopularMovies().results.forEach{ movie ->
            dataSourceLocal.saveMovie(movie.toMovieEntity("popular"))
        }
        return dataSourceLocal.getPopularMovies()
    }




}