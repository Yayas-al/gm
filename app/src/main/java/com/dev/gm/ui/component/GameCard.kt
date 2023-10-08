package com.dev.gm.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.dev.gm.domain.model.Game
import com.dev.gm.R
import com.dev.gm.ui.theme.RedErrorLight

@Composable
fun GameCard(
    game : Game,
    modifier: Modifier = Modifier,
    onClick : () -> Unit,
    ){
    Card (
        shape = MaterialTheme.shapes.small,
        modifier = modifier
            .padding(all = 8.dp)
            .clickable { onClick() },
        elevation = 8.dp,
        backgroundColor = MaterialTheme.colors.surface
    ){

        Column (
            verticalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
        ){
            NetworkImage(
                url = game.thumbnail,
                contentScale = ContentScale.Crop,
                crossFade = 1000,
                modifier = Modifier.fillMaxHeight(fraction = 0.5f),
                onLoading = {
                    ConstraintLayout (modifier = Modifier.fillMaxSize(fraction = 0.5f)){
                        val indicatorRef = createRef()
                        CircularProgressIndicator(
                            modifier = Modifier.constrainAs(indicatorRef){
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                        )
                    }
                },
                onError = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_warning),
                        contentDescription = "",
                        tint = RedErrorLight
                    )
                }
            )

            Column (
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(all = 3.dp),
                verticalArrangement = Arrangement.SpaceAround
            ){
               Text(
                   text = game.title,
                   modifier= Modifier.padding(all = 5.dp),
                   style = MaterialTheme.typography.body1,
                   overflow = TextOverflow.Ellipsis,
                   color= MaterialTheme.colors.onPrimary
               )
                Spacer(modifier = Modifier.padding(all = 5.dp))
                Box(modifier= Modifier
                    .fillMaxWidth(fraction = 0.95f)
                    .fillMaxHeight(fraction = 0.6f)){
                    Text(
                        text = game.shortDescription,
                        modifier = Modifier.fillMaxWidth(fraction = 0.85f),
                        style = MaterialTheme.typography.caption,
                        color = MaterialTheme.colors.onSurface,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp)
                ){

                    Chip (
                        backgroundColor = MaterialTheme.colors.primaryVariant,
                        borderWidth = 1.dp,
                    ){
                        Text(
                            text = game.genre,
                            modifier = Modifier.padding(all = 5.dp),
                            style = MaterialTheme.typography.caption,
                            color = Color.Black
                        )
                    }
                    Spacer(modifier = Modifier.padding(all = 3.dp))
                    Platform(
                        text = game.platform
                    )
                }
            }

        }
    }
}