package amritansh.tripathi.com.searchflix.presentation.movieDetails.adapterDelegate

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.ItemMovieDetailBinding
import amritansh.tripathi.com.searchflix.network.Item
import amritansh.tripathi.com.searchflix.network.Movie
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

/**
 * Created by amritanshtripathi on 3/8/18.
 */
class MovieInformationAdapterDelegate : AdapterDelegate<Any>() {


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieDetailBinding>(inflater, R.layout.item_movie_detail, parent, false)
        return ViewHolder(binding)
    }

    override fun isForViewType(items: Any, position: Int): Boolean {
        return (items  as List<*>)[position] is Movie
    }

    override fun onBindViewHolder(items: Any, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        when {
            holder !is ViewHolder -> Throwable("Incompatible holder at position $position")
            (items  as List<*>)[position] !is Movie -> Throwable("Incompatible item at position $position")
            else -> holder.bind(items[position] as Movie)
        }

    }

    private inner class ViewHolder(binding: ItemMovieDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemMovieDetailBinding = binding

        fun bind(movie: Movie) {
            binding.data = movie
        }

    }

}