package com.example.keep.presentation.components

import androidx.compose.material.icons.rounded.CalendarMonth
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.keep.ui.theme.KeepTheme
import com.example.keep.ui.theme.MyAppIcons

@Composable
fun KeepBottomBar(modifier: Modifier = Modifier) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background
    ){
        NavigationBarItem(
            selected = true,
            icon = {
                Icon(
                    MyAppIcons.CalendarMonth,
                    contentDescription = null
                )
            },
            label = { Text(text = "Calendar") },
            onClick = {}
        )
        NavigationBarItem(
            selected = false,
            icon = {
                Icon(
                    MyAppIcons.LightMode,
                    contentDescription = null
                )
            },
            label = { Text(text = "Today") },
            onClick = {}
        )
        NavigationBarItem(
            selected = false,
            icon = {
                Icon(
                    MyAppIcons.Settings,
                    contentDescription = null
                )
            },
            label = { Text(text = "Settings") },
            onClick = {}
        )
    }
}

@Preview
@Composable
fun PreviewKeepBottomBar(modifier: Modifier = Modifier) {
    KeepTheme {
        KeepBottomBar()
    }
}