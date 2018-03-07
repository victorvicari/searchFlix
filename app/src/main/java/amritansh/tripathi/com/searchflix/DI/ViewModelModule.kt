package amritansh.tripathi.com.searchflix.DI

import amritansh.tripathi.com.searchflix.presentation.movieList.MovieListViewModel
import amritansh.tripathi.com.searchflix.utils.ViewModelFactory
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import dagger.multibindings.IntoMap
import dagger.Binds
import dagger.Module


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
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    //Others ViewModels
}