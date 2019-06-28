package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.ItemMovieListBinding
import amritansh.tripathi.com.searchflix.databinding.ItemMovieListFooterBinding
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.presentation.models.UiState
import android.arch.paging.PagedListAdapter
import android.databinding.DataBindingUtil
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject

class MoviePagedListAdapter(private val retry: () -> Unit) :
        PagedListAdapter<Movie, RecyclerView.ViewHolder>(MoviesDiffUtilCallback) {

    private val DATA_TYPE = 1
    private val FOOTER_TYPE = 2

    private var state = UiState.LOADING
    private val clickObservable = PublishSubject.create<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == DATA_TYPE) {
            val bind = DataBindingUtil.inflate<ItemMovieListBinding>(inflater, R.layout.item_movie_list,
                    parent, false)
            MoviesViewHolder(bind)
        } else {
            val bind = DataBindingUtil.inflate<ItemMovieListFooterBinding>(inflater, R.layout.item_movie_list_footer,
                    parent, false)
            FooterViewHolder(bind)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_TYPE) {
            (holder as MoviesViewHolder).bindView(getItem(position))
        } else {
            (holder as FooterViewHolder).bindView(state)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position < super.getItemCount()) {
            DATA_TYPE
        } else {
            FOOTER_TYPE
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount() + if (hasFooter()) {
            1
        } else {
            0
        }
    }

    private fun hasFooter(): Boolean {
        return super.getItemCount() != 0 && (state == UiState.LOADING || state == UiState.ERROR)
    }

    fun setState(uiState: UiState) {
        state = uiState
        notifyDataSetChanged()
    }

    fun getClickObservable(): PublishSubject<Movie> {
        return clickObservable
    }

    companion object {
        val MoviesDiffUtilCallback = object : DiffUtil.ItemCallback<Movie>() {
            override fun areItemsTheSame(old: Movie, new: Movie): Boolean {
                return old.id == new.id
            }

            override fun areContentsTheSame(old: Movie, new: Movie): Boolean {
                return old == new
            }

        }
    }

    inner class MoviesViewHolder(private val bind: ItemMovieListBinding) : RecyclerView.ViewHolder(bind.root) {
        fun bindView(movie: Movie?) {
            bind.data = movie
            bind.root.setOnClickListener { clickObservable.onNext(movie!!) }
        }
    }

    inner class FooterViewHolder(private val bind: ItemMovieListFooterBinding) :
            RecyclerView.ViewHolder(bind.root) {
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
            bind.itemMovieFooterReloading.setOnClickListener { retry() }
        }

    }

}