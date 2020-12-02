package ninja.bryansills.composelogin

import androidx.navigation.NavController
import androidx.navigation.compose.navigate

interface Navigator {
    val hasPreviousBackStackEntry: Boolean
    fun popBackStack()
    fun showDashboard()
    fun showScrollable()
}

class LoggedInNavigator(private val navController: NavController) : Navigator {
    override val hasPreviousBackStackEntry: Boolean
        get() = navController.previousBackStackEntry != null

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun showDashboard() {
        navController.navigate(Screen.Dashboard.title)
    }

    override fun showScrollable() {
        navController.navigate(Screen.Scrollable.title)
    }
}

class LoggedOutNavigator(private val navController: NavController) : Navigator {
    override val hasPreviousBackStackEntry: Boolean
        get() = navController.previousBackStackEntry != null

    override fun popBackStack() {
        navController.popBackStack()
    }

    override fun showDashboard() {
        navController.navigate(Screen.Dashboard.title)
    }

    override fun showScrollable() {
        throw IllegalStateException("You cannot show the scrollable screen while logged out")
    }
}

class NavigatorFactory(private val navController: NavController) {
    private val loggedInNavigator: LoggedInNavigator by lazy { LoggedInNavigator(navController) }
    private val loggedOutNavigator: LoggedOutNavigator by lazy { LoggedOutNavigator(navController) }

    fun get(isLoggedIn: Boolean): Navigator {
        return if (isLoggedIn) {
            loggedInNavigator
        } else {
            loggedOutNavigator
        }
    }
}
