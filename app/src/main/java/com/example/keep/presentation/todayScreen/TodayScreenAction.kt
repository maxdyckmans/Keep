package com.example.keep.presentation.todayScreen

import android.net.Uri

sealed interface TodayScreenAction {
    data class OnImageOfTheDayPicked(val uri: Uri?): TodayScreenAction
    data class OnTimeStampDeleted(val timeStampId: Int): TodayScreenAction
    data class OnTextFieldEdited(val timeStampId: Int, val textFieldId: Int, val text: String): TodayScreenAction
    object OnTitleDone: TodayScreenAction
    object OnEmptySpaceClicked: TodayScreenAction
}