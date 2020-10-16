package ninja.bryansills.composelogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Text
import androidx.compose.material.Scaffold
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.ui.tooling.preview.Preview
import ninja.bryansills.composelogin.ui.ComposeLoginTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                BasicNav()
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeLoginTheme {
        Greeting("Android")
    }
}

@Composable
fun AppBarDemo() {
    val navController = rememberNavController()

    Scaffold(topBar = { TopAppBar(title = { Text(text = "Hello") }) }) {
        NavHost(navController, startDestination = "Home") {
            composable("Home") { DefaultPreview() }
        }
    }
}
