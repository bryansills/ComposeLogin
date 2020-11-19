package ninja.bryansills.composelogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.setContent
import androidx.navigation.compose.rememberNavController
import ninja.bryansills.composelogin.ui.ComposeLoginTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLoginTheme {
                var isLoggedIn by remember { mutableStateOf(false) }
                val toggleLogin = { isLoggedIn = isLoggedIn.not() }
                val navController = rememberNavController()

                BasicNav(navController, isLoggedIn, toggleLogin)
            }
        }
    }
}
