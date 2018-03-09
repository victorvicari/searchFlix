package amritansh.tripathi.com.searchflix.presentation.movieDetails

import amritansh.tripathi.com.searchflix.presentation.movieDetails.adapterDelegate.MovieInformationAdapterDelegate
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates3.AdapterDelegatesManager

/**
 * Created by amritanshtripathi on 3/8/18.
 */
class MovieDetailsAdapter(val data: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var delegatesManager = AdapterDelegatesManager<List<Any>>()

    init {
        delegatesManager.addDelegate(MovieInformationAdapterDelegate())
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
}