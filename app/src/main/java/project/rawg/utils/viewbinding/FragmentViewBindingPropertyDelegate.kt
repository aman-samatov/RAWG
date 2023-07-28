package project.rawg.utils.viewbinding

import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import project.rawg.utils.viewbinding.ViewBindingPropertyDelegate

@PublishedApi
internal class FragmentViewBindingPropertyDelegate<TBinding : ViewBinding>(
    viewBinder: (Fragment) -> TBinding
) : ViewBindingPropertyDelegate<Fragment, TBinding>(viewBinder) {
    override fun getLifecycleOwner(thisRef: Fragment): LifecycleOwner = thisRef.viewLifecycleOwner
}
