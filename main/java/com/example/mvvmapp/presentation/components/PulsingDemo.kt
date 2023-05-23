package com.example.jetpackcompose.presentation.components

import androidx.compose.animation.core.updateTransition
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

object PulsingAnimationDefinitions {

    enum class PulseState {
        INITIAL,
        FINAL
    }
}