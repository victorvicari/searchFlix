package amritansh.tripathi.com.searchflix.presentation.movieList.view

import amritansh.tripathi.com.searchflix.databinding.ItemMovieListBinding
import amritansh.tripathi.com.searchflix.network.Movie
import android.support.v7.widget.RecyclerView

class MoviesViewHolder(
        private val bind: ItemMovieListBinding,
        private val listener: Listener
) : RecyclerView.ViewHolder(bind.root) {

    interface Listener {
        fun clickObservable(movie: Movie)
    }

    fun bindView(movie: Movie?) {
        bind.data = movie
        bind.root.setOnClickListener { listener.clickObservable(movie!!) }
    }
}