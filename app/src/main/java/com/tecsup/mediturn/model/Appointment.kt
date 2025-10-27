package com.tecsup.mediturn.model

data class Appointment(
    val id: Int,
    val doctor: Doctor,
    val date: String,
    val time: String,
    val reason: String
)