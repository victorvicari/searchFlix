package amritansh.tripathi.com.searchflix.model

import amritansh.tripathi.com.searchflix.network.ApiService
import amritansh.tripathi.com.searchflix.network.MovieResult
import amritansh.tripathi.com.searchflix.network.SimilarMoviesResult
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getPopularMovies(): Single<MovieResult> {
        return apiService.getPopularMovies()
    }

    fun search(search: String?): Single<MovieResult> {
        return apiService.searchMovies(search)
    }

    fun getSimilarMovies(id: String): Single<SimilarMoviesResult> {
        return apiService.getSimilarMovies(id)
    }
}