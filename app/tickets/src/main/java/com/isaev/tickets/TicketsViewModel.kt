package com.isaev.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.isaev.common.Network
import com.isaev.common.TicketOffer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TicketsViewModel : ViewModel() {

    private val _ticketsState = MutableStateFlow<List<TicketOffer>?>(null)
    val ticketsState: StateFlow<List<TicketOffer>?> = _ticketsState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val ticketOffers = Network.getFirstTickets().take(3)

                _ticketsState.update {
                    ticketOffers
                }
            } catch (e: Exception) {

            }
        }
    }

}