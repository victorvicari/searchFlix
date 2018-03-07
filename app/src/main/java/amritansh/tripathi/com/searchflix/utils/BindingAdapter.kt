package amritansh.tripathi.com.searchflix.utils

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide


/**
 * Created by amritanshtripathi on 3/7/18.
 */
@BindingAdapter("app:imageUrl")
fun loadImage(v: ImageView, imgUrl: String) {
    Glide.with(v.getContext()).load(imgUrl).into(v)
}