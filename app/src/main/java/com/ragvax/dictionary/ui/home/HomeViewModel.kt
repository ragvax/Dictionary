package com.ragvax.dictionary.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ragvax.dictionary.data.source.local.RecentQuery
import com.ragvax.dictionary.domain.usecase.DeleteRecentQuery
import com.ragvax.dictionary.domain.usecase.GetRecentQueries
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getRecentQueries: GetRecentQueries,
    private val deleteRecentQuery: DeleteRecentQuery,
) : ViewModel() {

    private val homeEventChannel = Channel<HomeEvent>(Channel.CONFLATED)
    val homeEvent = homeEventChannel.receiveAsFlow()

    fun onButtonClick(query: String) = viewModelScope.launch {
        homeEventChannel.send(HomeEvent.NavigateToDefinition(query))
    }

    fun onQuerySwiped(query: RecentQuery) = viewModelScope.launch {
        deleteRecentQuery(query)
        homeEventChannel.send(HomeEvent.ShowDeleteNotificationToast)
    }

    val recentQueries = getRecentQueries()
}