package com.example.hiltnavgraphscope.viewmodel

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.hilt.lifecycle.ViewModelAssistedFactory
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class NavGraphSavedStateViewModelFactory @Inject constructor(
    private val viewModelFactories: Map<@JvmSuppressWildcards String, @JvmSuppressWildcards Provider<ViewModelAssistedFactory<out ViewModel>>>,
    savedStateOwner: SavedStateRegistryOwner,
    bundle: Bundle?
) : AbstractSavedStateViewModelFactory(savedStateOwner, bundle) {

    @SuppressLint("RestrictedApi")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T = viewModelFactories[modelClass.name]!!.get().create(handle) as T
}
