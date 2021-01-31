package android.com.movieapp.data.local


import android.com.movieapp.data.model.MovieEntity
import android.com.movieapp.data.model.MovieList
import android.com.movieapp.data.model.toMovieList


class LocalMovieDataSource(private val movieDao: MovieDao) {



    suspend fun getUpComingMovie(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "upcoming" }.toMovieList()
    }
        suspend fun getTopRatedMovies(): MovieList {
            return movieDao.getAllMovies().filter { it.movie_type == "toprated" }.toMovieList()

        }

    suspend fun getPopularMovies(): MovieList {
        return movieDao.getAllMovies().filter { it.movie_type == "popular" }.toMovieList()
    }
suspend fun saveMovie(movie: MovieEntity) {
    movieDao.saveMovie(movie)
}
}