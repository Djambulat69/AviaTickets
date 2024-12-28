package com.isaev.alltickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaev.common.Network
import com.isaev.common.Ticket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class AllTicketsViewModel @Inject constructor(
    private val network: Network
) : ViewModel() {


    private val _ticketsState = MutableStateFlow<List<Ticket>?>(null)
    val ticketsState: StateFlow<List<Ticket>?> = _ticketsState.asStateFlow()

    init {
        try {
            viewModelScope.launch {
                _ticketsState.update { network.getAllTickets() }
            }
        } catch (e: Exception) {

        }
    }

}