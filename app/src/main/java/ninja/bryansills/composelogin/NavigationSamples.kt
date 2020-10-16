package ninja.bryansills.composelogin

import android.os.Bundle
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.AmbientNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigate

sealed class Screen(val title: String) {
    object Profile : Screen("Profile")
    object Dashboard : Screen("Dashboard")
    object Scrollable : Screen("Scrollable")
}

@Composable
fun BasicNav() {
    var isLoggedIn by remember { mutableStateOf(false) }
    NavHost(startDestination = "Profile") {
        composable("Profile") { Profile(isLoggedIn) { isLoggedIn = !isLoggedIn } }
        composable("Dashboard") { Dashboard() }
        if (isLoggedIn) {
            composable("Scrollable") { Scrollable() }
        }
    }
}

@Composable
fun Profile(isLoggedIn: Boolean, toggleLogin: () -> Unit) {
    Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        Text(text = Screen.Profile.title)
        NavigateButton(Screen.Dashboard)
        Divider(color = Color.Black)
        NavigateButton(Screen.Scrollable)
        Spacer(Modifier.weight(1f))
        Button(onClick = toggleLogin) {
            Text(text = "Toggle Login to: ${!isLoggedIn}")
        }
        NavigateBackButton()
    }
}

@Composable
fun Dashboard() {
    Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        Text(text = Screen.Dashboard.title)
        Spacer(Modifier.weight(1f))
        NavigateBackButton()
    }
}

@Composable
fun Scrollable() {
    Column(Modifier.fillMaxSize().then(Modifier.padding(8.dp))) {
        NavigateButton(Screen.Dashboard)
        ScrollableColumn(Modifier.weight(1f)) {
            phrases.forEach { phrase ->
                Text(phrase, fontSize = 30.sp)
            }
        }
        NavigateBackButton()
    }
}

@Composable
fun NavigateButton(screen: Screen, args: Bundle? = null) {
    val navController = AmbientNavController.current
    Button(
        onClick = { navController.navigate(screen.title, args) },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(text = "Navigate to " + screen.title)
    }
}

@Composable
fun NavigateBackButton() {
    val navController = AmbientNavController.current
    if (navController.previousBackStackEntry != null) {
        Button(
            onClick = { navController.popBackStack() },
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
