package com.dev.gm.ui.screen.search

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dev.gm.ui.component.SearchDetail
import com.dev.gm.ui.component.SearchSuggestions
import com.dev.gm.domain.model.Game
import com.dev.gm.ui.component.SearchBar

@Composable
fun SearchMode(
    isLoading: Boolean,
    searchSuggestions: List<Game>,
    focusManager: FocusManager,
    onClearQuery: () -> Unit,
    onSearch: (String) -> Unit,
    search: () -> Unit,
    query: String,
    searchDetailVisible: Boolean,
    onItemClick:(Int) -> Unit,
){

    SearchBar(
        query = query,
        focusManager = focusManager,
        onClearQuery = onClearQuery,
        onSearch = onSearch,
        search = search
    )

    if(isLoading){
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    } else{
        Spacer(modifier = Modifier.padding(vertical = 16.dp))
        if(!searchDetailVisible){
            SearchSuggestions(
                query = query,
                searchResult = searchSuggestions,
                onClick =  { id ->
                    onItemClick(id)
                }
            )
        } else{
            SearchDetail(
                query = query,
                searchResult = searchSuggestions,
                onClick = { id ->
                    onItemClick(id)
                }
            )
        }

    }


}