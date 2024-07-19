package com.pinheiro.marvelhqs.presenter

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
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import com.pinheiro.marvelhqs.presenter.ui.authentication.LoginScreen
import com.pinheiro.marvelhqs.presenter.ui.comic.ComicItem
import com.pinheiro.marvelhqs.presenter.ui.comic.ComicScreen
import com.pinheiro.marvelhqs.presenter.ui.favorite.FavoriteScreen
import com.pinheiro.marvelhqs.presenter.ui.navigation.ComicNavigation
import com.pinheiro.marvelhqs.presenter.ui.theme.MarvelHQsTheme
import com.pinheiro.marvelhqs.presenter.ui.viewmodels.AuthViewModel
import kotlinx.serialization.Serializable
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelHQsTheme {
//                ComicScreen()
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = Home) {
                    navigation<Home>(startDestination = Login) {
                        composable<Login> {
                            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)
                            LoginScreen(navController = navController)
                        }

                        composable<Registration> {
                            val viewModel = it.sharedViewModel<AuthViewModel>(navController = navController)

                        }
                    }

                    navigation<Logged>(startDestination = Comics) {
                        composable<Comics>{
//                            ComicItem(comic = ComicViewObject(
//                                null,
//                                null,
//                                null,
//                                null,
//                                null,
//                                null,
//                            ))
//                            ComicScreen()
                            ComicNavigation()
                        }
//                        composable<Favorite> {
//                            FavoriteScreen()
//                        }
                    }
                }

            }
        }
    }
}

@Serializable
object Logged
@Serializable
object Home
@Serializable
object Login
@Serializable
object Registration

@Serializable
object Comics

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

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    MarvelHQsTheme {
//        Greeting("Android")
//    }
//}