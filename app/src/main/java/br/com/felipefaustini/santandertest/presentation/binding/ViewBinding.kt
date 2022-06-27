package br.com.felipefaustini.santandertest.presentation.binding

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import br.com.felipefaustini.santandertest.utils.extensions.showOrGoneInCondition
import br.com.felipefaustini.santandertest.utils.extensions.showOrInvisibleInCondition

object ViewBinding {

    @JvmStatic
    @BindingAdapter("app:visibility")
    fun changeVisibility(view: View, condition: LiveData<Boolean>) {
        if (condition.value != null) {
            view.showOrGoneInCondition(condition.value!!)
        }
    }

    @JvmStatic
    @BindingAdapter("app:visibility")
    fun changeVisibility(view: View, condition: Boolean?) {
        if (condition != null) {
            view.showOrGoneInCondition(condition)
        }
    }

    @JvmStatic
    @BindingAdapter("app:invisibility")
    fun changeInvisiblity(view: View, condition: LiveData<Boolean>) {
        if (condition.value != null) {
            view.showOrInvisibleInCondition(!condition.value!!)
        }
    }

}