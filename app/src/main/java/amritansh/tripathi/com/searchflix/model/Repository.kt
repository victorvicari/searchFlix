package amritansh.tripathi.com.searchflix.model

import amritansh.tripathi.com.searchflix.network.ApiService
import amritansh.tripathi.com.searchflix.network.MovieResult
import amritansh.tripathi.com.searchflix.network.SimilarMoviesResult
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */
class Repository @Inject constructor(private val apiService: ApiService) {

    fun getPopularMovies(page: Int, pageSize: Int): Flowable<MovieResult> = apiService.getPopularMovies(page, pageSize)

    fun search(search: String?): Single<MovieResult> = apiService.searchMovies(search)

    fun getSimilarMovies(id: String): Single<SimilarMoviesResult> = apiService.getSimilarMovies(id)

}