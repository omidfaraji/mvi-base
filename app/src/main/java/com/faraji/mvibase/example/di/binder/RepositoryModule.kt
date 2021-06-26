package com.faraji.mvibase.example.di.binder

import com.faraji.mvibase.example.data.repository.CatRepositoryImpl
import com.faraji.mvibase.example.domain.repository.CatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface RepositoryModule {

    @Binds
    fun bindCatRepositoryImpl(catRepositoryImpl: CatRepositoryImpl): CatRepository

}