package amritansh.tripathi.com.searchflix.presentation.movieList.view

import amritansh.tripathi.com.searchflix.databinding.ItemMovieListFooterBinding
import amritansh.tripathi.com.searchflix.presentation.models.UiState
import android.support.v7.widget.RecyclerView
import android.view.View

class FooterViewHolder(
        private val bind: ItemMovieListFooterBinding,
        private val listener: Listener
) : RecyclerView.ViewHolder(bind.root) {

    interface Listener {
        fun loadRetry()
    }

    fun bindView(state: UiState) {
        when (state) {
            UiState.ERROR -> {
                bind.itemMovieFooterLoading.visibility = View.GONE
                bind.itemMovieFooterReloading.visibility = View.VISIBLE
            }
            UiState.LOADING -> {
                bind.itemMovieFooterLoading.visibility = View.VISIBLE
                bind.itemMovieFooterReloading.visibility = View.GONE
            }
            else -> {
                bind.root.visibility = View.GONE
            }
        }
        bind.itemMovieFooterReloading.setOnClickListener { listener.loadRetry() }
    }

}