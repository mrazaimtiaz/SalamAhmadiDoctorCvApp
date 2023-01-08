package com.gicproject.salamdoctorcvapp.data.repository


import com.gicproject.salamdoctorcvapp.data.remote.MyApi
import com.gicproject.salamdoctorcvapp.data.remote.dto.*
import com.gicproject.salamdoctorcvapp.domain.repository.MyRepository
import javax.inject.Inject

class MyRepositoryImpl @Inject constructor(
    private val api: MyApi
): MyRepository {

    override suspend fun getLastTicket(branchId: String, counterId: String): List<TicketDto>? {
        return  api.getLastTicket(branchId,counterId)
    }

    override suspend fun getDoctorDetails(branchId: String, counterId: String): List<DoctorDto>? {
        return  api.getDoctorDetails(branchId,counterId)
    }

    override suspend fun getDepartments(branchId: String): List<DepartmentDto>? {
        return  api.getDepartments(branchId)
    }

    override suspend fun getCounters(branchId: String): List<CounterDto>? {
        return  api.getCounters(branchId)
    }

    override suspend fun getBranches(): List<BranchDto>? {
        return  api.getBranches()
    }
}