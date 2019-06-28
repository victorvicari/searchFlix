package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.FragmentCommonBinding
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.presentation.models.UiState
import amritansh.tripathi.com.searchflix.presentation.movieDetails.MovieDetailsFragment
import amritansh.tripathi.com.searchflix.presentation.navigation.Navigator
import amritansh.tripathi.com.searchflix.utils.ViewModelFactory
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
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

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    @Inject
    lateinit var navigator: Navigator

    private lateinit var adapter: MoviePagedListAdapter
    private lateinit var binding: FragmentCommonBinding
    private val disposable = CompositeDisposable()
    private var query: String? = null

    private val viewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_common, container, false)

        setUpRecyclerView()
        query = arguments?.get(QUERY_TAG) as? String
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        if (query != null) {
            disposable.addAll(viewModel.searchMovie(query)
                    .doOnSubscribe({ this.showLoading() })
                    .doOnTerminate({ this.hideLoading() })
                    .subscribe({ onSuccess() }, { onError() }),
                    adapter.getClickObservable()
                            .subscribe(this::showMovieDetailFragment))
        } else {
            disposable.addAll(adapter.getClickObservable()
                    .subscribe(this::showMovieDetailFragment))
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getState().observe(viewLifecycleOwner, Observer {
            if (viewModel.listResult() && it == UiState.LOADING) {
                showLoading()
            } else if (viewModel.listResult() && it == UiState.ERROR) {
                onError()
            } else {
                hideLoading()
            }
            if (!viewModel.listResult()) {
                adapter.setState(it ?: UiState.COMPLETE)
            }
        })
    }

    override fun onStop() {
        super.onStop()
        disposable.clear()
    }

    private fun showLoading() {
        binding.error.visibility = View.INVISIBLE
        binding.myRecyclerView.visibility = View.INVISIBLE
        binding.loading.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.error.visibility = View.INVISIBLE
        binding.loading.visibility = View.INVISIBLE
        binding.myRecyclerView.visibility = View.VISIBLE
    }

    private fun onSuccess() {
        binding.error.visibility = View.INVISIBLE
    }

    private fun onError() {
        binding.loading.visibility = View.INVISIBLE
        binding.myRecyclerView.visibility = View.INVISIBLE
        binding.error.visibility = View.VISIBLE
        binding.error.setOnClickListener { viewModel.retry() }
    }

    private fun setUpRecyclerView() {
        adapter = MoviePagedListAdapter { viewModel.retry() }
        binding.myRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.myRecyclerView.adapter = adapter

        viewModel.listPagedMovie.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })
    }

    private fun showMovieDetailFragment(movie: Movie) {
        activity?.let {
            navigator.throughFrag(it,
                    MovieDetailsFragment.newInstance(movie),
                    MovieDetailsFragment::class.java.name)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(): MovieListFragment {
            return MovieListFragment()
        }

        @JvmStatic
        fun newInstance(searchQuery: String): MovieListFragment {
            val args = Bundle()
            val fragment = MovieListFragment()
            args.putString(QUERY_TAG, searchQuery)
            fragment.arguments = args
            return fragment
        }

        private val QUERY_TAG = " QUERYTAG"
        private val TAG = MovieListFragment::class.java.simpleName

    }

}