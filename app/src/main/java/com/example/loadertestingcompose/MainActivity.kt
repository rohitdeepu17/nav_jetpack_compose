package com.example.loadertestingcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loadertestingcompose.ui.theme.LoaderTestingComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoaderTestingComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //Greeting("Android")
                    //DemoScreen()

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Screens.Splash) {
                        composable(route = Screens.Splash) {
                            //SplashScreen(navController = navController)
                            SplashScreen{
                                navController.navigate("${Screens.Dashboard}/${it}")
                            }
                        }

                        composable(route = "${Screens.Dashboard}/{sampleText}", arguments = listOf(navArgument("sampleText"){type= NavType.StringType})) {
                            navBackStackEntry ->  DashboardScreen(navBackStackEntry.arguments?.getString("sampleText"))
                        }
                    }
                }
            }
        }
    }
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
    LoaderTestingComposeTheme {
        Greeting("Android")
    }
}

/*
@Composable
fun DemoScreen() {
    val viewModel = DemoScreenViewModel()
    when (val state = viewModel.state.collectAsState().value) {
        DemoScreenViewModel.State.Loading -> {
            Text("Loading")
        }
        is DemoScreenViewModel.State.Data -> {
            DemoScreenContent(state)
        }
    }
}

@Composable
fun DemoScreenContent(data: DemoScreenViewModel.State.Data){
    Text(text = data.data)
}


class DemoScreenViewModel : ViewModel() {
    sealed class State {
        object Loading: State()
        data class Data(val data: String): State()
    }

    private var _state = MutableStateFlow<State>(State.Loading)
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            while (isActive) {
                val data = makeDataLoadCallOnEntry()
                _state.value = State.Data(data)
                // wait one minute and repeat your request
                delay(6 * 1000L)
            }
        }
    }

    suspend fun makeDataLoadCallOnEntry(): String {
        delay(1000)
        return "Hello world"
    }
}*/

object Screens {
    const val Splash = "Splash"
    const val Dashboard = "Dashboard"
}


