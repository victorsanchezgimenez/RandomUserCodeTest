package alisys.robotica.randomusercodetest.view.navigate

import alisys.robotica.randomusercodetest.view.screen.DetailsScreen
import alisys.robotica.randomusercodetest.view.screen.UsersScreen
import alisys.robotica.randomusercodetest.view.utils.constants.NavArgs
import alisys.robotica.randomusercodetest.view.utils.constants.NavRoutes
import alisys.robotica.viewmodel.LanguageViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


//Navigation control and parameters
@Composable
fun AppNavigation(languageViewModel: LanguageViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavRoutes.UsersScreen) {
        composable(NavRoutes.UsersScreen) {
            UsersScreen(navController, languageViewModel)
        }
        composable(
            "${NavRoutes.DetailsScreen}/{${NavArgs.FirstName}}/{${NavArgs.LastName}}/{${NavArgs.Email}}/{${NavArgs.Gender}}/{${NavArgs.RegisterDate}}/{${NavArgs.Phone}}/{${NavArgs.Thumbnail}}",
            arguments = listOf(
                navArgument(NavArgs.FirstName) { type = NavType.StringType },
                navArgument(NavArgs.LastName) { type = NavType.StringType },
                navArgument(NavArgs.Email) { type = NavType.StringType },
                navArgument(NavArgs.Gender) { type = NavType.StringType },
                navArgument(NavArgs.RegisterDate) { type = NavType.StringType },
                navArgument(NavArgs.Phone) { type = NavType.StringType },
                navArgument(NavArgs.Thumbnail) { type = NavType.StringType }
            )
        ) { backStackEntry ->
            DetailsScreen(
                navController = navController,
                languageViewModel = languageViewModel,
                firstName = backStackEntry.arguments?.getString(NavArgs.FirstName) ?: "",
                lastName = backStackEntry.arguments?.getString(NavArgs.LastName) ?: "",
                email = backStackEntry.arguments?.getString(NavArgs.Email) ?: "",
                gender = backStackEntry.arguments?.getString(NavArgs.Gender) ?: "",
                registerDate = backStackEntry.arguments?.getString(NavArgs.RegisterDate) ?: "",
                phone = backStackEntry.arguments?.getString(NavArgs.Phone) ?: "",
                thumbnail = backStackEntry.arguments?.getString(NavArgs.Thumbnail) ?: ""
            )
        }
    }
}
