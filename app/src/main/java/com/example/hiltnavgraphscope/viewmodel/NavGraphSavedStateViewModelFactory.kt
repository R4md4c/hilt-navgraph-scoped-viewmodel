package com.example.hiltnavgraphscope.viewmodel

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class NavGraphSavedStateViewModelFactory @Inject constructor(
    savedStateOwner: SavedStateRegistryOwner,
    bundle: Bundle?
) : AbstractSavedStateViewModelFactory(savedStateOwner, bundle) {

    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = NavGraphViewModel(handle) as T // Should be replaced with the Factory using AssistedInject.
}
