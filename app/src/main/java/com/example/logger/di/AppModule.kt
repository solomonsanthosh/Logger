package com.example.logger.di

import android.app.Application
import androidx.hilt.navigation.HiltViewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.room.Room
import com.example.logger.data.repositoryImpl.LogRepositoryImpl
import com.example.logger.data.room.Dao.LogDAO
import com.example.logger.data.room.LogDB
import com.example.logger.data.repository.LogRepository
import com.example.logger.viewModel.LogViewModel

import com.example.logger.views.MainActivity

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

//    @Provides
//    fun provideViewModelStoreOwner(activity: MainActivity): ViewModelStoreOwner {
//        return activity
//    }
//
//    @Provides
//    @Singleton
//    fun provideVm(activity: MainActivity,repository: LogRepository): ViewModel {
//        return ViewModelProvider(activity)[LogViewModel::class.java]
//    }


    @Provides
    @Singleton
    fun provideRepository(context: Application,logDB: LogDB): LogRepository {
        return LogRepositoryImpl(context,logDB)
    }
    @Singleton
    @Provides
    fun providesLogDB(context: Application):LogDB {
        return Room.databaseBuilder(
            context.applicationContext,
            LogDB::class.java,
            "logs_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesLogDao(logDB: LogDB): LogDAO {
        return logDB.logDao()
    }
}