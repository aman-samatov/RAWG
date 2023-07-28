package project.rawg.root

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import dagger.hilt.android.AndroidEntryPoint
import project.rawg.R
import project.rawg.databinding.ActivityRootBinding
import project.rawg.mainpage.ui.MainPageFragment
import project.rawg.utils.extensions.popFeature


@AndroidEntryPoint
class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_RAWG)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replace(MainPageFragment())
    }

    @Deprecated(
        "Deprecated in Java",
        ReplaceWith("popFeature()", "project.rawg.utils.extensions.popFeature")
    )
    override fun onBackPressed() {
        popFeature()
    }

    private fun replace(
        fragment: Fragment,
        tag: String = fragment::class.java.name
    ) {
        val fragmentManager = this.supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }
}
