package com.gicproject.salamdoctorcvapp.domain.use_case


import com.gicproject.salamdoctorcvapp.common.Resource
import com.gicproject.salamdoctorcvapp.domain.model.Doctor
import com.gicproject.salamdoctorcvapp.domain.model.Ticket
import com.gicproject.salamdoctorcvapp.domain.repository.MyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetDoctor @Inject constructor(
    private val repository: MyRepository
) {
    operator fun invoke(
        branchId: String,
        counterId: String
    ): Flow<Resource<Doctor>> = flow {
        try {
            emit(Resource.Loading())

            var doctors = repository.getDoctorDetails(branchId, counterId)
            if (!doctors.isNullOrEmpty()) {
                emit(Resource.Success(doctors[0].toDoctor()))
            } else {
                emit(Resource.Error("Empty Doctor List."))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }
}