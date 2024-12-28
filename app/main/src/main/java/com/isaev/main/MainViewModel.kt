package com.isaev.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.isaev.common.Network
import com.isaev.common.Offer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel(private val network: Network) : ViewModel() {

    private val _musicAirLinesState: MutableStateFlow<List<Offer>?> = MutableStateFlow(null)

    val musicAirLinesState: StateFlow<List<Offer>?> = _musicAirLinesState.asStateFlow()

    init {
        viewModelScope.launch {
            try {
                withContext(Dispatchers.IO) {
                    val offers = network.getMain()

                    _musicAirLinesState.update { offers }
                }
            } catch (e: Exception) {

            }
        }
    }

    class Factory @Inject constructor(private val network: Network) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MainViewModel(network) as T
        }

    }

}