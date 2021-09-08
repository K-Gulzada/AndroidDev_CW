package com.example.androiddevhw_2.lesson_10.module

import com.example.androiddevhw_2.lesson_10.model.PaymentsModel
import com.example.androiddevhw_2.lesson_10.repository.PaymentsRepository
import com.example.androiddevhw_2.lesson_10.repository.PaymentsRepositoryImpl
import com.example.androiddevhw_2.lesson_10.ui.dashboard.PaymentsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val paymentsModule = module {
    single<PaymentsRepository> { PaymentsRepositoryImpl() } // singleton

    factory { PaymentsModel(get()) } // каждый раз новый инстанс

    // после добавления библиотеки implementation "io.insert-koin:koin-android-viewmodel:$koin_version"
    viewModel{PaymentsViewModel(get())}
}