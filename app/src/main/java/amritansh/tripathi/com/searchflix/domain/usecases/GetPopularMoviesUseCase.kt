package amritansh.tripathi.com.searchflix.domain.usecases

import amritansh.tripathi.com.searchflix.model.Repository
import amritansh.tripathi.com.searchflix.network.MovieResult
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class GetPopularMoviesUseCase @Inject constructor(private val repository: Repository) {

    fun getPopularMovieUseCase(page: Int, pageSize: Int): Observable<MovieResult> =
            repository.getPopularMovies(page, pageSize)

}