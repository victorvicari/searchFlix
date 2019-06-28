package amritansh.tripathi.com.searchflix.presentation.movieList.paging_lib

import amritansh.tripathi.com.searchflix.domain.usecases.GetPopularMoviesUseCase
import amritansh.tripathi.com.searchflix.network.Movie
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.DataSource
import io.reactivex.disposables.CompositeDisposable

class MovieDataSourceFactory(
        private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
        private val compositeDisposable: CompositeDisposable
) : DataSource.Factory<Int, Movie>() {

    val moviesLiveData = MutableLiveData<MoviesDataSource>()

    override fun create(): DataSource<Int, Movie> {
        val moviesDataSource = MoviesDataSource(getPopularMoviesUseCase, compositeDisposable)
        moviesLiveData.postValue(moviesDataSource)
        return moviesDataSource
    }
}