package amritansh.tripathi.com.searchflix.domain.interactors

import amritansh.tripathi.com.searchflix.domain.usecases.SearchMovieUseCase
import amritansh.tripathi.com.searchflix.model.Repository
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.utils.SchedulerProvider
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class SearchMovieInteractor @Inject constructor(private val repository: Repository, private val schedulerProvider: SchedulerProvider) : SearchMovieUseCase{
    override fun searchMovieUseCase(search: String, page: Int): Single<List<Movie>> {
        return repository.search(search,page).compose(schedulerProvider.getSchedulersForSingle())
    }
}