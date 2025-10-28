package com.tecsup.mediturn.model

data class Doctor(
    val id: Int,
    val name: String,
    val specialty: String,
    val city: String,
    val teleconsulta: Boolean,
    val photoUrl: String,
    val availableSlots: List<Slot>
)
