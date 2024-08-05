package com.pinheiro.marvelhqs.presentation.ui.navigation


import android.content.Context
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.pinheiro.marvelhqs.R
import com.pinheiro.marvelhqs.presentation.ui.authentication.LoginScreen
import org.junit.Rule
import org.junit.Test

class ComicNavigationTest {

    //    @get:Rule(order = 0)
//    val koinRule = KoinTestRule(
//        modules = listOf(testAppModule)
//    )
    @get:Rule
    val composeTestRule = createComposeRule()
    @Test
    fun teste() {

        val context = ApplicationProvider.getApplicationContext<Context>()

        composeTestRule.setContent {
            LoginScreen(navController = rememberNavController())
        }
            composeTestRule.onNodeWithText(
                context.getString(R.string.LOGIN_BUTON_TITLE)
            ).performClick()
    }
}