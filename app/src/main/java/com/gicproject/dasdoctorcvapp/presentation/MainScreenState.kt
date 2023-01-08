package com.gicproject.salamdoctorcvapp.presentation

import com.gicproject.salamdoctorcvapp.domain.model.*

data class MainScreenState(
    val isLoading: Boolean = false,
    val ticket: Ticket = Ticket(TicketNumber = ""),
    val doctorDetail: Doctor = Doctor(),
    val department: Department = Department(),
    val error: String = ""
)
