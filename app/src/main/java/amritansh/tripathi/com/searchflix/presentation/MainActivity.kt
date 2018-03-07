package amritansh.tripathi.com.searchflix.presentation

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.presentation.movieList.MovieListFragment
import amritansh.tripathi.com.searchflix.utils.replaceFragment
import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable


class MainActivity : DaggerAppCompatActivity() {


    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewFragment()

    }

    private fun setupViewFragment() {
        supportFragmentManager.findFragmentById(R.id.contentFrame)
                ?: MovieListFragment.newInstance().let {
                    replaceFragment(R.id.contentFrame, it, MovieListFragment::class.java.name)
                }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }


}

//    @Inject lateinit var apiService: ApiService
//    @Inject lateinit var schedulerProvider: SchedulerProvider

//        var
//        apiService.getPopularMovies().compose(schedulerProvider.getSchedulersForSingle()).subscribeBy(onSuccess = {
//            Log.d("MainActivity", it.toString())
//        }, onError = {
//            Log.d("MainActivity", it.message)
//        })
//        val userAvailable = findViewById(R.id.target) as TextView