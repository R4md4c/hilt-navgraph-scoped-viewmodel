package com.example.hiltnavgraphscope.viewmodel

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

class NavGraphViewModel @ViewModelInject constructor(@Assisted val savedStateHandle: SavedStateHandle) :
    ViewModel()
