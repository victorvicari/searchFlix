package amritansh.tripathi.com.searchflix.usecaseTest

import amritansh.tripathi.com.searchflix.domain.usecases.SearchMovieUseCase
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test

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
//        val searchMovieInteractor = Mockito.mock(SearchMovieUseCase::class.java)
//
//        whenever(repository.search("Mars")).thenReturn(Single.just(emptyList<Movie>()))
//        var list:List<Movie>
//        var result:Int=-1
//
////        Assert.assertNotNull(searchMovieInteractor)
//        searchMovieInteractor.searchMovie("Mars").test()
    }


}