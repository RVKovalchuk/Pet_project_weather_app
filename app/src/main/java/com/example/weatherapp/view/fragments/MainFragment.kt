package com.example.weatherapp.view.fragments

import android.Manifest
import android.os.Bundle
import android.transition.Visibility
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.weatherapp.R
import com.example.weatherapp.adapters.ViewPagerAdapter
import com.example.weatherapp.usecases.*
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainFragment : Fragment() {
    private val getWeatherFromApiUseCase: GetWeatherFromApiUseCase by lazy {
        GetWeatherFromApiUseCase(
            convertFromWeatherToWeatherModel = convertFromWeatherToWeatherModelUseCase,
            fillMainCard = fillMainCardUseCase,
            getListForDayFragmentUseCase = getListForDayFragmentUseCase,
            view = requireView(),
            context = requireContext(),
            getListForHourFragmentUseCase = getListForHourFragmentUseCase
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

    //инициализация лаунчера для получения разрешения
    private lateinit var launcher: ActivityResultLauncher<String>

    //инициализация списка фрагментов
    private val listOfFragmentsForViewPager = listOf(
        DaysFragment(), HoursFragment()
    )

    //инициализация листов для viewPager2
    private val listOfTabs = listOf(
        "DAYS", "HOURS"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAPI()
        checkPermission()
        init()
        setNewCity()
    }

    fun initAPI() {
        getWeatherFromApiUseCase.execute(Constants.CITY, {

        }, {
            Log.d("!!!!", "SIZE ${it.size}")
        })
    }

    //инициализация viewPager (свайп фрагментов)
    private fun init() {
        val viewPager =
            (activity as FragmentActivity).findViewById<ViewPager2>(R.id.fragment_main_view_pager_layout)
        val tabLayout =
            (activity as FragmentActivity).findViewById<TabLayout>(R.id.fragment_main_tab_layout)
        val adapter = ViewPagerAdapter(activity as FragmentActivity, listOfFragmentsForViewPager)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = listOfTabs[position]
        }.attach()
    }

    //создание лаунчера для получения разрешения
    private fun permissionListener() {
        launcher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
        }
    }

    //проверка разрешения на получение местоположения
    private fun checkPermission() {
        if (!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)) {
            permissionListener()
            launcher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    private fun setNewCity() {
        val buttonSearch = view?.findViewById<ImageButton>(R.id.fragment_main_image_button_search)
        val buttonRefresh = view?.findViewById<ImageButton>(R.id.fragment_main_image_button_refresh)
        val edittext = view?.findViewById<EditText>(R.id.fragment_main_edit_text)

        buttonSearch?.setOnClickListener {
            if (edittext!!.isVisible) {
                edittext.visibility = View.GONE
            } else {
                edittext.visibility = View.VISIBLE
            }
        }
        buttonRefresh?.setOnClickListener {
            Constants.CITY = edittext?.text.toString()
            initAPI()
            edittext?.text = null
            Log.d("!!!!", "CITY ${Constants.CITY}")
        }
    }
}