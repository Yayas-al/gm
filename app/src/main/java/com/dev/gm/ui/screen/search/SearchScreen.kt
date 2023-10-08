package com.dev.gm.ui.screen.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.dev.gm.domain.model.Game
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import com.dev.gm.util.SEARCH_MODE
import com.dev.gm.util.SEARCH_MODE_KEY
import com.dev.gm.util.SEARCH_SCREEN_MODE_KEY

@Composable
fun SearchScreen(
    viewModel: SearchViewModel,
    onItemClick: (Int) -> Unit,
    onOpenDrawer: () -> Unit,
    onSearchClick: () -> Unit,
    onClearQuery: () -> Unit,
    games: List<Game>
){
    val screenMode by viewModel.screenMode
        .collectAsState()

    val searchQuery by viewModel.query
        .collectAsState()

    val isLoading by viewModel.isLoading
        .collectAsState(initial = false)

    val searchDetailVisible by viewModel.searchDetailVisible
        .collectAsState()

    val availableGames by viewModel.games
        .collectAsState()

    val scope = rememberCoroutineScope()

    val focusManager = LocalFocusManager.current

    Column(modifier = Modifier.fillMaxSize()) {
        if(screenMode == SEARCH_MODE){
            SearchMode(
                query = searchQuery,
                searchDetailVisible = searchDetailVisible,
                isLoading = isLoading,
                searchSuggestions = availableGames,
                focusManager = focusManager,
                onClearQuery = {
                    viewModel.clearSearchQuery()
                    onClearQuery
                },
                onSearch = { query ->
                    viewModel.onQuery(query = query)
                    if(query.isNotEmpty()){
                        viewModel.onSearch(games = games)
                    }
                },
                search = {
                    if(searchQuery.isNotEmpty()){
                        viewModel.showSearchDetail()
                    }
                },
                onItemClick = {id ->
                    onItemClick(id)
                },

            )
        }else{
            FilterMode(
                isLoading = isLoading,
                games = availableGames,
                onGameClick = onItemClick,
                onOpenDrawer = onOpenDrawer,
                onSearchClick = onSearchClick,
            )
        }
    }

}