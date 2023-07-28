package project.rawg.utils.viewbinding

import androidx.activity.ComponentActivity
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import project.rawg.utils.viewbinding.ViewBindingPropertyDelegate

@PublishedApi
internal class ActivityViewBindingPropertyDelegate<TBinding : ViewBinding>(
    viewBinder: (ComponentActivity) -> TBinding
) : ViewBindingPropertyDelegate<ComponentActivity, TBinding>(viewBinder) {
    override fun getLifecycleOwner(thisRef: ComponentActivity): LifecycleOwner = thisRef
}
