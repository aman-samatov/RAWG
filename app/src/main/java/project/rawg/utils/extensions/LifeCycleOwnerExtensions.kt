package project.rawg.utils.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.coroutineScope
import androidx.lifecycle.whenStateAtLeast
import kotlinx.coroutines.launch

inline fun LifecycleOwner.whenStateAtLeast(
    state: Lifecycle.State,
    crossinline block: () -> Unit
) {
    if (lifecycle.currentState.isAtLeast(state)) {
        block()
    } else {
        lifecycle.coroutineScope.launch {
            lifecycle.whenStateAtLeast(state) { block() }
        }
    }
}
