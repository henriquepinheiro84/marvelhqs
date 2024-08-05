package com.pinheiro.marvelhqs.presentation.ui.authentication

import android.content.Context
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
import com.pinheiro.marvelhqs.R
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

class LoginScreenTest {

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
    fun failed_login_with_wrong_user() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(context.getString(R.string.USER_TEST_TAG)).performTextInput("abc")
        composeRule.onNodeWithTag(context.getString(R.string.PASSWORD_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithText(context.getString(R.string.LOGIN_BUTON_TITLE)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.USER_LOGIN_FAILED)).assertExists()
    }

    @Test
    fun failed_login_with_wrong_password() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(context.getString(R.string.USER_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithTag(context.getString(R.string.PASSWORD_TEST_TAG))
            .performTextInput("abc")
        composeRule.onNodeWithText(context.getString(R.string.LOGIN_BUTON_TITLE)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.USER_LOGIN_FAILED)).assertExists()
    }

    @Test
    fun failed_login_with_empty_user() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(context.getString(R.string.USER_TEST_TAG)).performTextInput("")
        composeRule.onNodeWithTag(context.getString(R.string.PASSWORD_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithText(context.getString(R.string.LOGIN_BUTON_TITLE)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.USER_LOGIN_FAILED)).assertExists()
    }

    @Test
    fun failed_login_with_empty_password() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(context.getString(R.string.USER_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithTag(context.getString(R.string.PASSWORD_TEST_TAG))
            .performTextInput("")
        composeRule.onNodeWithText(context.getString(R.string.LOGIN_BUTON_TITLE)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.USER_LOGIN_FAILED)).assertExists()
    }

    @Test
    fun success_login() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        composeRule.onNodeWithTag(context.getString(R.string.USER_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithTag(context.getString(R.string.PASSWORD_TEST_TAG))
            .performTextInput("admin")
        composeRule.onNodeWithText(context.getString(R.string.LOGIN_BUTON_TITLE)).performClick()
        composeRule.onNodeWithText(context.getString(R.string.USER_LOGIN_FAILED))
            .assertDoesNotExist()
    }
}