package com.example.mvvmapp.di

import com.example.mvvmapp.network.RecipeService
import com.example.mvvmapp.network.model.RecipeDtoMapper
import com.example.mvvmapp.repository.RecipeRepository
import com.example.mvvmapp.repository.RecipeRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponentManager::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun provideRecipeRepository(
        recipeService: RecipeService,
        recipeDtoMapper: RecipeDtoMapper
    ): RecipeRepository {
        return RecipeRepository_Impl(recipeService, recipeDtoMapper)
    }
}