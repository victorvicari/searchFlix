package amritansh.tripathi.com.searchflix

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

/**
 * Created by amritanshtripathi on 3/4/18.
 */
class SearchFlixApp :Application(), HasActivityInjector{

    @Inject lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
    }

    override fun activityInjector(): AndroidInjector<Activity> {
        acti
    }

}