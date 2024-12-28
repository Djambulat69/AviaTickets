package com.isaev.tickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isaev.common.Network
import com.isaev.common.TicketOffer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketsViewModel(private val network: Network) : ViewModel() {

    private val _ticketsState = MutableStateFlow<List<TicketOffer>?>(null)
    val ticketsState: StateFlow<List<TicketOffer>?> = _ticketsState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                val ticketOffers = network.getFirstTickets().take(3)

                _ticketsState.update {
                    ticketOffers
                }
            } catch (e: Exception) {

            }
        }
    }

    class Factory @Inject constructor(private val network: Network) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TicketsViewModel(network) as T
        }
    }

}