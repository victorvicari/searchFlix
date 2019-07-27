package amritansh.tripathi.com.searchflix.presentation.navigation

import amritansh.tripathi.com.searchflix.R
import amritansh.tripathi.com.searchflix.utils.replaceFragment
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentActivity
import android.support.v7.app.AppCompatActivity
import javax.inject.Inject

class Navigator @Inject constructor() {

    fun setupFirstFrag(ctx: AppCompatActivity, destination: Fragment, name: String) {
        ctx.supportFragmentManager.findFragmentById(R.id.contentFrame)
                ?: ctx.replaceFragment(R.id.contentFrame, destination, name)
    }

    fun throughFrag(ctx: FragmentActivity, destination: Fragment, name: String) {
        ctx.supportFragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .setCustomAnimations(R.anim.abc_fade_in, R.anim.abc_fade_out)
                .replace(R.id.contentFrame, destination, name)
                .commit()
    }

}