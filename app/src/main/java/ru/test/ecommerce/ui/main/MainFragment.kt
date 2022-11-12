package ru.test.ecommerce.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import ru.test.ecommerce.adapter.CompositeAdapter
import ru.test.ecommerce.adapter.DelegateAdapterItem
import ru.test.ecommerce.databinding.FragmentMainBinding
import ru.test.ecommerce.model.AuthAdapter
import ru.test.ecommerce.model.AuthAdapterModel

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = mutableListOf<DelegateAdapterItem>()
        for (i in 0..10) {
            data.add(AuthAdapterModel(i.toString()))
        }
        val compositeAdapter = CompositeAdapter.Builder()
            .add(AuthAdapter {})
            .add(AuthAdapter {})
            .build()
        binding.recyclerCategory.adapter = compositeAdapter
        compositeAdapter.submitList(data)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        fun newInstance() = MainFragment()
    }
}
