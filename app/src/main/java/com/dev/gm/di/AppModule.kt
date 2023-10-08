package com.dev.gm.di

import android.content.Context
import com.dev.gm.application.FreeGameApplication
import com.dev.gm.data.remote.api.Api
import com.dev.gm.data.remote.api.ApiBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providerFreeGameApplication(
        @ApplicationContext app: Context
    ): FreeGameApplication{
        return app as FreeGameApplication
    }


    @Singleton
    @Provides
    fun providerApi(apiBuilder: ApiBuilder) : Api {
        return  apiBuilder.builder(Api::class.java)
    }
}