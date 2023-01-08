package com.gicproject.salamdoctorcvapp.di

import android.content.Context
import com.gicproject.salamdoctorcvapp.common.Constants
import com.gicproject.salamdoctorcvapp.data.remote.MyApi
import com.gicproject.salamdoctorcvapp.data.repository.DataStoreRepositoryImpl
import com.gicproject.salamdoctorcvapp.data.repository.MyRepositoryImpl
import com.gicproject.salamdoctorcvapp.domain.repository.DataStoreRepository
import com.gicproject.salamdoctorcvapp.domain.repository.MyRepository
import com.gicproject.salamdoctorcvapp.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun provideDataStoreRepository(
        @ApplicationContext app: Context
    ): DataStoreRepository = DataStoreRepositoryImpl(app)

    @Provides
    @Singleton
    fun provideMyApi(): MyApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }

    @Provides
    @Singleton
    fun provideMyRepository(api: MyApi): MyRepository {
        return MyRepositoryImpl(api = api)
    }

    @Provides
    @Singleton
    fun provideSurveyUseCases(
        repository: MyRepository,
        dataStoreRepository: DataStoreRepository
    ): MyUseCases {
        return MyUseCases(
            getDepartments = GetDepartments(repository = repository),
            getBranches = GetBranches(repository = repository),
            getCounters = GetCounters(repository = repository),
            getDoctor = GetDoctor(repository = repository),
            getTicket = GetTicket(repository = repository)
        )
    }
}