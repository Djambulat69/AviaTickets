package com.isaev.alltickets

import dagger.Subcomponent


@Subcomponent
interface AllTicketsComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): AllTicketsComponent
    }

    fun inject(fragment: AllTicketsFragment)
}

interface AllTicketsComponentProvider {
    fun provideAllTicketsComponent(): AllTicketsComponent
}

