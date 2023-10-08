package com.dev.gm.ui.component

import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.dev.gm.R

@Composable
fun Platform(
    text: String,
    iconColor : Color = MaterialTheme.colors.primaryVariant) {

    val resource = if (text.contains("windows", ignoreCase = true)) {
        R.drawable.ic_windows_brands
    } else {
        R.drawable.ic_window_maximize_solid
    }

    Icon(
        painter = painterResource(id = resource),
        contentDescription = "",
        tint = MaterialTheme.colors.primaryVariant
    )
}