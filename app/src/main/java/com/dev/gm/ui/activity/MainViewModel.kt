package com.dev.gm.ui.activity

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.gm.data.repo.GameRepoImpl
import com.dev.gm.domain.model.Game
import com.dev.gm.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repoImpl: GameRepoImpl
): ViewModel(){

    private val _splashScreenVisible = mutableStateOf(value = false)
    val splashScreenVisible : State<Boolean>
        get() = _splashScreenVisible

    private val _games: MutableStateFlow<Resource<List<Game>>> =
        MutableStateFlow(value = Resource.Loading())
    val games : StateFlow<Resource<List<Game>>>
        get() = _games

    init {
        viewModelScope.launch {
            _splashScreenVisible.value = true
            _games.value = repoImpl.getAllGames()
            _splashScreenVisible.value = false
        }
    }
}