package project.rawg.utils.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.behavior.SwipeDismissBehavior
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import project.rawg.R

const val DEFAULT_THROTTLE_DURATION_IN_MILLIS = 300L

fun Activity.hideKeyboard() {
    currentFocus?.hideKeyboard()
}

fun View.hideKeyboard() {
    post {
        (context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            .hideSoftInputFromWindow(windowToken, 0)
    }
}

fun FragmentActivity.createSnackBar(view: View, message: String) {
    val behavior = BaseTransientBottomBar.Behavior().apply {
        setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY)
    }
    this.baseContext?.let {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT)
            .setActionTextColor(ContextCompat.getColor(it, R.color.lotus))
            .setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_SLIDE).setBehavior(behavior)
            .setBackgroundTint(ContextCompat.getColor(it, R.color.grey))
            .show()
    }
}

fun View.setThrottleOnClickListener(callback: (view: View) -> Unit) {
    var lastClickTime = 0L
    this.setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > DEFAULT_THROTTLE_DURATION_IN_MILLIS) {
            lastClickTime = currentTimeMillis
            callback.invoke(it)
        }
    }
}

fun RecyclerView.attachAdapter(adapter: RecyclerView.Adapter<*>) {
    doOnAttach { this.adapter = adapter }
    doOnDetach { this.adapter = null }
}
