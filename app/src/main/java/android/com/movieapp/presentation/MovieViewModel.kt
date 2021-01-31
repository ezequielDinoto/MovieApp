package android.com.movieapp.presentation


import android.com.movieapp.core.Resource
import android.com.movieapp.repository.MovieRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import java.lang.Exception

//extendemos de Viewmodel donde se importa ligecycle su ciclo de vida. Trabajan en conjunto para tener la info del servidor
//el MovieRepository te lleva a MovieRepositoryImpl
class MovieViewModel(private val repo: MovieRepository): ViewModel() {
    //este metodo se comunica entre el repositorio y la vista. Tenemos que adjudicalrle un hilomde trasmicion
//buscar las peliculas de la pantalla principal
    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
        emit(Resource.Loading())

        try {
            emit(Resource.Success(Triple(repo.getUpComingMovies(), repo.getToRatedMovies(), repo.getPopularMovies())))

        } catch (e: Exception) {
            emit(Resource.Failure(e))

        }

    }
}
    class MovieViewModelFactory(private val repo: MovieRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            return modelClass.getConstructor(MovieRepository::class.java).newInstance(repo)
        }

    }
//por si quiero hacer mas de tres objetos distintos.
//data class NTuple4<T1,T2,T3,T4,T5>(val t1:T1, val t2:T2,val t3:T3, val t4:T4, val t5:T5)