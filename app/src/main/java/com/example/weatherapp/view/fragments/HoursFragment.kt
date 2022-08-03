package com.example.weatherapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapters.RecyclerViewAdapter
import com.example.weatherapp.adapters.RecyclerViewAdapterHours
import com.example.weatherapp.usecases.*

class HoursFragment : Fragment() {
    private lateinit var adapter: RecyclerViewAdapterHours
    private val getWeatherFromApiUseCase: GetWeatherFromApiUseCase by lazy {
        GetWeatherFromApiUseCase(
            convertFromWeatherToWeatherModel = convertFromWeatherToWeatherModelUseCase,
            fillMainCard = fillMainCardUseCase,
            getListForDayFragmentUseCase = getListForDayFragmentUseCase,
            getListForHourFragmentUseCase = getListForHourFragmentUseCase,
            view = requireView(),
            context = requireContext()
        )
    }
    private val getListForDayFragmentUseCase: GetListForDayFragmentUseCase by lazy {
        GetListForDayFragmentUseCase()
    }

    private val getListForHourFragmentUseCase: GetListForHourFragmentUseCase by lazy {
        GetListForHourFragmentUseCase()
    }

    private val fillMainCardUseCase: FillMainCardUseCase by lazy {
        FillMainCardUseCase()
    }

    private val convertFromWeatherToWeatherModelUseCase: ConvertFromWeatherToWeatherModelUseCase by lazy {
        ConvertFromWeatherToWeatherModelUseCase()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_hours, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
    }

    private fun initRecycler() {
        val recyclerView =
            view?.findViewById<RecyclerView>(R.id.fragment_hours_recycler_view)

        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        adapter = RecyclerViewAdapterHours()
        recyclerView?.adapter = adapter
        val list = getWeatherFromApiUseCase.execute(Constants.CITY, {

        },
            {
                adapter.submitList(it)
                adapter.notifyDataSetChanged()
                Log.d("!!!!", "Размер листа после адаптера: ${adapter.listForHourFragment.size}")
            })
        Log.d("!!!!", "Размер листа до адаптера: ${adapter.listForHourFragment.size}")
    }
}