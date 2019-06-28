package amritansh.tripathi.com.searchflix.presentation.movieList.paging_lib

import amritansh.tripathi.com.searchflix.domain.usecases.GetPopularMoviesUseCase
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.presentation.models.UiState
import android.arch.lifecycle.MutableLiveData
import android.arch.paging.PageKeyedDataSource
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers

class MoviesDataSource(
        private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
        private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, Movie>() {

    var state = MutableLiveData<UiState>()
    var retryComplete: Completable? = null

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        state.postValue(UiState.LOADING)
        val pageInit = 1
        compositeDisposable.add(
                getPopularMoviesUseCase.getPopularMovieUseCase(pageInit, params.requestedLoadSize)
                        .subscribe({
                            state.postValue(UiState.COMPLETE)
                            callback.onResult(it.result, null, 2)
                        }, {
                            state.postValue(UiState.ERROR)
                            setRetry(Action { loadInitial(params, callback) })
                        })
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        state.postValue(UiState.LOADING)
        compositeDisposable.add(
                getPopularMoviesUseCase.getPopularMovieUseCase(params.key, params.requestedLoadSize)
                        .subscribe({
                            state.postValue(UiState.COMPLETE)
                            callback.onResult(it.result, params.key + 1)
                        }, {
                            state.postValue(UiState.ERROR)
                            setRetry(Action { loadAfter(params, callback) })
                        })
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    private fun setRetry(action: Action) {
        retryComplete = Completable.fromAction(action)
    }

    fun retry() {
        retryComplete?.let {
            compositeDisposable.add(retryComplete!!
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe())
        }
    }


}