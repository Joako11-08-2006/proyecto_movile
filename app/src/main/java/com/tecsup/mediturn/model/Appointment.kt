package com.tecsup.mediturn.model

data class Appointment(
    val id: Int,
    val doctor: Doctor,
    val patient: Patient,
    val slot: Slot,
    val reason: String
)
