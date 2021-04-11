package com.ragvax.dictionary.utils

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

inline fun <reified T> Flow<T>.collectWhileStarted(
    lifecycleOwner: LifecycleOwner,
    noinline action: suspend (T) -> Unit
) {
    object : DefaultLifecycleObserver {
        private var job: Job? = null

        init {
            lifecycleOwner.lifecycle.addObserver(this)
        }

        override fun onStart(owner: LifecycleOwner) {
            job = owner.lifecycleScope.launch {
                collect { action(it) }
            }
        }

        override fun onStop(owner: LifecycleOwner) {
            job?.cancel()
            job = null
        }
    }
}