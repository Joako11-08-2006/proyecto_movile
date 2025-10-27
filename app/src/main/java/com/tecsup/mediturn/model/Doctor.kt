package com.tecsup.mediturn.model

data class Doctor(
    val id: Int,
    val name: String,
    val specialty: String,
    val photoUrl: String,
    val availableSlots: List<Slot>
)
