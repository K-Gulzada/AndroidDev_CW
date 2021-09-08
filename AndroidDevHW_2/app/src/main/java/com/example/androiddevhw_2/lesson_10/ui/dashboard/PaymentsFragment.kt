package com.example.androiddevhw_2.lesson_10.ui.dashboard

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.lesson_10.model.PaymentsModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

/*class PaymentsFragment : Fragment() {

    private lateinit var viewModel: PaymentsViewModel

    val paymentsModel: PaymentsModel by inject() // заинжектили PaymentsModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payments_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<TextView>(R.id.payment)?.text = paymentsModel.payments.first()
    }

}*/

// после добавления библиотеки implementation "io.insert-koin:koin-android-viewmodel:$koin_version"

class PaymentsFragment : Fragment() {

    private val paymentsViewModel: PaymentsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.payments_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<TextView>(R.id.payment)?.text = paymentsViewModel.payments.first()
    }

}