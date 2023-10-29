package vn.bn.teams.appdemo.core.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline callback: (T) -> Unit) {
    observe(owner, Observer { callback(it) })
}