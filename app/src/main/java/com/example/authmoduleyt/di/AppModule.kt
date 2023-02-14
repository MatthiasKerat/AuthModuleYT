package com.example.authmoduleyt.di

import com.example.authmoduleyt.data.AuthRepositoryImpl
import com.example.authmoduleyt.domain.repository.AuthRepository
import com.example.authmoduleyt.domain.use_case.ValidateLoginInputUseCase
import com.example.authmoduleyt.domain.use_case.ValidateRegisterInputUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideValidateLoginInputUseCase():ValidateLoginInputUseCase{
        return ValidateLoginInputUseCase()
    }

    @Provides
    @Singleton
    fun provideValidateRegisterInputUseCase():ValidateRegisterInputUseCase{
        return ValidateRegisterInputUseCase()
    }

    @Provides
    @Singleton
    fun provideAuthRepository():AuthRepository{
        return AuthRepositoryImpl()
    }

}