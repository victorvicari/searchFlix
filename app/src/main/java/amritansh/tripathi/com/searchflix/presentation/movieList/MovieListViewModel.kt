package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.domain.interactors.GetPopularMoviesInteractor
import amritansh.tripathi.com.searchflix.domain.interactors.SearchMovieInteractor
import amritansh.tripathi.com.searchflix.network.Movie
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class MovieListViewModel @Inject constructor(private val popularMoviesInteractor: GetPopularMoviesInteractor, private val searchMovieInteractor: SearchMovieInteractor) : ViewModel() {

    fun getMovieList(): Observable<List<Movie>> {
        return popularMoviesInteractor.getPopularMovieUseCase()
    }

    fun searchMovie(query: String?): Observable<List<Movie>> {
        return searchMovieInteractor.searchMovie(search = query)
    }
}