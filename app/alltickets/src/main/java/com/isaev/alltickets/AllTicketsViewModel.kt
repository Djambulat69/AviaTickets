package com.isaev.alltickets

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.viewModelFactory
import com.isaev.common.Ticket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class AllTicketsViewModel : ViewModel() {


    private val _ticketsState = MutableStateFlow<List<Ticket>?>(null)
    val ticketsState: StateFlow<List<Ticket>?> = _ticketsState.asStateFlow()



}