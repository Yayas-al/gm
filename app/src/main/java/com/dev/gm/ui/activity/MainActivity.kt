package com.dev.gm.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalUriHandler
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.dev.gm.ui.screen.base.Index
import com.dev.gm.ui.screen.base.Screen
import com.dev.gm.ui.theme.GmTheme
import com.dev.gm.util.ALL_GAMES_KEY
import com.dev.gm.util.BROWSER_GAMES
import com.dev.gm.util.FILTER_MODE_KEY
import com.dev.gm.util.LATEST_GAMES
import com.dev.gm.util.PC_GAMES
import com.dev.gm.util.SEARCH_MODE
import com.dev.gm.util.SEARCH_MODE_KEY
import com.dev.gm.util.SEARCH_SCREEN_MODE_KEY
import com.dev.gm.util.SHOOT
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition{
                mainViewModel.splashScreenVisible.value
            }
        }
        setContent {
            val scaffoldState = rememberScaffoldState()
            val navHostController = rememberNavController()
            val availableGame by mainViewModel.games
                .collectAsState()
            val scope = rememberCoroutineScope()
            val uriHandler = LocalUriHandler.current
            GmTheme(
                darkTheme = isSystemInDarkTheme()
            ) {
                Index(
                    scaffoldState = scaffoldState,
                    navHostController = navHostController,
                    availableGames = availableGame,
                    onOpenDrawer = {
                                   scope.launch {
                                       scaffoldState.drawerState.open()
                                   }
                                   },
                    onSearchButtonClick = {
                        val path ="search?mode=$SEARCH_MODE"
                        navHostController.currentBackStackEntry?.savedStateHandle?.set(
                            key = "ALL_GAMES_KEY" ,
                            value = availableGame.data ?: emptyList()
                        )
                        navHostController.navigate(route = path)
                    },
                    onGameClick = {gameId ->
                        navHostController.navigate(route = "gameDetail/$gameId")

                    },
                    onBackPress = { navHostController.navigateUp()},
                    onClearQuery = { navHostController.popBackStack()},
                    onPlayTheGameClicked = { gameUrl ->
                        uriHandler.openUri(uri = gameUrl)
                    },
                    onHomeMenuClick = {
                        scope.launch {
                            scaffoldState.drawerState.close()
                            navHostController.navigate(route = Screen.HomeScreen.route)
                        }
                    },
                    onLatestGamesMenuClick = {
                        scope .launch {
                            val path = "search?mode=$FILTER_MODE_KEY&filter=$SHOOT"
                            scaffoldState.drawerState.close()
                            navHostController.navigate(route = path)
                        }
                    },
                    onPCGamesMenuClick = {
                        scope .launch {
                            val path = "search?mode=$FILTER_MODE_KEY&filter=$PC_GAMES"
                            scaffoldState.drawerState.close()
                            navHostController.navigate(route = path)
                        }

                    },
                    onWebGamesMenuClick = {
                        scope .launch {
                            val path = "search?mode=$FILTER_MODE_KEY&filter=$BROWSER_GAMES"
                            scaffoldState.drawerState.close()
                            navHostController.navigate(route = path)
                        }
                    }
                )
            }
        }
    }
}
