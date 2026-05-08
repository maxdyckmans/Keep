package com.example.keep.presentation.todayScreen

import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.example.keep.domain.TimeStamp
import com.example.keep.domain.TimeStampElement
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.time.Instant
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class TodayScreenState(
    val dayId: Int = 0,
    val title: String = "",
    val imageOfTheDay: Uri? = null,
    val timeStamps: List<TimeStamp> = emptyList()
)

class TodayScreenViewmodel(): ViewModel() {
    init {
        Log.d("Debug", "ViewModel created")
    }
    var currentTimestampId = 1
    private val _uiState = MutableStateFlow(TodayScreenState())
    val uiState = _uiState.asStateFlow()

    fun onAction(action: TodayScreenAction){
        when(action){
            is TodayScreenAction.OnImageOfTheDayPicked -> {
                if (action.uri != null){
                    _uiState.update {
                            todayScreenState -> todayScreenState.copy(imageOfTheDay = action.uri)
                    }
                    Log.d("Debug", "Image of the Day Updated: ${action.uri}")
                }

            }
            is TodayScreenAction.OnEmptySpaceClicked -> {
                _uiState.update {
                    it.copy(
                        timeStamps = it.timeStamps + TimeStamp(
                            timeStampId = currentTimestampId,
                            time = Instant.now(),
                            timeStampElements = listOf(TimeStampElement.Text())
                        )
                    )
                }
                currentTimestampId++
            }

            is TodayScreenAction.OnTitleDone -> {
                if (_uiState.value.timeStamps.isEmpty()){
                    _uiState.update {
                        it.copy(
                            timeStamps = it.timeStamps + TimeStamp(
                                timeStampId = 0,
                                time = Instant.now(),
                                timeStampElements = listOf(TimeStampElement.Text())
                            )
                        )
                    }
                }
                currentTimestampId++
            }
            is TodayScreenAction.OnTimeStampDeleted -> {
                _uiState.update {
                    it.copy(
                        timeStamps = it.timeStamps.filter {timeStamp ->
                            timeStamp.timeStampId != action.timeStampId
                        }
                    )
                }
            }
            is TodayScreenAction.OnTextFieldEdited -> {
                _uiState.update {
                    it.copy(
                        timeStamps = it.timeStamps.map{
                            timeStamp ->
                            if (timeStamp.timeStampId == action.timeStampId){
                                timeStamp.copy(
                                    timeStampElements = timeStamp.timeStampElements.map{
                                        element ->
                                        if(element.elementId == action.textFieldId){
                                            TimeStampElement.Text(action.text)
                                        }
                                        else{
                                            element
                                        }
                                    }
                                )
                            }
                            else{
                                timeStamp
                            }
                        }
                    )
                }
            }

        }
    }
}