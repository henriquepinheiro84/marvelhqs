package com.pinheiro.marvelhqs.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.pinheiro.marvelhqs.presentation.ui.authentication.LoginScreen
import com.pinheiro.marvelhqs.presentation.ui.navigation.ComicNavigation
import com.pinheiro.marvelhqs.presentation.ui.navigation.Comics
import com.pinheiro.marvelhqs.presentation.ui.navigation.Home
import com.pinheiro.marvelhqs.presentation.ui.navigation.Logged
import com.pinheiro.marvelhqs.presentation.ui.navigation.Login
import com.pinheiro.marvelhqs.presentation.ui.navigation.Registration
import com.pinheiro.marvelhqs.presentation.ui.theme.MarvelHQsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
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
                        composable<Comics>{

                            ComicNavigation(navController)
                        }

                    }
                }

            }
        }
    }
}


//@Serializable
//object Favorite

@Composable
inline fun <reified T: ViewModel>NavBackStackEntry.sharedViewModel(navController: NavController): T {
    val navGraphRoute = destination.parent?.route ?: return koinViewModel()
    val parentEntery = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }
    return viewModel(parentEntery)
}
