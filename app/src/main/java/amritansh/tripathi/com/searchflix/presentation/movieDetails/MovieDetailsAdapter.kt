package amritansh.tripathi.com.searchflix.presentation.movieDetails

import amritansh.tripathi.com.searchflix.network.Item
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.presentation.movieDetails.adapterDelegate.HorizontalScrollAdapterDelegate
import amritansh.tripathi.com.searchflix.presentation.movieDetails.adapterDelegate.MovieInformationAdapterDelegate
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager
import io.reactivex.subjects.PublishSubject

/**
 * Created by amritanshtripathi on 3/8/18.
 */
class MovieDetailsAdapter(var data: List<Item>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var delegatesManager = AdapterDelegatesManager<Any>()
    private var horizontalScrollAdapterDelegate =HorizontalScrollAdapterDelegate()
    init {
        delegatesManager.addDelegate(MovieInformationAdapterDelegate())
        delegatesManager.addDelegate(horizontalScrollAdapterDelegate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(data, position, holder)
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(data, position)
    }

    fun setItems(data: List<Item>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getClickObservable():PublishSubject<Movie>{
        return horizontalScrollAdapterDelegate.getClickObservable()
    }
}