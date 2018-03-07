package amritansh.tripathi.com.searchflix.presentation

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.presentation.movieList.MovieListFragment
import amritansh.tripathi.com.searchflix.utils.replaceFragment
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable


class MainActivity : DaggerAppCompatActivity() {


    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewFragment()
        handleIntent(intent)

    }

    override fun onNewIntent(intent: Intent) {
        handleIntent(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = menu.findItem(R.id.search).actionView as SearchView
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(componentName))
        searchView.setIconifiedByDefault(false)
        return true
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            supportFragmentManager.findFragmentById(R.id.contentFrame)
                    ?: MovieListFragment.newInstance(query).let {
                        replaceFragment(R.id.contentFrame, it, MovieListFragment::class.java.name)
                    }
        }
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

    //TODO:1)Add Fragment Navigator
    //TODO:2)Add backstack management
    //TODO:3)Add Loading progress bar
    //TODO:4)Add pagination
    //TODO:5)Add delegate Adapter
    //TODO:6)Add Recommendations in Details
    //TODO:7)Add UserManagement
    //TODO:8)ADD Test Cases
    //TODO:9)Add User Prefrences
    //TODO:10)Add Room

}