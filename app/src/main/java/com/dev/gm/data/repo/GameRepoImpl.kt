package com.dev.gm.data.repo

import com.dev.gm.data.remote.api.Api
import com.dev.gm.domain.model.Game
import com.dev.gm.domain.model.GameDetail
import com.dev.gm.domain.repo.GameRepo
import com.dev.gm.util.Resource
import javax.inject.Inject

class GameRepoImpl @Inject constructor(
    private val api : Api
): BaseRepo(), GameRepo {
    override suspend fun getAllGames(): Resource<List<Game>> {
        val response = invokeApi {
            api.getAllGames()
        }
        return when(response){
            is Resource.Error -> Resource.Error(error = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.map { it.theGame() }?: emptyList()
            )
        }
    }

    override suspend fun getGame(id: Int): Resource<GameDetail?> {
        val response = invokeApi {
            api.getGame(id = id)
        }
        return  when(response){
            is Resource.Error -> Resource.Error(error = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.toGameDetail()
            )
        }
    }

    override suspend fun getGamesByPlatform(platform: String): Resource<List<Game>> {
        val response = invokeApi {
            api.getGamesByPlatform(platform = platform)
        }
        return  when(response){
            is Resource.Error -> Resource.Error(error = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.map{it.theGame()}?: emptyList()
            )
        }
    }

    override suspend fun sortGames(criteria: String): Resource<List<Game>> {
        val response = invokeApi {
            api.sortGames(criteria = criteria)
        }
        return  when(response){
            is Resource.Error -> Resource.Error(error = response.error)
            is Resource.Loading -> Resource.Loading()
            is Resource.Success -> Resource.Success(
                data = response.data?.map{it.theGame()}?: emptyList()
            )
        }
    }
}