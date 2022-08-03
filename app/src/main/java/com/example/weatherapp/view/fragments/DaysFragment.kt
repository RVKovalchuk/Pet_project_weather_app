package com.example.weatherapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.adapters.RecyclerViewAdapter
import com.example.weatherapp.classesFromJSONs.Forecastday
import com.example.weatherapp.usecases.*
import retrofit2.*


class DaysFragment : Fragment() {
    private lateinit var adapter: RecyclerViewAdapter
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
        return inflater.inflate(R.layout.fragment_hours, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() {
        val recyclerView =
            view?.findViewById<RecyclerView>(R.id.fragment_hours_recycler_view)

        recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        adapter = RecyclerViewAdapter()
        recyclerView?.adapter = adapter
        val list = getWeatherFromApiUseCase.execute(Constants.CITY, {
            adapter.submitList(it)
            adapter.notifyDataSetChanged()
            Log.d("!!!!", "Размер листа после адаптера: ${adapter.listForDayFragment.size}")
        },
            {

            })
    }
}