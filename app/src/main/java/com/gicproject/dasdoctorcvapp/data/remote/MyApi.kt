package com.gicproject.salamdoctorcvapp.data.remote


import com.gicproject.salamdoctorcvapp.data.remote.dto.*
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {
    @GET("api/LastTicket")
    suspend fun getLastTicket(
        @Query("branchid")
        branchId: String,
        @Query("counterid")
        counterId: String,
    ): List<TicketDto>?

    @GET("api/doctordetails")
    suspend fun getDoctorDetails(
        @Query("branchid")
        branchId: String,
        @Query("counterid")
        counterId: String,
    ): List<DoctorDto>?

    @GET("api/departments")
    suspend fun getDepartments(
        @Query("branchid")
        branchId: String,
    ): List<DepartmentDto>?

    @GET("api/Counters")
    suspend fun getCounters(
        @Query("branchid")
        branchId: String,
    ): List<CounterDto>?

    @GET("api/Branches")
    suspend fun getBranches(
    ): List<BranchDto>?


}