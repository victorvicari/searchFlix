package amritansh.tripathi.com.searchflix.DI

/**
 * Created by amritanshtripathi on 3/6/18.
 */
import android.arch.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)