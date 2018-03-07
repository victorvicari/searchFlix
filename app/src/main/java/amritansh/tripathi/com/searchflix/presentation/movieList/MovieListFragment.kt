package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.utils.ViewModelFactory
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */

class MovieListFragment : DaggerFragment() {

     @Inject lateinit var viewModelFactory: ViewModelFactory

    private lateinit var viewModel: MovieListViewModel
    private val disposable = CompositeDisposable()
    private lateinit var  recyclerView: RecyclerView
    private lateinit var adapter: MovieListAdapter

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel=ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view =inflater.inflate(R.layout.fragment_movie_list, container, false)
        setUpRecyclerView(view)
        return view

    }

    override fun onStart() {
        super.onStart()
        disposable.add(viewModel.getMovieList()
                .subscribe(this::onSuccess,this::onError))

    }

    override fun onStop() {
        super.onStop()

        // clear all the subscription
        disposable.clear()
    }

    private fun onSuccess(movieList:List<Movie>){
            adapter.setData(movieList)
    }

    private fun onError(error: Throwable){
        Log.e(TAG, "Unable to get movieList", error)
    }

    private fun setUpRecyclerView(view:View) {
        recyclerView = view.findViewById(R.id.my_recycler_view) as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this.context)
        adapter= MovieListAdapter(ArrayList<Movie>(0))
        recyclerView.adapter =adapter
    }


    companion object {
        @JvmStatic
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }

        private val TAG = MovieListFragment::class.java.simpleName

    }


}