package com.fernando.todolistjetpackcompose.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.fernando.todolistjetpackcompose.ui.theme.Purple600
import com.fernando.todolistjetpackcompose.ui.theme.Purple900
import com.fernando.todolistjetpackcompose.ui.theme.poppinsFontFamily


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(title: String, isVisibleNavigationIcon: Boolean, onNavigateBack: () -> Unit) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Purple600
        ),
        navigationIcon = {
            VisibilityComposable(isVisibleNavigationIcon) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Arrow back",
                    tint = Color.White,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 20.dp)
                        .clickable {
                            onNavigateBack
                        }
                )
            }
        },
        title = {
            Text(
                text = title,
                color = Color.White,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.W400
            )
        }
    )
}

@Preview
@Composable
private fun TopBarPreviewNavigationIconVisible() {
    TopBar("Title", true) {}
}

@Preview
@Composable
private fun TopBarPreviewNavigationIconNotVisible() {
    TopBar("Title", false) {}
}