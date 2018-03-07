package amritansh.tripathi.com.searchflix.domain.usecases

import amritansh.tripathi.com.searchflix.model.Repository
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.network.MovieResult
import amritansh.tripathi.com.searchflix.utils.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class GetPopularMoviesUseCase @Inject constructor(private val repository: Repository, private val schedulerProvider: SchedulerProvider) {
    fun getPopularMovieUseCase(): Observable<List<Movie>> {
        return repository.getPopularMovies().compose(schedulerProvider.getSchedulersForSingle()).map { it: MovieResult -> it.result }.toObservable()
    }
}