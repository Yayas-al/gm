package com.dev.gm.domain.model

import com.dev.gm.data.remote.dto.MinimumSystemRequirementsDto
import com.dev.gm.data.remote.dto.ScreenshotDto

data class GameDetail(
    val description: String,
    val developer: String,
    val freeToGameProfileUrl: String,
    val gameUrl: String,
    val genre: String,
    val id: Int,
    val minimumSystemRequirementsDto: MinimumSystemRequirements?,
    val platform: String,
    val publisher: String,
    val releaseDate: String,
    val screenShots: List<Screenshots>,
    val shortDescription: String,
    val status: String,
    val thumbnail: String,
    val title: String
)