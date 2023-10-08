package com.dev.gm.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.dev.gm.R

@Composable
fun WarningMassage(
    @StringRes textId: Int,
    extraText: String =""
){
Column(
    modifier = Modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
){
    Icon(
        painter = painterResource(id = R.drawable.ic_circle_info_solid),
        tint = MaterialTheme.colors.onSurface,
        contentDescription = "",
        
    )
    Spacer(modifier = Modifier.padding(all = 8.dp))

    Text(
        text = buildAnnotatedString {
            append(stringResource(id =textId ))
            withStyle(SpanStyle(fontWeight = FontWeight.Bold)){
                append(extraText)
            }
        },
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle2
    )
}
}