package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.ItemMovieListBinding
import amritansh.tripathi.com.searchflix.network.Movie
import android.app.Activity
import android.content.Context
import android.database.Observable
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject


/**
 * Created by amritanshtripathi on 3/6/18.
 */

class MovieListAdapter(private var dataset: List<Movie>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    private val clickObservable = PublishSubject.create<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieListBinding>(inflater, R.layout.item_movie_list, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }


    fun setData(dataset: List<Movie>) {
        this.dataset = dataset
        notifyDataSetChanged()
    }


    inner class ViewHolder(binding: ItemMovieListBinding) : RecyclerView.ViewHolder(binding.root) {
        private var binding: ItemMovieListBinding

        init {
            this.binding = binding
        }

        fun bind(movie: Movie) {
            binding.data = movie
            binding.root.setOnClickListener({ clickObservable.onNext(movie) })
        }
    }

    fun getClickObservable(): PublishSubject<Movie> {
        return clickObservable
    }

}