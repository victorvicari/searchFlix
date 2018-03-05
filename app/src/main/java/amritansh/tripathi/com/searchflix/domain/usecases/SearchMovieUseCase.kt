package amritansh.tripathi.com.searchflix.domain.usecases

import amritansh.tripathi.com.searchflix.network.Movie
import io.reactivex.Single

/**
 * Created by amritanshtripathi on 3/5/18.
 */
interface SearchMovieUseCase {

    fun searchMovieUseCase(search:String,page:Int=1): Single<List<Movie>>
}