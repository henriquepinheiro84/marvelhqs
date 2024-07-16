package com.pinheiro.marvelhqs.presenter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pinheiro.marvelhqs.domain.viewobject.ComicViewObject
import com.pinheiro.marvelhqs.ui.theme.MarvelHQsTheme
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {

//    val viewModel: MarvelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MarvelHQsTheme {
                // A surface container using the 'background' color from the theme
//                val factory = KoinViewModelFactory(LocalContext.current, )
                val viewModel: MarvelViewModel = koinViewModel()
                val comics = viewModel.comics.collectAsState(initial = null)
                viewModel.getCharacters()
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    comics.value?.let {comics ->
                        items(comics)  {comic ->
                            comic.id?.let {
                                ComicItem(
                                    comic = comic,
                                    modifier = Modifier.fillMaxWidth()
                                    )
                            }
//                            if (it != null) {
//                                ComicItem(
//                                    comic = it,
//                                    modifier = Modifier.fillMaxWidth()
//                                )
//                            }
                        }

                    }
                }

            }
        }
    }
}

@Composable
fun toast() {
    val context = LocalContext.current

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MarvelHQsTheme {
        Greeting("Android")
    }
}