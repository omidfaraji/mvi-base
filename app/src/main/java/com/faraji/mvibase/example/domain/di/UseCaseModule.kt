package com.faraji.mvibase.example.domain.di

import com.faraji.mvibase.example.domain.usecase.GetFullImageUseCase
import com.faraji.mvibase.example.domain.usecase.GetFullImageUseCaseImpl
import com.faraji.mvibase.example.domain.usecase.GetThumbsUseCase
import com.faraji.mvibase.example.domain.usecase.GetThumbsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {

    @Binds
    fun bindGetThumbsUseCaseImpl(getThumbsUseCaseImpl: GetThumbsUseCaseImpl): GetThumbsUseCase

    @Binds
    fun bindGetFullImageUseCaseImpl(getFullImageUseCaseImpl: GetFullImageUseCaseImpl): GetFullImageUseCase

}