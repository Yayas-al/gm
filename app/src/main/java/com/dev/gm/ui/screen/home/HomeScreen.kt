package com.dev.gm.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dev.gm.R
import com.dev.gm.domain.model.Game
import com.dev.gm.ui.component.CarouselView
import com.dev.gm.ui.component.EmptyResult
import com.dev.gm.ui.component.GameCard
import com.dev.gm.ui.component.WarningMassage
import com.dev.gm.util.Resource
import com.dev.gm.util.getUrls
import com.dev.gm.util.header

@Composable
fun HomeScreen(
    availableGames: Resource<List<Game>>,
    onOpenDrawer: () -> Unit,
    onSearch: () -> Unit,
    onGameClick: (Int) -> Unit,
){
    
    availableGames.data?.let { games ->
        if(games.isEmpty()){
            WarningMassage(textId = R.string.empty_game)
        }else {
            val screenHeight = LocalContext.current.resources.displayMetrics.heightPixels.dp /
                    LocalDensity.current.density
            if (games.isEmpty()) {
                EmptyResult(textId = R.string.empty_game)
            } else {
                Column(modifier = Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(all = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        IconButton(onClick = onOpenDrawer) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_menu),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onBackground
                            )

                        }
                        IconButton(onClick = onSearch) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = "",
                                tint = MaterialTheme.colors.onBackground
                            )

                        }
                    }

                    LazyVerticalGrid(columns = GridCells.Fixed(count = 2)) {
                        header {
                            CarouselView(
                                modifier = Modifier
                                    .requiredHeight(height = 260.dp)
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp, horizontal = 12.dp),
                                urls = games.getUrls(),
                                shape = MaterialTheme.shapes.medium,
                                crossFade = 1000
                            )
                            Spacer(modifier = Modifier.height(30.dp))
                        }
                        items(items = games) { game ->
                            GameCard(
                                game = game,
                                modifier = Modifier
                                    .padding(all = 8.dp)
                                    .requiredHeight(height = screenHeight * 0.45f),
                                onClick = {onGameClick(game.id)}
                            )
                        }
                    }
                }
            }
        }
    }
}