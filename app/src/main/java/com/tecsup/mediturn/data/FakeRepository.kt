package com.tecsup.mediturn.data

import com.tecsup.mediturn.model.*

object FakeRepository {

    val doctors = listOf(
        Doctor(
            id = 1,
            name = "Dr. Luis Ramírez",
            specialty = "Cardiología",
            photoUrl = "https://randomuser.me/api/portraits/men/32.jpg",
            availableSlots = listOf(
                Slot(1, "2025-10-26", "10:00 AM", true),
                Slot(2, "2025-10-26", "11:00 AM", true)
            )
        ),
        Doctor(
            id = 2,
            name = "Dra. Ana López",
            specialty = "Dermatología",
            photoUrl = "https://randomuser.me/api/portraits/women/44.jpg",
            availableSlots = listOf(
                Slot(3, "2025-10-27", "09:00 AM", true),
                Slot(4, "2025-10-27", "10:30 AM", true)
            )
        )
    )

    fun getDoctorsBySpecialty(specialty: String): List<Doctor> {
        return doctors.filter { it.specialty.contains(specialty, ignoreCase = true) }
    }

    fun getDoctorByName(name: String): List<Doctor> {
        return doctors.filter { it.name.contains(name, ignoreCase = true) }
    }
}