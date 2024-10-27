package com.roxx.grigarage.presentation.screens.tracker.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.roxx.grigarage.domain.use_cases.another.ConvertBase64ToImageBitmapUseCase
import com.roxx.grigarage.domain.use_cases.another.ConvertBitmapToBase64UseCase
import com.roxx.grigarage.domain.use_cases.beers.SearchBeersUseCase
import com.roxx.grigarage.domain.use_cases.beers.UpdateBeerUseCase
import com.roxx.grigarage.presentation.navigation.Route
import com.roxx.grigarage.presentation.screens.tracker.BeerUiModel
import com.roxx.grigarage.presentation.util.UiEvent
import com.roxx.grigarage.presentation.util.toBeer
import com.roxx.grigarage.presentation.util.toBeerUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchBeersUseCase: SearchBeersUseCase,
    private val updateBeerUseCase: UpdateBeerUseCase,
    private val baseToBitmap: ConvertBase64ToImageBitmapUseCase,
    private val imageToString: ConvertBitmapToBase64UseCase
) : ViewModel() {
    var state by mutableStateOf(SearchState())
        private set

    private val _beers = MutableStateFlow<PagingData<BeerUiModel>>(PagingData.empty())
    val beers: StateFlow<PagingData<BeerUiModel>> = _beers

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnQueryChange -> {
                state = state.copy(query = event.query)
            }

            is SearchEvent.OnSearch -> {
                executeSearch()
            }

            is SearchEvent.OnSearchFocusChange -> {
                state = state.copy(
                    isHintVisible = !event.isFocused && state.query.isBlank()
                )
            }

            is SearchEvent.OnBeerClick -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Navigate(Route.DETAIL))
                }
            }

            is SearchEvent.OnBeerLiked -> {
                viewModelScope.launch {
                    updateBeerUseCase(event.beer.toBeer(imageToString(event.beer.photoUri.asAndroidBitmap())))
                }
            }
        }
    }

    private fun executeSearch() {
        viewModelScope.launch {
            state = state.copy(
                isSearching = true
            )
            _beers.value = PagingData.empty()
            searchBeersUseCase
                .invoke(state.query)
                .cachedIn(viewModelScope)
                .collectLatest { pagingData ->
                    _beers.value = pagingData.map { beer ->
                        beer.toBeerUiModel(baseToBitmap(beer.photoUri))
                    }
                }.apply {
                    state = state.copy(
                        isSearching = false,
                        query = ""
                    )
                }
        }
    }
}