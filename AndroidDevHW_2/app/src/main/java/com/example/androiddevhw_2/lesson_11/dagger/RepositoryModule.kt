package com.example.androiddevhw_2.lesson_11.dagger

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
/*    @Provides
    fun getApi(): Api = MockApi()

    @Provides
    fun getDataSource(): DataSource = RemoteDataSourceImpl(getApi())

    @Provides
    fun getRepository(): Repository = RepositoryImpl(getDataSource())*/

    @Provides
    fun getRepo(): TestRepo = TestRepo()
}
