package com.dev.gm.ui.component.drawer


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp


@Composable
fun NavigationDrawerItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconPainter: Painter,
    contentDescription: String = "",
    iconColor: Color,
    textStyle: TextStyle,
    text: String,
    textColor:Color,
    fontWeight: FontWeight = FontWeight.Bold
){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().clickable { onClick() }
    ){

        Icon(
            modifier = Modifier.padding(end = 3.dp),
            painter = iconPainter,
            contentDescription = contentDescription,
            tint = iconColor
        )
        Text(
            text = text,
            style = textStyle,
            color = textColor,
            fontWeight = fontWeight,
        )


    }

}