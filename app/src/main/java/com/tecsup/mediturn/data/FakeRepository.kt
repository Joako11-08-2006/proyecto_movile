package com.tecsup.mediturn.data

import com.tecsup.mediturn.model.*

object FakeRepository {

    // 👨‍⚕️ Lista de doctores simulada
    val doctors = listOf(
        Doctor(
            id = 1,
            name = "Dr. Juan Pérez",
            specialty = "Cardiología",
            city = "Lima",
            teleconsulta = true,
            photoUrl = "https://randomuser.me/api/portraits/men/32.jpg",
            availableSlots = listOf(
                Slot(id = 1, date = "2025-10-28", time = "10:00 AM", isAvailable = true),
                Slot(id = 2, date = "2025-10-30", time = "02:00 PM", isAvailable = true)
            )
        ),
        Doctor(
            id = 2,
            name = "Dra. María López",
            specialty = "Dermatología",
            city = "Cusco",
            teleconsulta = false,
            photoUrl = "https://randomuser.me/api/portraits/women/44.jpg",
            availableSlots = listOf(
                Slot(id = 3, date = "2025-10-29", time = "09:30 AM", isAvailable = true)
            )
        )
    )

    // 🩺 Citas simuladas
    private val appointments = mutableListOf(
        Appointment(
            id = 1,
            doctor = doctors.first(),
            slot = Slot(
                id = 1,
                date = "2025-10-30",
                time = "09:00 AM",
                isAvailable = true
            ),
            reason = "Chequeo general"
        )
    )

    // ==========================
    // 🔍 FUNCIONES DE BÚSQUEDA
    // ==========================
    fun getAllDoctors(): List<Doctor> = doctors

    fun searchDoctors(query: String): List<Doctor> {
        val lowerQuery = query.lowercase()
        return doctors.filter {
            it.name.lowercase().contains(lowerQuery) ||
                    it.specialty.lowercase().contains(lowerQuery) ||
                    it.city.lowercase().contains(lowerQuery)
        }
    }

    // ==========================
    // 🗓️ FUNCIONES DE CITAS
    // ==========================
    fun addAppointment(appointment: Appointment) {
        appointments.add(appointment)
    }

    fun getAppointments(): List<Appointment> = appointments.toList()

    fun cancelAppointment(appointmentId: Int) {
        val cita = appointments.find { it.id == appointmentId }
        cita?.let { appointments.remove(it) }
    }

    fun updateAppointment(id: Int, newDate: String, newTime: String) {
        val index = appointments.indexOfFirst { it.id == id }
        if (index != -1) {
            val oldAppointment = appointments[index]
            appointments[index] = oldAppointment.copy(
                slot = oldAppointment.slot.copy(
                    date = newDate,
                    time = newTime
                )
            )
        }
    }
}
