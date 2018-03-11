package amritansh.tripathi.com.searchflix.presentation.movieDetails

import amritansh.tripathi.com.searchflix.domain.usecases.SimilarMoviesUseCase
import amritansh.tripathi.com.searchflix.network.Item
import amritansh.tripathi.com.searchflix.network.Movie
import android.arch.lifecycle.ViewModel
import io.reactivex.Observable
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/9/18.
 */

class MovieDetailsViewModel @Inject constructor(val movieDetailsUseCase: SimilarMoviesUseCase) : ViewModel() {

    lateinit var movie: Movie

    fun getMovieDetailsData(): Observable<List<Item>> {
        var items = mutableListOf<Item>()
        items.add(movie)
        return movieDetailsUseCase.getSimilarMovies(movie.id.toString()).map { it -> items.plus(it) }
    }


}
