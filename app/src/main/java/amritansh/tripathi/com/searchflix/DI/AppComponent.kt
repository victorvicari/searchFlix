package amritansh.tripathi.com.searchflix.DI

import amritansh.tripathi.com.searchflix.SearchFlixApp
import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by amritanshtripathi on 3/5/18.
 */
@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class, AppModule::class))
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: SearchFlixApp)

}