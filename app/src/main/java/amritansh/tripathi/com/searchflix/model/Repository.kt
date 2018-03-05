package amritansh.tripathi.com.searchflix.model

import amritansh.tripathi.com.searchflix.network.ApiService
import amritansh.tripathi.com.searchflix.network.Movie
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getPopularMovies(page: Int = 1): Single<List<Movie>> {
        return apiService.getPopularMovies(page = page)
    }

    fun search(search: String, page: Int = 1): Single<List<Movie>> {
        return apiService.searcgMovies(search,page=page)
    }
}