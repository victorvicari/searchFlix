package amritansh.tripathi.com.searchflix.presentation.movieDetails.adapterDelegate

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.ItemHorizontalRvBinding
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.network.SimilarMoviesResult
import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegate
import io.reactivex.subjects.PublishSubject

/**
 * Created by amritanshtripathi on 3/9/18.
 */
class HorizontalScrollAdapterDelegate : AdapterDelegate<Any>() {

    private lateinit var context: Context
    var horizontalScrollAdapter: HorizontalScrollAdapter = HorizontalScrollAdapter(emptyList())

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemHorizontalRvBinding>(inflater, R.layout.item_horizontal_rv, parent, false)
        context = parent.context
        return ViewHolder(binding)
    }

    override fun isForViewType(items: Any, position: Int): Boolean {
        return (items as List<*>)[position] is SimilarMoviesResult
    }

    override fun onBindViewHolder(items: Any, position: Int, holder: RecyclerView.ViewHolder, payloads: MutableList<Any>) {
        when {
            holder !is ViewHolder -> Throwable("Incompatible holder at position $position")
            (items as List<*>)[position] !is SimilarMoviesResult -> Throwable("Incompatible item at position $position")
            (items[position] as SimilarMoviesResult).result.size == 0 -> {
                holder.binding.similarmovies.visibility = View.INVISIBLE
            }
            else -> {
                horizontalScrollAdapter.setData((items[position] as SimilarMoviesResult).result)
                holder.binding.rvHorizontal.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                holder.binding.rvHorizontal.adapter = horizontalScrollAdapter

            }
        }
    }

    private inner class ViewHolder(val binding: ItemHorizontalRvBinding) : RecyclerView.ViewHolder(binding.root)

    fun getClickObservable():PublishSubject<Movie>{
        return horizontalScrollAdapter.clickObservable
    }
}