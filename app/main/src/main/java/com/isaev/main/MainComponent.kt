package com.isaev.main

import dagger.Subcomponent

@Subcomponent
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(fragment: MainFragment)
}

interface MainComponentProvider {
    fun provideMainComponent(): MainComponent
}