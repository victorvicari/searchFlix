package amritansh.tripathi.com.searchflix.DI

import amritansh.tripathi.com.searchflix.presentation.movieDetails.MovieDetailsFragment
import amritansh.tripathi.com.searchflix.presentation.movieList.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by amritanshtripathi on 3/6/18.
 */

@Module
abstract class FragmentBuilder {

    @ContributesAndroidInjector
    internal abstract fun contributeMovieListFragment(): MovieListFragment

    @ContributesAndroidInjector
    internal abstract fun contributeMovieDetailFragment(): MovieDetailsFragment

}