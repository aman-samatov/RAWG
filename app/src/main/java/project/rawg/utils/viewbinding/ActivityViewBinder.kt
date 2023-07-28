package project.rawg.utils.viewbinding

import android.view.View
import androidx.activity.ComponentActivity

@PublishedApi
internal class ActivityViewBinder<T>(bindingClass: Class<T>) {

    private val bindMethod by lazy {
        bindingClass.getMethod("bind", View::class.java)
    }

    @Suppress("UNCHECKED_CAST")
    fun bind(componentActivity: ComponentActivity) = bindMethod.invoke(null, componentActivity) as T
}
