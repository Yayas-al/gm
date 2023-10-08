package com.dev.gm.domain.repo

import com.dev.gm.domain.model.Game
import com.dev.gm.domain.model.GameDetail
import com.dev.gm.util.Resource

interface GameRepo {

    suspend fun getAllGames() : Resource<List<Game>>

    suspend fun getGame(id: Int): Resource<GameDetail?>

    suspend fun getGamesByPlatform(platform : String): Resource<List<Game>>

    suspend fun sortGames(criteria: String): Resource<List<Game>>
}