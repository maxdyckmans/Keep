package com.example.keep.domain

import android.net.Uri


sealed interface TimeStampElement{
    object currentElementId {
        private var counter = 1
        fun count(): Int = counter++
        fun getCurrentElementId(): Int {
            return counter
        }
    }
    val elementId: Int

    data class Text(val text: String = ""): TimeStampElement{
        override val elementId = currentElementId.getCurrentElementId()
        init{
            currentElementId.count()
        }
    }
    data class ImageList(val images: List<Uri> = emptyList()): TimeStampElement{
        override val elementId = currentElementId.getCurrentElementId()
        init{
            currentElementId.count()
        }
    }
}