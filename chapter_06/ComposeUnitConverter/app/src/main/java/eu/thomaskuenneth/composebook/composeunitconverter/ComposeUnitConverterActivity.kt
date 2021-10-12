package eu.thomaskuenneth.composebook.composeunitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eu.thomaskuenneth.composebook.composeunitconverter.screens.ComposeUnitConverterScreen
import eu.thomaskuenneth.composebook.composeunitconverter.screens.DistancesConverter
import eu.thomaskuenneth.composebook.composeunitconverter.screens.TemperatureConverter
import eu.thomaskuenneth.composebook.composeunitconverter.theme.ComposeUnitConverterTheme
import eu.thomaskuenneth.composebook.composeunitconverter.viewmodels.ViewModelFactory

class ComposeUnitConverterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ViewModelFactory(Repository(this))
        setContent {
            ComposeUnitConverter(factory)
        }
    }
}

@Composable
fun ComposeUnitConverter(factory: ViewModelFactory) {
    val navController = rememberNavController()
    ComposeUnitConverterTheme {
        Scaffold(topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            })
        },
            bottomBar = {
                BottomNavigation {
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    ComposeUnitConverterScreen.screens.forEach { screen ->
                        BottomNavigationItem(
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    launchSingleTop = true
                                }
                            },
                            label = {
                                Text(text = stringResource(id = screen.label))
                            },
                            icon = {
                                Icon(
                                    painter = painterResource(id = screen.icon),
                                    contentDescription = stringResource(id = screen.label)
                                )
                            },
                            alwaysShowLabel = false
                        )
                    }
                }
            }) {
            NavHost(
                navController = navController,
                startDestination = ComposeUnitConverterScreen.route_temperature
            ) {
                composable(ComposeUnitConverterScreen.route_temperature) {
                    TemperatureConverter(
                        viewModel = viewModel(factory = factory)
                    )
                }
                composable(ComposeUnitConverterScreen.route_distances) {
                    DistancesConverter(
                        viewModel = viewModel()
                    )
                }
            }
        }
    }
}

