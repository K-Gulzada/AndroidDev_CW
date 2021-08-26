package com.example.androiddevhw_2.lesson_5

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.androiddevhw_2.R
import com.example.androiddevhw_2.databinding.FragmentFinishBinding

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/*
class FinishFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_finish, container, false)
    }

    override fun onStart() {
        super.onStart()
        view?.findViewById<TextView>(R.id.title)?.setText(param1 + param2)
        */
/*this.view?.findViewById<Button>(R.id.start_fragment_btn)?.setOnClickListener{
            (activity as? FragmentActivity)?.onNext()

        }*//*

    }

    companion object {

    }
}*/

// после добавления navigation

class FinishFragment : Fragment() {
    
    val args: FinishFragmentArgs by navArgs()
    private lateinit var binding: FragmentFinishBinding
  
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

      binding = FragmentFinishBinding.inflate(layoutInflater)
        return  binding.root
    }

    override fun onStart() {
        super.onStart()
      binding.title.text = args.title
        binding.subtitle.text = args.subtitle?:"default subtitle text"
      
    }
}

// создать третий фрагмент A -> B -> C -> A
// добавить анимашку в навигацию
// через навигэйшн A -> B -> C ->  никуда, не может выйти