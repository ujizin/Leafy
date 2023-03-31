package com.ujizin.leafy.local.datastore

import android.content.Context
import com.ujizin.leafy.local.datastore.implementation.UserDataStoreImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {

    @Provides
    @Singleton
    fun provideUserDataStore(
        @ApplicationContext context: Context
    ): UserDataStore = UserDataStoreImpl(
        context = context,
        serializer = Json,
    )
}
