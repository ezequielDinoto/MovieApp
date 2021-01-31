package android.com.movieapp.ui.movie.adapters.concat


import android.com.movieapp.databinding.TopRatedMoviesRowBinding
import android.com.movieapp.core.BaseConcatHolder
import android.com.movieapp.ui.movie.adapters.MovieAdapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class TopRatedConcatAdapter (private val movieAdapter: MovieAdapter): RecyclerView.Adapter<BaseConcatHolder<*>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseConcatHolder<*> {
        val itemView = TopRatedMoviesRowBinding.inflate(LayoutInflater.from(parent.context),parent, false)
        return ConcatViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: BaseConcatHolder<*>, position: Int) {
        when(holder) {
            is ConcatViewHolder -> holder.bind(movieAdapter)
        }

    }

    override fun getItemCount(): Int = 1//porque es un solo adapter

    private inner class ConcatViewHolder(val binding: TopRatedMoviesRowBinding): BaseConcatHolder<MovieAdapter>(binding.root) {
        override fun bind(adapter: MovieAdapter) {
            binding.rvTopRatedMovies.adapter = adapter
        }

    }
}
