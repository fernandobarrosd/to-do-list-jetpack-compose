package com.fernando.todolistjetpackcompose.ui.screens.tasksScreen

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val tasksModule = module {
    viewModelOf(::TasksViewModel)
}