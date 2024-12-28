package com.isaev.tickets

import dagger.Subcomponent

@Subcomponent
interface TicketsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): TicketsComponent
    }

    fun inject(fragment: TicketsFragment)
}

interface TicketsComponentProvider {
    fun provideTicketsComponentComponent(): TicketsComponent
}