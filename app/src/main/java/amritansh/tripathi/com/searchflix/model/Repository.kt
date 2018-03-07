package amritansh.tripathi.com.searchflix.model

import amritansh.tripathi.com.searchflix.network.ApiService
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.network.MovieResult
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getPopularMovies(page: Int = 1): Single<MovieResult> {
        return apiService.getPopularMovies()
    }

    fun search(search: String, page: Int = 1): Single<MovieResult> {
        return apiService.searchMovies(search, page = page)
    }
}