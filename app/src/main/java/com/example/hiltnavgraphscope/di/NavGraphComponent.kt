package com.example.hiltnavgraphscope.di

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.savedstate.SavedStateRegistryOwner
import com.example.hiltnavgraphscope.viewmodel.NavGraphSavedStateViewModelFactory
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.hilt.DefineComponent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@DefineComponent(parent = ActivityRetainedComponent::class)
interface NavGraphComponent

@DefineComponent.Builder
interface NavGraphComponentBuilder {
    fun bindsArguments(@BindsInstance arguments: Bundle?): NavGraphComponentBuilder
    fun bindsSavedStateOwner(@BindsInstance owner: SavedStateRegistryOwner): NavGraphComponentBuilder
    fun build(): NavGraphComponent
}

@InstallIn(NavGraphComponent::class)
@Module
abstract class NavGraphModule {
    @Binds
    abstract fun bindsSavedStateFactory(
        it: NavGraphSavedStateViewModelFactory
    ): AbstractSavedStateViewModelFactory
}

@EntryPoint
@InstallIn(NavGraphComponent::class)
interface NavGraphEntryPoint {
    val viewModelFactory: AbstractSavedStateViewModelFactory
}
