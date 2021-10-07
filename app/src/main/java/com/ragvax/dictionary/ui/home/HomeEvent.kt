package com.ragvax.dictionary.ui.home

sealed class HomeEvent {
    data class NavigateToDefinition(val query: String) : HomeEvent()
    object ShowDeleteNotificationToast : HomeEvent()
}