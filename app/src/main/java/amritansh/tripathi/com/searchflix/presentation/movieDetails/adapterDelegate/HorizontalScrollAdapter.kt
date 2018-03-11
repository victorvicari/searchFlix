package amritansh.tripathi.com.searchflix.presentation.movieDetails.adapterDelegate

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.ItemSimilarMovieBinding
import amritansh.tripathi.com.searchflix.network.Movie
import android.app.Fragment
import android.app.FragmentManager
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.subjects.PublishSubject

/**
 * Created by amritanshtripathi on 3/10/18.
 */
class HorizontalScrollAdapter(private var data:List<Movie>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val clickObservable = PublishSubject.create<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSimilarMovieBinding>(inflater, R.layout.item_similar_movie, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ViewHolder).bind(data[position])
    }

    private inner class ViewHolder(binding: ItemSimilarMovieBinding): RecyclerView.ViewHolder(binding.root){
        private var binding = binding
        fun bind(data: Movie) {
            binding.movie.layout(0, 0, 0, 0)
            this.binding.data = data
            this.binding.root.setOnClickListener({clickObservable.onNext(data)})
        }
    }

    fun setData(data: List<Movie>){
        this.data=data
        notifyDataSetChanged()
    }
}