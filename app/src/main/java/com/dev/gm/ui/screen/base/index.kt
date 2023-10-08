package com.dev.gm.ui.screen.base

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.dev.gm.R
import com.dev.gm.domain.model.Game
import com.dev.gm.ui.component.drawer.NavigationDrawer
import com.dev.gm.ui.component.drawer.NavigationDrawerItem
import com.dev.gm.ui.screen.game.GameDetailScreen
import com.dev.gm.ui.screen.game.GameDetailViewModel
import com.dev.gm.ui.screen.home.HomeScreen
import com.dev.gm.ui.screen.search.SearchScreen
import com.dev.gm.ui.screen.search.SearchViewModel
import com.dev.gm.util.ALL_GAMES_KEY
import com.dev.gm.util.Resource
import com.dev.gm.util.SEARCH_SCREEN_FILTER_KEY

@Composable
fun Index(
    scaffoldState: ScaffoldState,
    navHostController: NavHostController,
    availableGames: Resource<List<Game>>,
    onOpenDrawer: () -> Unit,
    onSearchButtonClick: () -> Unit,
    onGameClick: (Int) -> Unit,
    onBackPress: () -> Unit,
    onPlayTheGameClicked: (String) -> Unit,
    onHomeMenuClick: () -> Unit,
    onPCGamesMenuClick: () -> Unit,
    onWebGamesMenuClick: () -> Unit,
    onLatestGamesMenuClick: () -> Unit,
    onClearQuery: () -> Unit,


){
    Scaffold (
        scaffoldState = scaffoldState,
        drawerShape = RectangleShape,
        drawerContent ={
            NavigationDrawer(
                header = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .requiredHeight(height = dimensionResource(id = com.intuit.sdp.R.dimen._200sdp))
                    ){
                        Image(
                            modifier = Modifier
                                .size(150.dp)
                                .align(Alignment.Center),
                            painter = painterResource(id = R.drawable.icons__1_ ),
                            contentDescription = ""
                        )
                    }

                }, content = {
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(all = 5.dp),
                        onClick = onHomeMenuClick,
                        iconPainter = painterResource(id = R.drawable.ic_bars_staggered_solid),
                        iconColor = MaterialTheme.colors.primary,
                        textStyle = MaterialTheme.typography.subtitle1,
                        text = "All games",
                        textColor = MaterialTheme.colors.onBackground
                    )
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(all = 5.dp),
                        onClick = onPCGamesMenuClick,
                        iconPainter = painterResource(id = R.drawable.ic_windows_brands),
                        iconColor = MaterialTheme.colors.primary,
                        textStyle = MaterialTheme.typography.subtitle1,
                        text = "PC games",
                        textColor = MaterialTheme.colors.onBackground
                    )
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(all = 5.dp),
                        onClick = onWebGamesMenuClick,
                        iconPainter = painterResource(id = R.drawable.ic_window_maximize_solid),
                        iconColor = MaterialTheme.colors.primary,
                        textStyle = MaterialTheme.typography.subtitle1,
                        text = "Web games",
                        textColor = MaterialTheme.colors.onBackground
                    )
                    NavigationDrawerItem(
                        modifier = Modifier
                            .requiredHeight(45.dp)
                            .padding(all = 5.dp),
                        onClick = onLatestGamesMenuClick,
                        iconPainter = painterResource(id = R.drawable.ic_arrow_trend_up_solid),
                        iconColor = MaterialTheme.colors.primary,
                        textStyle = MaterialTheme.typography.subtitle1,
                        text = "Latest games",
                        textColor = MaterialTheme.colors.onBackground
                    )

                }
            )


        }
    ){innerPadding ->
        NavHost(
            navController = navHostController,
            startDestination = Screen.HomeScreen.route,
            modifier = Modifier.padding(innerPadding)
        ){

            composable(route = Screen.HomeScreen.route){
                HomeScreen(
                    availableGames = availableGames,
                    onOpenDrawer = onOpenDrawer,
                    onSearch = onSearchButtonClick,
                    onGameClick = { id -> onGameClick(id)}

                )
            }

            composable(route = Screen.GameDetailScreen.route){
                val viewModel = hiltViewModel<GameDetailViewModel>()
                GameDetailScreen(
                    viewModel = viewModel,
                    onBackPress = onBackPress,
                    onPlayTheGameClicked = { gameUrl ->
                        onPlayTheGameClicked(gameUrl)
                    }

                    )
            }
            composable(
                route = Screen.SearchScreen.route,
                arguments = listOf(
                    navArgument(name = SEARCH_SCREEN_FILTER_KEY) {
                        defaultValue = ""
                        type = NavType.StringType
                    }
                )
            ) {
                val viewModel = hiltViewModel<SearchViewModel>()
                val games =
                    navHostController.previousBackStackEntry?.savedStateHandle?.get<List<Game>>(key = ALL_GAMES_KEY)
                        ?: emptyList()
                SearchScreen(
                    viewModel = viewModel,
                    games = games,
                    onItemClick = onGameClick,
                    onOpenDrawer = onOpenDrawer,
                    onSearchClick = onSearchButtonClick,
                    onClearQuery = onClearQuery
                )
            }
        }
    }
}