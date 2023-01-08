package com.gicproject.salamdoctorcvapp.domain.model

import com.gicproject.salamdoctorcvapp.domain.model.Branch
import com.google.gson.annotations.SerializedName

data class Ticket(
    var TicketNumber: String? = null,
    var Status: String? = null
)
