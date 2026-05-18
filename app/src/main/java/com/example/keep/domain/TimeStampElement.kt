package com.example.keep.domain




interface TimeStampElement{
    val elementId: Int

    data class Text(
        override val elementId: Int,
        val text: String = ""
    ): TimeStampElement

    data class ImageList(
        override val elementId: Int,
        val images: List<String> = emptyList()

    ): TimeStampElement
}