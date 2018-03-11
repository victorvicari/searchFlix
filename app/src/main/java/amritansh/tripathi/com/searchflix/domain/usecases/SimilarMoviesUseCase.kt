package amritansh.tripathi.com.searchflix.domain.usecases

import amritansh.tripathi.com.searchflix.model.Repository
import amritansh.tripathi.com.searchflix.network.SimilarMoviesResult
import amritansh.tripathi.com.searchflix.utils.SchedulerProvider
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/9/18.
 */
class SimilarMoviesUseCase @Inject constructor(private val repository: Repository, private val schedulerProvider: SchedulerProvider)  {
     fun getSimilarMovies(id:String) : Observable<SimilarMoviesResult>{
         return repository.getSimilarMovies(id).compose(schedulerProvider.getSchedulersForSingle()).toObservable()
     }
}