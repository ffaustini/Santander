package br.com.felipefaustini.santandertest.utils.extensions

import androidx.lifecycle.LiveData

fun <T> LiveData<T>.asValue(): T {
    return if (this.value != null) this.value!! else throw RuntimeException("Null value")
}