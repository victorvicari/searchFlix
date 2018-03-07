package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.domain.interactors.GetPopularMoviesInteractor
import amritansh.tripathi.com.searchflix.domain.usecases.GetPopularMoviesUseCase
import amritansh.tripathi.com.searchflix.network.Movie
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class MovieListViewModel @Inject constructor(private val popularMoviesInteractor: GetPopularMoviesInteractor) :ViewModel(){

    fun getMovieList():Observable<List<Movie>>{
        return popularMoviesInteractor.getPopularMovieUseCase()
    }


}