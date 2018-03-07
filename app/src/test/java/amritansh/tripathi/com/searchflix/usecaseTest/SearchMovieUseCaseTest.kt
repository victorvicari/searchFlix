package amritansh.tripathi.com.searchflix.usecaseTest

import amritansh.tripathi.com.searchflix.domain.interactors.SearchMovieInteractor
import amritansh.tripathi.com.searchflix.domain.usecases.SearchMovieUseCase
import amritansh.tripathi.com.searchflix.model.Repository
import amritansh.tripathi.com.searchflix.network.Movie
import amritansh.tripathi.com.searchflix.utils.SchedulerProvider
import android.arch.core.executor.testing.InstantTaskExecutorRule
import io.reactivex.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/5/18.
 */

class SearchMovieUseCaseTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()



    @Test
    fun returnSearchResultsForQuery(){

//        val repository = Mockito.mock(Repository::class.java)
//        val schedulerProvider = Mockito.mock(SchedulerProvider::class.java)
//        val searchMovieInteractor = Mockito.mock(SearchMovieInteractor::class.java)
//
//        whenever(repository.search("Mars")).thenReturn(Single.just(emptyList<Movie>()))
//        var list:List<Movie>
//        var result:Int=-1
//
////        Assert.assertNotNull(searchMovieInteractor)
//        searchMovieInteractor.searchMovie("Mars").test()
    }


}