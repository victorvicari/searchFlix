package amritansh.tripathi.com.searchflix.presentation

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.presentation.movieList.MovieListFragment
import amritansh.tripathi.com.searchflix.presentation.navigation.Navigator
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.SearchView
import dagger.android.support.DaggerAppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

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

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out)
    }

    private fun handleIntent(intent: Intent) {
        if (Intent.ACTION_SEARCH == intent.action) {
            val query = intent.getStringExtra(SearchManager.QUERY)
            navigator.setupFirstFrag(this,
                    MovieListFragment.newInstance(query),
                    MovieListFragment::class.java.name)
        }
    }

    private fun setupViewFragment() {
        navigator.setupFirstFrag(this,
                MovieListFragment.newInstance(),
                MovieListFragment::class.java.name)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
        compositeDisposable.dispose()
    }

    //TODO:1)Add Recommendations in Details
    //TODO:2)Add empty states

}