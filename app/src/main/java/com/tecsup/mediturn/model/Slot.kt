package com.tecsup.mediturn.model

data class Slot(
    val id: Int,
    val date: String,
    val time: String,
    val isAvailable: Boolean
)