package com.tecsup.mediturn.data

import com.tecsup.mediturn.model.*

object FakeRepository {

    val doctors = listOf(
        Doctor(
            id = 1,
            name = "Dr. Luis Ramírez",
            specialty = "Cardiología",
            city = "Lima",
            teleconsulta = true,
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
            city = "Arequipa",
            teleconsulta = false,
            photoUrl = "https://randomuser.me/api/portraits/women/44.jpg",
            availableSlots = listOf(
                Slot(3, "2025-10-27", "09:00 AM", true),
                Slot(4, "2025-10-27", "10:30 AM", true)
            )
        )
    )

    private val appointments = mutableListOf<Appointment>()

    fun getAllDoctors(): List<Doctor> = doctors

    fun searchDoctors(query: String): List<Doctor> {
        val q = query.lowercase()
        return doctors.filter {
            it.name.lowercase().contains(q) ||
                    it.specialty.lowercase().contains(q) ||
                    it.city.lowercase().contains(q) ||
                    (it.teleconsulta && q.contains("teleconsulta"))
        }
    }

    fun addAppointment(appointment: Appointment) {
        appointments.add(appointment)
    }

    fun getAppointments(): List<Appointment> = appointments

    fun cancelAppointment(id: Int) {
        appointments.removeAll { it.id == id }
    }

    fun updateAppointment(id: Int, newDate: String, newTime: String) {
        val index = appointments.indexOfFirst { it.id == id }
        if (index != -1) {
            val current = appointments[index]
            appointments[index] = current.copy(
                slot = current.slot.copy(date = newDate, time = newTime)
            )
        }
    }
}
