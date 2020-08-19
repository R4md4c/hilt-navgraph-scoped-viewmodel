package com.example.hiltnavgraphscope.utils

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.hiltnavgraphscope.R
import com.example.hiltnavgraphscope.di.NavGraphComponentBuilder
import com.example.hiltnavgraphscope.di.NavGraphEntryPoint
import dagger.hilt.EntryPoints

inline fun <reified VM : ViewModel> ViewModelStoreOwner.exampleNavGraphViewModels(
    navGraphId: Int,
    crossinline componentBuilder: () -> NavGraphComponentBuilder
): Lazy<VM> {
    val backStackEntry by lazy {
        val navController = when (this) {
            is ComponentActivity -> {
                findNavController(R.id.nav_host_fragment)
            }
            is Fragment -> {
                findNavController()
            }
            else -> {
                throw IllegalArgumentException("ViewModelStoreOwner must be either Fragment or ComponentActivity")
            }
        }
        navController.getBackStackEntry(navGraphId)
    }

    return ViewModelLazy(
        VM::class,
        storeProducer = { backStackEntry.viewModelStore },
        factoryProducer = {
            val component = componentBuilder.invoke()
                .bindsArguments(backStackEntry.arguments)
                .bindsSavedStateOwner(backStackEntry)
                .build()
            EntryPoints.get(component, NavGraphEntryPoint::class.java).viewModelFactory
        })
}
