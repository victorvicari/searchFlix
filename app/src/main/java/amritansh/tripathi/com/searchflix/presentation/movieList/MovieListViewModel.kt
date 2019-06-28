package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.domain.usecases.GetPopularMoviesUseCase
import amritansh.tripathi.com.searchflix.domain.usecases.SearchMovieUseCase
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.presentation.models.UiState
import amritansh.tripathi.com.searchflix.presentation.movieList.paging_lib.MovieDataSourceFactory
import amritansh.tripathi.com.searchflix.presentation.movieList.paging_lib.MoviesDataSource
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class MovieListViewModel @Inject constructor(
        popularMoviesUseCase: GetPopularMoviesUseCase,
        private val searchMovieUseCase: SearchMovieUseCase
) : ViewModel() {

    private var dtSourceFactory: MovieDataSourceFactory
    private val _compositeDisposable = CompositeDisposable()
    var listPagedMovie: LiveData<PagedList<Movie>>

    init {
        dtSourceFactory = MovieDataSourceFactory(popularMoviesUseCase, _compositeDisposable)

        val config = PagedList.Config.Builder()
                .setPageSize(10)
                .setPageSize(10 * 2)
                .setEnablePlaceholders(false)
                .build()

        listPagedMovie = LivePagedListBuilder(dtSourceFactory, config).build()
    }

    fun getState(): LiveData<UiState> =
            Transformations.switchMap<MoviesDataSource, UiState>(
                    dtSourceFactory.moviesLiveData, MoviesDataSource::state)

    fun retry() {
        dtSourceFactory.moviesLiveData.value?.retry()
    }

    fun searchMovie(query: String?): Observable<List<Movie>> = searchMovieUseCase.searchMovie(search = query)

    fun listResult(): Boolean {
        return listPagedMovie.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        _compositeDisposable.dispose()
        super.onCleared()
    }
}