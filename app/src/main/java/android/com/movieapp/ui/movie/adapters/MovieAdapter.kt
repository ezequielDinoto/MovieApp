package android.com.movieapp.ui.movie.adapters

import android.com.movieapp.databinding.MovieItemBinding
import android.com.movieapp.core.BaseViewHolder
import android.com.movieapp.data.model.Movie
//import android.com.movieapp.ui.model.MovieList
import android.content.Context
import android.view.LayoutInflater
//import android.com.movieapp.ui.model.Movie
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movieList: List<Movie>, private val  itemClickListener: OnMovieClickListener
    ):RecyclerView.Adapter<BaseViewHolder<*>>() {
    //esta interfaz se usa para mostrar una pelicula y esta devuelve un override con esta pelicula.
    interface OnMovieClickListener {
        fun onMovieClick(movie: Movie)
    }
    //retorna las imagenes de la pelicula
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        //itemBinding = MovieItemBinding va directo a movie_item.xml
       val itemBinding = MovieItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = MoviesViewHolder(itemBinding, parent.context)
//it es el predicado
        itemBinding.root.setOnClickListener {
            val position = holder.bindingAdapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            itemClickListener.onMovieClick(movieList[position])
        }
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MoviesViewHolder -> holder.bind(movieList[position])
        }
    }

    override fun getItemCount(): Int = movieList.size

    private inner class MoviesViewHolder(
        val binding: MovieItemBinding,
        val context: Context
    ): BaseViewHolder<Movie>(binding.root) {
        //item:Movie posee toda la pelicula y su poster al q buscamos
        override fun bind(item: Movie) {
            Glide.with(context).load("https://image.tmdb.org/t/p/w500/${item.poster_path}").centerCrop().into(binding.imgMovie)


        }
    }
}