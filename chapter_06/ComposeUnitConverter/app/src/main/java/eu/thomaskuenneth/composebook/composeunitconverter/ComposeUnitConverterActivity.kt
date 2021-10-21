package eu.thomaskuenneth.composebook.composeunitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import eu.thomaskuenneth.composebook.composeunitconverter.screens.ComposeUnitConverterScreen
import eu.thomaskuenneth.composebook.composeunitconverter.screens.DistancesConverter
import eu.thomaskuenneth.composebook.composeunitconverter.screens.TemperatureConverter
import eu.thomaskuenneth.composebook.composeunitconverter.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch

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
    val menuItems = listOf("Item #1", "Item #2")
    val scaffoldState = rememberScaffoldState()
    val snackbarCoroutineScope = rememberCoroutineScope()
    ComposeUnitConverterTheme {
        Scaffold(scaffoldState = scaffoldState,
            topBar = {
                ComposeUnitConverterTopBar(menuItems) { s ->
                    snackbarCoroutineScope.launch {
                        scaffoldState.snackbarHostState.showSnackbar(s)
                    }
                }
            },
            bottomBar = {
                ComposeUnitConverterBottomBar(navController)
            }
        ) {
            ComposeUnitConverterNavHost(
                navController = navController,
                factory = factory
            )
        }
    }
}

@Composable
fun ComposeUnitConverterTopBar(menuItems: List<String>, onClick: (String) -> Unit) {
    var menuOpened by remember { mutableStateOf(false) }
    TopAppBar(title = {
        Text(text = stringResource(id = R.string.app_name))
    },
        actions = {
            Box {
                IconButton(onClick = {
                    menuOpened = true
                }) {
                    Icon(Icons.Default.MoreVert, "")
                }
                DropdownMenu(expanded = menuOpened,
                    onDismissRequest = {
                        menuOpened = false
                    }) {
                    menuItems.forEachIndexed { index, s ->
                        if (index > 0) Divider()
                        DropdownMenuItem(onClick = {
                            menuOpened = false
                            onClick(s)
                        }) {
                            Text(s)
                        }
                    }
                }
            }
        }
    )
}

@Composable
fun ComposeUnitConverterBottomBar(navController: NavHostController) {
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
}

@Composable
fun ComposeUnitConverterNavHost(
    navController: NavHostController,
    factory: ViewModelProvider.Factory?
) {
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
                viewModel = viewModel(factory = factory)
            )
        }
    }
}