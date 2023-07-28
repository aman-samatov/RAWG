package project.rawg.core.mvvm

import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import timber.log.Timber

abstract class BaseFragment(@LayoutRes layoutRes: Int) : Fragment(layoutRes) {
    abstract fun bind()
    abstract fun initViews(view: View)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews(view)
        bind()
        Timber.d("/=/ onViewCreated ${this.javaClass.name}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.d("/=/ onDestroyView ${this.javaClass.name}")
    }

    fun setToolBar (toolbar: Toolbar){
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)
    }

    fun <T : Any, F : Flow<T>> observe(flow: F, body: (T) -> Unit) {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                flow.collect { body(it) }
            }
        }
    }
}
