package com.pinheiro.marvelhqs

import android.content.Context
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.isNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.test.core.app.ApplicationProvider
import com.pinheiro.marvelhqs.presentation.ui.authentication.LoginScreen
import com.pinheiro.marvelhqs.presentation.ui.navigation.ComicNavigation
import com.pinheiro.marvelhqs.presentation.ui.navigation.Comics
import com.pinheiro.marvelhqs.presentation.ui.navigation.Home
import com.pinheiro.marvelhqs.presentation.ui.navigation.Logged
import com.pinheiro.marvelhqs.presentation.ui.navigation.Login
import com.pinheiro.marvelhqs.presentation.ui.navigation.Registration
import com.pinheiro.marvelhqs.presentation.ui.theme.MarvelHQsTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MarvelsEndToEndTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    fun setUp() {
        composeRule.setContent {
            MarvelHQsTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Home) {
                    navigation<Home>(startDestination = Login) {
                        composable<Login> {
                            LoginScreen(navController = navController)
                        }

                        composable<Registration> {

                        }
                    }

                    navigation<Logged>(startDestination = Comics) {
                        composable<Comics> {
                            ComicNavigation(navController)
                        }

                    }
                }

            }
        }
    }

    @Test
    fun login_navigateToHome_NavigationToFavorite_Logout() {
        // Get Context
        val context = ApplicationProvider.getApplicationContext<Context>()
        // Perform Login
        composeRule.onNodeWithTag(context.getString(R.string.USER_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithTag(context.getString(R.string.PASSWORD_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithText(context.getString(R.string.LOGIN_BUTON_TITLE)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.TOP_BAR_TITLE)).isDisplayed()
// Navigation to Favorite
        composeRule.onNodeWithText(context.getString((R.string.FAVORITE))).performClick()
        composeRule.onNodeWithText(context.getString(R.string.TOP_BAR_TITLE)).isDisplayed()
        //Logout
        composeRule.onNodeWithText(context.getString((R.string.LOG_OUT))).performClick()
        composeRule.onNodeWithText(context.getString(R.string.TOP_BAR_TITLE)).isNotDisplayed()


    }

}