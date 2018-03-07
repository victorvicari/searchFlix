package amritansh.tripathi.com.searchflix.utils

import amritansh.tripathi.com.searchflix.R
import android.support.annotation.IdRes
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity

/**
 * Created by amritanshtripathi on 3/5/18.
 */


fun AppCompatActivity.replaceFragment(@IdRes where: Int, fragment: Fragment, tag: String) {
    supportFragmentManager.beginTransaction()
            .replace(where, fragment, tag)
            .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
            .commit()
}