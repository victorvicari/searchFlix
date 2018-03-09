package amritansh.tripathi.com.searchflix.DI

import amritansh.tripathi.com.searchflix.presentation.movieDetails.MovieDetailsViewModel
import amritansh.tripathi.com.searchflix.presentation.movieList.MovieListViewModel
import amritansh.tripathi.com.searchflix.utils.ViewModelFactory
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


/**
 * Created by amritanshtripathi on 3/6/18.
 */
@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel::class)
    internal abstract fun movieListViewModel(movieListViewModel: MovieListViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class)
    internal abstract fun movieDetailViewModel(movieListViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    //Others ViewModels
}