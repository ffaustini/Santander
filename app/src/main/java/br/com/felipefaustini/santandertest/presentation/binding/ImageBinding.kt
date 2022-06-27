package br.com.felipefaustini.santandertest.presentation.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import br.com.felipefaustini.domain.models.Pokemon
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions

object ImageBinding {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, url: String?) {
        if (url.isNullOrEmpty().not()) {
            Glide.with(imageView)
                .load(url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun setImageUrl(imageView: ImageView, pokemonLiveData: LiveData<Pokemon>) {
        if (pokemonLiveData.value != null) {
            Glide.with(imageView)
                .load(pokemonLiveData.value!!.url)
                .centerCrop()
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)
        }
    }

}