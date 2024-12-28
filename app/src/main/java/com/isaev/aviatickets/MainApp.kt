package com.isaev.aviatickets

import android.app.Application
import com.isaev.main.MainComponent
import com.isaev.main.MainComponentProvider
import com.isaev.tickets.TicketsComponent
import com.isaev.tickets.TicketsComponentProvider


class MainApp : Application(), MainComponentProvider, TicketsComponentProvider {
    val appComponent: AppComponent = DaggerAppComponent.create()


    override fun provideMainComponent(): MainComponent {
        return appComponent.mainComponent().create()
    }

    override fun provideTicketsComponentComponent(): TicketsComponent {
        return appComponent.ticketsComponent().create()
    }
}