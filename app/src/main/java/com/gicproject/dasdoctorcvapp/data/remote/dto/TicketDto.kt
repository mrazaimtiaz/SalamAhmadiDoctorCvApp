package com.gicproject.salamdoctorcvapp.data.remote.dto

import com.gicproject.salamdoctorcvapp.domain.model.Branch
import com.gicproject.salamdoctorcvapp.domain.model.Ticket
import com.google.gson.annotations.SerializedName

data class TicketDto(
    @SerializedName("Queue_BookedNo") var TicketNumber: String? = null,
    @SerializedName("Status") var Status: String? = null,
){
    fun toTicket(): Ticket {
        return Ticket(
            TicketNumber = TicketNumber.toString(),
            Status = Status
        )
    }
}

