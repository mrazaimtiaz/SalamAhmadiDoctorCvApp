package com.gicproject.salamdoctorcvapp.domain.repository

import com.gicproject.salamdoctorcvapp.data.remote.dto.*

interface MyRepository {

    suspend fun getLastTicket(
        branchId: String,
        counterId: String,
    ): List<TicketDto>?

    suspend fun getDoctorDetails(
        branchId: String,
        counterId: String,
    ): List<DoctorDto>?

    suspend fun getDepartments(
        branchId: String,
    ): List<DepartmentDto>?

    suspend fun getCounters(
        branchId: String,
    ): List<CounterDto>?

    suspend fun getBranches(
    ): List<BranchDto>?

}



