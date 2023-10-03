package com.the_tj.weather.ui.weather

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.the_tj.weather.R
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.data.model.LocationModel
import com.the_tj.weather.data.model.WeatherModel
import com.the_tj.weather.data.repository.WeatherRepository
import com.the_tj.weather.databinding.FragmentWeatherBinding
import com.the_tj.weather.utils.isNetworkAvailable
import com.the_tj.weather.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class WeatherFragment : BottomSheetDialogFragment(), WeatherContracts.View,OnMapReadyCallback {

    //Binding
    private lateinit var binding: FragmentWeatherBinding
    //models

    lateinit var weatherModel:WeatherModel

    lateinit var locationModel: LocationModel
    @Inject
    lateinit var itemModel: ItemModel
    //presenter
    @Inject
    lateinit var presenter: WeatherPresenter
    //GoogleMaps
    private var googleMap:GoogleMap?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentWeatherBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            closeImg.setOnClickListener { close() }
            showLoading()
            val mapFragment = childFragmentManager.findFragmentById(R.id.mapFragment) as? SupportMapFragment
            //val mapFragment= childFragmentManager.mapFragment as SupportMapFragment
            mapFragment!!.getMapAsync(this@WeatherFragment)
            /*val mapView=view.findViewById<MapView>(R.id.mapFragment)
            showLoading()
            mapView.getMapAsync(this@WeatherFragment)*/

        }
    }

    override fun close() {
        this@WeatherFragment.dismiss()
    }

    override fun showWeather(weatherModel: WeatherModel) {
        this.weatherModel=weatherModel
        binding.weatherTv.text="descriptions: "+weatherModel.weather?.get(0).description +"\ntemprature: "+ weatherModel.main.temp
    }




    override fun showLoading() {
        binding.mapLoading.visibility=View.VISIBLE
        binding.weatherTv.visibility=View.GONE
        binding.mapFragment.visibility=View.GONE
    }

    override fun hideLoading() {
        binding.mapLoading.visibility=View.GONE
        binding.weatherTv.visibility=View.VISIBLE
        binding.mapFragment.visibility=View.VISIBLE
    }

    override fun checkInternet(): Boolean {
        return requireContext().isNetworkAvailable()
    }

    override fun internetError(hasInternet: Boolean) {
        binding.apply {
            if (!hasInternet) {
                Log.e("mytag","no internet")
                mapContent.visibility=View.GONE
                mapDisLay.visibility=View.VISIBLE
                //change view
                disconnectLay.disImg.setImageResource(R.drawable.disconnect)
                disconnectLay.disTxt.text = getString(R.string.checkInternet)
            } else{
                Log.e("mytag","internet error")
                mapContent.visibility=View.VISIBLE
                mapDisLay.visibility=View.GONE
            }
        }
    }

    override fun serverError(message: String) {
        binding.root.showSnackBar(message)
        Log.e("mytag","server error")
    }



    override fun onMapReady(p0: GoogleMap) {
        Log.e("mytag","on map ready")
        googleMap=p0
        hideLoading()
        // Set up the map click listener
        googleMap!!.setOnMapClickListener { latLng->
            locationModel= LocationModel(latLng.latitude,latLng.longitude)

            presenter.callWeather(locationModel)



        }
    }
    override fun getWeather() {
        itemModel.description=weatherModel.weather?.get(0).description
            itemModel.temp=+weatherModel.main.temp
            itemModel.latitude=locationModel.latitude
            itemModel.longitude=locationModel.longitude

            presenter.saveItem(itemModel)
    }


}