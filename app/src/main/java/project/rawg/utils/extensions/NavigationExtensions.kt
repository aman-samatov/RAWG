package project.rawg.utils.extensions

import androidx.annotation.AnimRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import androidx.lifecycle.Lifecycle
import project.rawg.R

private const val PREVIOUS_FRAGMENT_TAG_ARG = "PREVIOUS_FRAGMENT_TAG_ARG"
private var backPressedTime = 0L
private const val MIN_BACK_PRESSED_TIME = 2000
private const val MIN_BACK_STACK_ENTRY_COUNT = 2

fun Fragment.popScreen() {
    requireActivity().hideKeyboard()

    val fragmentManager = activity?.supportFragmentManager ?: childFragmentManager
    whenStateAtLeast(Lifecycle.State.STARTED) {
        fragmentManager.popBackStack()
    }
}

fun FragmentActivity.popFeature() {
    if (supportFragmentManager.backStackEntryCount < MIN_BACK_STACK_ENTRY_COUNT) {
        val backText = resources.getString(R.string.back_text)
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < MIN_BACK_PRESSED_TIME) {
            finish()
        } else {
            backPressedTime = currentTime
            this.createSnackBar(findViewById(R.id.container), backText)
        }
    } else {
        whenStateAtLeast(Lifecycle.State.STARTED) {
            supportFragmentManager.popBackStack()
        }
    }
}

private fun Fragment.getPreviousTag(): String? = arguments?.getString(PREVIOUS_FRAGMENT_TAG_ARG)

fun Fragment.getCurrentScreen(): Fragment? =
    childFragmentManager.findFragmentById(R.id.container)

fun Fragment.addScreen(
    fragment: Fragment,
    popCurrent: Boolean = false,
    addToBackStack: Boolean = true,
    requestCode: Int? = null,
    tag: String = fragment::class.java.name,
    @AnimRes enter: Int = R.anim.fade_in,
    @AnimRes exit: Int = R.anim.fade_out,
    @AnimRes popEnter: Int = R.anim.nav_pop_enter,
    @AnimRes popExit: Int = R.anim.nav_pop_exit,
    fragmentManager: FragmentManager = activity?.supportFragmentManager ?: childFragmentManager
) = whenStateAtLeast(Lifecycle.State.STARTED) {
    requireActivity().hideKeyboard()
    fragmentManager.commit {
        setCustomAnimations(enter, exit, popEnter, popExit)
        if (popCurrent) {
            getCurrentScreen()
                ?.let { it.getPreviousTag() ?: it::class.java.name }
                ?.let {
                    fragment.appendArgs(PREVIOUS_FRAGMENT_TAG_ARG to it)
                    fragmentManager.popBackStack()
                }
        }
        add(R.id.container, fragment, tag)
        if (addToBackStack) addToBackStack(tag)
        if (requestCode != null) fragment.setTargetFragment(this@addScreen, requestCode)
    }
}
