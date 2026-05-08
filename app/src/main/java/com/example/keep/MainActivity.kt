package com.example.keep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.keep.presentation.todayScreen.TodayScreen
import com.example.keep.presentation.todayScreen.TodayScreenRoot
import com.example.keep.presentation.todayScreen.TodayScreenViewmodel
import com.example.keep.ui.theme.KeepTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            KeepTheme {
                TodayScreenRoot(
                    viewModel()
                )
            }
        }
    }
}

