package amritansh.tripathi.com.searchflix.DI

import amritansh.tripathi.com.searchflix.presentation.MainActivity
import amritansh.tripathi.com.searchflix.presentation.movieList.MovieListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by amritanshtripathi on 3/5/18.
 */
@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = arrayOf(FragmentBuilder::class))
    abstract fun bindMainActivity(): MainActivity
}