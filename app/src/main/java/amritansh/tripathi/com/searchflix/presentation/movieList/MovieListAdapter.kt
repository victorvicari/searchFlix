package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.network.Movie
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide


/**
 * Created by amritanshtripathi on 3/6/18.
 */

class MovieListAdapter(private var dataset:List<Movie>) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = dataset.get(position).title
//        holder.overview.text = dataset.get(position).description
        holder.averageVote.text = dataset.get(position).avgVote.toString()
        Glide.with(holder.view.context).load("https://image.tmdb.org/t/p/w500/"+dataset.get(position).posterPath).into(holder.movie);
    }

    fun setData(dataset:List<Movie>){
        this.dataset=dataset
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        internal var title: TextView
//        internal var overview: TextView
        internal var averageVote: TextView
        internal var movie: ImageView
        internal var view : View

        init {
            this.view=view
            title = view.findViewById(R.id.title) as TextView
//            overview = view.findViewById(R.id.overView) as TextView
            averageVote = view.findViewById(R.id.releaseDate) as TextView
            movie = view.findViewById(R.id.movie) as ImageView

        }
    }

}