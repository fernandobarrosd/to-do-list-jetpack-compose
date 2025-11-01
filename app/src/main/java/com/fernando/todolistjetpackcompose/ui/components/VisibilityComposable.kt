package com.fernando.todolistjetpackcompose.ui.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun VisibilityComposable(isVisible: Boolean, composable: @Composable (() -> Unit)) {
    if (isVisible) {
        composable()
    }
}

@Preview
@Composable
private fun VisibilityComposablePreviewIsVisible() {
    VisibilityComposable(true) {
        Text("Text")
    }
}

@Preview
@Composable
private fun VisibilityComposablePreviewIsNotVisible() {
    VisibilityComposable(false) {
        Text("Text")
    }
}