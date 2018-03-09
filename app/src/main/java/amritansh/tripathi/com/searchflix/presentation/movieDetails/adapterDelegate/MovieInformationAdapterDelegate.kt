package amritansh.tripathi.com.searchflix.presentation.movieDetails.adapterDelegate

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.ItemMovieDetailBinding
import amritansh.tripathi.com.searchflix.network.Movie
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate

/**
 * Created by amritanshtripathi on 3/8/18.
 */
class MovieInformationAdapterDelegate : AdapterDelegate<List<Any>>() {


    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemMovieDetailBinding>(inflater, R.layout.item_movie_detail, parent, false)
        return ViewHolder(binding)
    }

    override fun isForViewType(items: List<Any>, position: Int): Boolean {
        return items[position] is Movie
    }

    override fun onBindViewHolder(items: List<Any>, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        if(!(holder is ViewHolder)){
            Throwable("Incompatible holder at position $position")
        }else if(!(items[position] is Movie)){
            Throwable("Incompatible item at position $position")
        }else{
            holder.bind(items[position] as Movie)
        }


    }

    inner class ViewHolder(binding: ItemMovieDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        var binding: ItemMovieDetailBinding = binding

        fun bind(movie: Movie) {
            binding.data = movie
        }

    }

}