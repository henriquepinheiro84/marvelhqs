package com.pinheiro.marvelhqs.presentation.ui.comic

import android.content.Context
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.pinheiro.marvelhqs.R
import com.pinheiro.marvelhqs.presentation.ui.navigation.ComicNavigation
import com.pinheiro.marvelhqs.presentation.ui.theme.MarvelHQsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ComicScreenTest {
    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            MarvelHQsTheme {
                val navController = rememberNavController()
                ComicNavigation(navController)
            }
        }
    }

    @Test
    fun apBarTitle_isVisible() {

        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithText(context.getString(R.string.TOP_BAR_TITLE)).isDisplayed()
    }

}