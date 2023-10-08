package com.dev.gm.ui.screen.game

import android.icu.text.CaseMap.Title
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.gm.data.repo.GameRepoImpl
import com.dev.gm.domain.model.GameDetail
import com.dev.gm.util.GAME_ID_KEY
import com.dev.gm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val repository : GameRepoImpl,
    private val savedStateHandle: SavedStateHandle
): ViewModel(){

    private val _gameDetailState: MutableStateFlow<Resource<GameDetail?>> =
        MutableStateFlow(value =Resource.Loading())
    val gameDetailState: StateFlow<Resource<GameDetail?>>
        get() = _gameDetailState

    private val _gameTitleState = mutableStateOf<String>(value = "")
    val gameTitleState: State<String>
        get() =_gameTitleState

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(value = false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    init {
        savedStateHandle.get<String>(key = GAME_ID_KEY)?.let {id ->
            getGameDetail(id = id.toInt())
        }
    }
    private fun getGameDetail(id: Int){
        viewModelScope.launch {
            _isLoading.value = true
            _gameDetailState.value = repository.getGame(id = id)
            _isLoading.value = false
        }
    }

    fun setGameTitle(title: String){
        _gameTitleState.value = title
    }
}