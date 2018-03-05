package amritansh.tripathi.com.searchflix

import amritansh.tripathi.com.searchflix.DI.DaggerAppComponent
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

    @Inject lateinit var activityDispatchingAndroidInjector:
            DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()

        DaggerAppComponent.builder()
                .application(this)
                .build()
                .inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> {
       return activityDispatchingAndroidInjector
    }

}