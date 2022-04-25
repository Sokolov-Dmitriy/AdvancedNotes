package com.sokolovds.domain.models

import kotlinx.coroutines.Dispatchers

class DispatchersProvider {
    val MAIN = Dispatchers.Main
    val DEFAULT = Dispatchers.Default
    val IO = Dispatchers.IO
}