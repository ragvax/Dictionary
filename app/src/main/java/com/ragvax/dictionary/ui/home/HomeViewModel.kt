package com.ragvax.dictionary.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragvax.dictionary.data.local.RecentQuery
import com.ragvax.dictionary.repository.RecentQueryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recentQueryRepository: RecentQueryRepository,
) : ViewModel() {

    private val homeEventChannel = Channel<HomeEvent>(Channel.CONFLATED)
    val homeEvent = homeEventChannel.receiveAsFlow()

    fun onButtonClick(query: String) = viewModelScope.launch {
        homeEventChannel.send(HomeEvent.NavigateToDefinition(query))
    }

    fun onQuerySwiped(query: RecentQuery) = viewModelScope.launch {
        recentQueryRepository.deleteRecentQuery(query)
        homeEventChannel.send(HomeEvent.ShowDeleteNotificationToast)
    }

    val recentQueries = recentQueryRepository.getRecentQueries()
}

sealed class HomeEvent {
    data class NavigateToDefinition(val query: String) : HomeEvent()
    object ShowDeleteNotificationToast : HomeEvent()
}