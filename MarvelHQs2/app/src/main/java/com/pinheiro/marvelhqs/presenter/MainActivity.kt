package com.pinheiro.marvelhqs.presenter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.pinheiro.marvelhqs.domain.GetCharactersUseCase
import com.pinheiro.marvelhqs.ui.theme.MarvelHQsTheme
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.androidx.viewmodel.factory.KoinViewModelFactory

class MainActivity : ComponentActivity() {

//    val viewModel: MarvelViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      
        setContent {
            MarvelHQsTheme {
                // A surface container using the 'background' color from the theme
//                val factory = KoinViewModelFactory(LocalContext.current, )
                val viewModel: MarvelViewModel = koinViewModel()
                var text = viewModel.test.collectAsState(initial = "Apertar aqui")
                    Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                    Button(onClick = {   viewModel.getCharacters()}) {
                        Text(text = text.value)


                        
                    }

                }
            }
        }
    }
}
@Composable
fun toast(){
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