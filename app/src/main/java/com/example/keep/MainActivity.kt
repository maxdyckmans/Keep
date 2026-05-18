package com.example.keep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.keep.data.KeepDataBase
import com.example.keep.presentation.todayScreen.TodayScreen
import com.example.keep.presentation.todayScreen.TodayScreenRoot
import com.example.keep.presentation.todayScreen.TodayScreenViewmodel
import com.example.keep.ui.theme.KeepTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val db = Room.databaseBuilder(
                applicationContext,
                KeepDataBase::class.java,
                "KeepDB"
            )
            KeepTheme {
                TodayScreenRoot(
                    viewModel()
                )
            }
        }
    }
}

