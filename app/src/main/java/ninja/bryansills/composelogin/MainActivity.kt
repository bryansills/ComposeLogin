package ninja.bryansills.composelogin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
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
                val navigatorFactory = NavigatorFactory(navController)

                NavHost(navController, startDestination = "Profile") {
                    composable("Profile") {
                        val navigator = navigatorFactory.get(isLoggedIn)
                        Profile(navigator, isLoggedIn, toggleLogin)
                    }
                    composable("Dashboard") {
                        val navigator = navigatorFactory.get(isLoggedIn)
                        Dashboard(navigator)
                    }
                    composable("Scrollable") {
                        val navigator = navigatorFactory.get(isLoggedIn)
                        Scrollable(navigator)
                    }
                }
            }
        }
    }
}

sealed class Screen(val title: String) {
    object Profile : Screen("Profile")
    object Dashboard : Screen("Dashboard")
    object Scrollable : Screen("Scrollable")
}

@Composable
fun NavigationRoot(navController: NavHostController, isLoggedIn: Boolean, toggleLogin: () -> Unit) {
    val navigatorFactory = NavigatorFactory(navController)
    val navigator = navigatorFactory.get(isLoggedIn)

    NavHost(navController, startDestination = "Profile") {
        composable("Profile") { Profile(navigator, isLoggedIn, toggleLogin)  }
        composable("Dashboard") { Dashboard(navigator) }
        composable("Scrollable") { Scrollable(navigator) }
    }
}

@Composable
fun Profile(navigator: Navigator, isLoggedIn: Boolean, toggleLogin: () -> Unit) {
    Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        Text(text = Screen.Profile.title)
        NavigateButton(Screen.Dashboard.title) { navigator.showDashboard() }
        Divider(color = Color.Black)

        if (isLoggedIn) {
            NavigateButton(Screen.Scrollable.title) { navigator.showScrollable() }
        }

        Spacer(Modifier.weight(1f))
        Button(onClick = toggleLogin) {
            Text(text = "Toggle Login to: ${!isLoggedIn}")
        }
        NavigateBackButton(navigator)
    }
}

@Composable
fun Dashboard(navigator: Navigator) {
    Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        Text(text = Screen.Dashboard.title)
        Spacer(Modifier.weight(1f))
        NavigateBackButton(navigator)
    }
}

@Composable
fun Scrollable(navigator: Navigator) {
    Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        NavigateButton(Screen.Dashboard.title) { navigator.showDashboard() }
        ScrollableColumn(Modifier.weight(1f)) {
            phrases.forEach { phrase ->
                Text(phrase, fontSize = 30.sp)
            }
        }
        NavigateBackButton(navigator)
    }
}

@Composable
fun NavigateButton(screenTitle: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Navigate to $screenTitle")
    }
}

@Composable
fun NavigateBackButton(navigator: Navigator) {
    if (navigator.hasPreviousBackStackEntry) {
        Button(
            onClick = { navigator.popBackStack() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Go to Previous screen")
        }
    }
}

private val phrases = listOf(
    "Easy As Pie",
    "Wouldn't Harm a Fly",
    "No-Brainer",
    "Keep On Truckin'",
    "An Arm and a Leg",
    "Down To Earth",
    "Under the Weather",
    "Up In Arms",
    "Cup Of Joe",
    "Not the Sharpest Tool in the Shed",
    "Ring Any Bells?",
    "Son of a Gun",
    "Hard Pill to Swallow",
    "Close But No Cigar",
    "Beating a Dead Horse",
    "If You Can't Stand the Heat, Get Out of the Kitchen",
    "Cut To The Chase",
    "Heads Up",
    "Goody Two-Shoes",
    "Fish Out Of Water",
    "Cry Over Spilt Milk",
    "Elephant in the Room",
    "There's No I in Team",
    "Poke Fun At",
    "Talk the Talk",
    "Know the Ropes",
    "Fool's Gold",
    "It's Not Brain Surgery",
    "Fight Fire With Fire",
    "Go For Broke"
)
