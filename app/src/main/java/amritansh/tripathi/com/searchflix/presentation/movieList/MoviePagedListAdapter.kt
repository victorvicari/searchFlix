package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.databinding.ItemMovieListBinding
import amritansh.tripathi.com.searchflix.databinding.ItemMovieListFooterBinding
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.presentation.models.UiState
import amritansh.tripathi.com.searchflix.presentation.movieList.view.FooterViewHolder
import amritansh.tripathi.com.searchflix.presentation.movieList.view.MoviesViewHolder
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject

class MoviePagedListAdapter(
        private val retry: () -> Unit
) : PagedListAdapter<Movie, RecyclerView.ViewHolder>(MoviesDiffUtilCallback),
        FooterViewHolder.Listener, MoviesViewHolder.Listener {

    private val DATA_TYPE = 1
    private val FOOTER_TYPE = 2

    private var state = UiState.LOADING
    private val clickObservable = PublishSubject.create<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        return if (viewType == DATA_TYPE) {
            val bind = ItemMovieListBinding.inflate(inflater, parent, false)
            MoviesViewHolder(bind, this)
        } else {
            val bind = ItemMovieListFooterBinding.inflate(inflater, parent, false)
            FooterViewHolder(bind, this)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == DATA_TYPE) {
            (holder as MoviesViewHolder).bindView(getItem(position))
        } else {
            (holder as FooterViewHolder).bindView(state)
        }

    }

    override fun getItemViewType(position: Int): Int =
            if (position < super.getItemCount()) DATA_TYPE else FOOTER_TYPE


    override fun getItemCount(): Int =
            super.getItemCount() + if (hasFooter()) 1 else 0

    private fun hasFooter(): Boolean =
            super.getItemCount() != 0 && (state == UiState.LOADING || state == UiState.ERROR)


    fun setState(uiState: UiState) {
        state = uiState
        notifyDataSetChanged()
    }

    fun getClickObservable(): PublishSubject<Movie> = clickObservable

    override fun loadRetry() {
        retry()
    }

    override fun clickObservable(movie: Movie) {
        clickObservable.onNext(movie)
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

}