package amritansh.tripathi.com.searchflix.presentation.movieDetails

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.utils.ViewModelFactory
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class MovieDetailsFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: MovieDetailsViewModel
    private lateinit var adapter:MovieDetailsAdapter
    private lateinit var recyclerView: RecyclerView
    private var disposable = CompositeDisposable()
    private lateinit var  movie:Movie


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MovieDetailsViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_common, container, false)
        movie = arguments?.get(MOVIE_OBJECT) as Movie
        setUpRecyclerView(view)
        return view
    }

    private fun setUpRecyclerView(view: View) {
        recyclerView = view.findViewById(R.id.my_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter = MovieDetailsAdapter(ArrayList(listOf(movie)))
        recyclerView.adapter = adapter
    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle()
            val fragment = MovieDetailsFragment()
            args.putParcelable(MOVIE_OBJECT, movie)
            fragment.arguments = args
            return fragment
        }

        private val MOVIE_OBJECT = "MOVIEOBJECT"

    }
}