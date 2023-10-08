package com.dev.gm.data.remote.dto

import com.dev.gm.domain.model.Screenshots

data class ScreenshotDto(
    val id: Int,
    val image: String
){
    fun toScreenshot(): Screenshots{
        return Screenshots(
           id,
            image
        )
    }
}