package amritansh.tripathi.com.searchflix.domain.usecases

import amritansh.tripathi.com.searchflix.network.Movie
import io.reactivex.Observable

/**
 * Created by amritanshtripathi on 3/5/18.
 */
interface GetPopularMoviesUseCase {
    fun getPopularMovieUseCase(): Observable<List<Movie>>
}