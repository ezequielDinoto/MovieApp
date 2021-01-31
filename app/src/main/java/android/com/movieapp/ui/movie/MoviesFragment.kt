package android.com.movieapp.ui.movie


import android.com.movieapp.R
import android.com.movieapp.core.Resource
import android.com.movieapp.data.local.AppDatabase
import android.com.movieapp.data.local.LocalMovieDataSource
import android.com.movieapp.data.model.Movie
import android.com.movieapp.data.remote.RemoteMovieDataSource
import android.com.movieapp.databinding.FragmentMoviesBinding
import android.com.movieapp.presentation.MovieViewModel
import android.com.movieapp.presentation.MovieViewModelFactory
import android.com.movieapp.repository.MovieRepositoryImpl
import android.com.movieapp.repository.RetroFitClient
import android.com.movieapp.ui.movie.adapters.MovieAdapter
import android.com.movieapp.ui.movie.adapters.concat.PopularConcatAdapter
import android.com.movieapp.ui.movie.adapters.concat.TopRatedConcatAdapter
import android.com.movieapp.ui.movie.adapters.concat.UpcomingConcatAdapter
//import android.com.peliculasapp.databinding.FragmentMoviesBinding
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ConcatAdapter

//el dos puntos (:) es igual a extends en java.
// el view de binding. Representa (R.layout.fragment_movies)
class MoviesFragment : Fragment(R.layout.fragment_movies), MovieAdapter.OnMovieClickListener {
    private lateinit var binding: FragmentMoviesBinding
    //by es un delegador sl q le doy un trabsjo. el Delegador se llama viewmodels
    //se llama inyeccion de dependencias.
    private val viewModel by viewModels<MovieViewModel> {
        MovieViewModelFactory(
            MovieRepositoryImpl(
                RemoteMovieDataSource(RetroFitClient.webservise),
                        LocalMovieDataSource(AppDatabase.getDatabase(requireContext()).movieDao())
            )
        )
    }
    //setap
    private lateinit var concatAdapter: ConcatAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)//onMovieClick te lleva a los detalles de la pelicula. MovieDetailFragment
    binding = FragmentMoviesBinding.bind(view)
    //para acceder a los elementos del fragment_movies uso Ej: binding.ProgressBar

    concatAdapter = ConcatAdapter()
    viewModel.fetchMainScreenMovies().observe(viewLifecycleOwner, Observer { result ->
        when(result) {
            is Resource.Loading -> {
                binding.ProgressBar.visibility = View.VISIBLE
            }
            is Resource.Success -> {
                binding.ProgressBar.visibility = View.GONE
                //este concatAdapter permite hacer pantallas muy complejos
                concatAdapter.apply {
                    addAdapter(0, UpcomingConcatAdapter(MovieAdapter(result.data.first.results, this@MoviesFragment)))
                    addAdapter(0, TopRatedConcatAdapter(MovieAdapter(result.data.second.results, this@MoviesFragment)))
                    addAdapter(0, PopularConcatAdapter(MovieAdapter(result.data.third.results, this@MoviesFragment)))


                }
                binding.rvMovies.adapter = concatAdapter

            }
            is Resource.Failure -> {
                binding.ProgressBar.visibility = View.GONE
                Log.d("Error", "${result.exception}")
            }
        }

    })
}

override fun onMovieClick(movie: Movie) {
        val action = MoviesFragmentDirections.actionMoviesFragmentToMovieDetailFragment(
            movie.poster_path,
            movie.backdrop_path,
            movie.vote_average.toFloat(),
            movie.vote_count,
            movie.overview,
            movie.original_title,
            movie.original_language,
            movie.release_date

        )
        findNavController().navigate(action)
    }
}