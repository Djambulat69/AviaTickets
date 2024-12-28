package com.isaev.aviatickets

import com.isaev.common.Network
import com.isaev.main.MainComponent
import com.isaev.tickets.TicketsComponent
import dagger.Component
import dagger.Module
import dagger.Provides

@Component(modules = [SubcomponentsModule::class, AppModule::class])
interface AppComponent {

    fun mainComponent(): MainComponent.Factory
    fun ticketsComponent(): TicketsComponent.Factory
}

@Module
class AppModule {
    @Provides
    fun provideNetwork(): Network = Network
}

@Module(subcomponents = [MainComponent::class])
class SubcomponentsModule