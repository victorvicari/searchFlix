package amritansh.tripathi.com.searchflix.utils

import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AppCompatActivity

/**
 * Created by amritanshtripathi on 3/5/18.
 */

fun addFragmentToActivity(fragmentManager: FragmentManager,
                          fragment: Fragment, frameId: Int) {
    checkNotNull(fragmentManager)
    checkNotNull(fragment)
    val transaction = fragmentManager.beginTransaction()
    transaction.add(frameId, fragment)
    transaction.commit()
}

fun AppCompatActivity.replaceFragment(@IdRes where: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
            .replace(where, fragment, tag)
            .commit()
}