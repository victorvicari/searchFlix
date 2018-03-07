package amritansh.tripathi.com.searchflix.presentation.movieList

import amritansh.tripathi.com.searchflix.domain.usecases.GetPopularMoviesUseCase
import amritansh.tripathi.com.searchflix.domain.usecases.SearchMovieUseCase
import amritansh.tripathi.com.searchflix.network.Movie
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class MovieListViewModel @Inject constructor(private val popularMoviesUseCase: GetPopularMoviesUseCase, private val searchMovieUseCase: SearchMovieUseCase) : ViewModel() {

    fun getMovieList(): Observable<List<Movie>> {
        return popularMoviesUseCase.getPopularMovieUseCase()
    }

    fun searchMovie(query: String?): Observable<List<Movie>> {
        return searchMovieUseCase.searchMovie(search = query)
    }
}