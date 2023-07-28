package project.rawg.mainpage.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import project.rawg.launchFragmentInHiltContainer

@RunWith(AndroidJUnit4::class)
@MediumTest
@HiltAndroidTest
class MainPageFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun clickOnGame_navigateToDetailsPage() {
        launchFragmentInHiltContainer<MainPageFragment> {

        }
    }
}