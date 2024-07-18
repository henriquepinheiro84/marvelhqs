package com.pinheiro.marvelhqs.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
import com.pinheiro.marvelhqs.presenter.ui.comic.ComicScreen
import com.pinheiro.marvelhqs.presenter.ui.theme.MarvelHQsTheme
import com.pinheiro.marvelhqs.presenter.ui.viewmodels.AuthViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
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
                            ComicScreen()
                        }
                        composable<Favorite> {

                        }
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

@Serializable
object Favorite

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