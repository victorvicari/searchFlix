package amritansh.tripathi.com.searchflix.presentation.movieDetails

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.databinding.FragmentMovieDetailBinding
import amritansh.tripathi.com.searchflix.network.Movie
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class MovieDetailsFragment : DaggerFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = DataBindingUtil.inflate<FragmentMovieDetailBinding>(layoutInflater, R.layout.fragment_movie_detail, container, false)
        val movie = arguments?.get(MOVIE_OBJECT) as? Movie
        binding.data = movie
        return binding.root
    }

    companion object {
        fun newInstance(movie: Movie): MovieDetailsFragment {
            val args = Bundle()
            val fragment = MovieDetailsFragment()
            args.putParcelable(MOVIE_OBJECT, movie)
            fragment.arguments = args
            return fragment
        }

        private val MOVIE_OBJECT = " MOVIEOBJECT"

    }
}