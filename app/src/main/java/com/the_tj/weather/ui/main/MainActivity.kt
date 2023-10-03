package com.the_tj.weather.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.the_tj.weather.R
import com.the_tj.weather.data.model.ItemModel
import com.the_tj.weather.databinding.ActivityMainBinding
import com.the_tj.weather.ui.weather.WeatherFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : AppCompatActivity(),MainContracts.View{
    //Binding
    private lateinit var binding: ActivityMainBinding
    //Adapter
    @Inject
    lateinit var itemsAdapter:ItemsAdapter
    @Inject
    lateinit var presenter: MainPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            presenter.loadAllItems()
            //make a new location and weather item
            addItemBtn.setOnClickListener {
                WeatherFragment().show(supportFragmentManager,WeatherFragment().tag)
            }
        }


    }

    override fun showAllItems(itemModel:List<ItemModel> ) {
        binding.emptyLay.visibility=View.GONE
        binding.itemsList.visibility=View.VISIBLE
        itemsAdapter.differ.submitList(itemModel)

        binding.itemsList.apply {
            layoutManager=StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)
            adapter=itemsAdapter
        }
            }

    override fun showEmpty() {
        binding.emptyLay.visibility = View.VISIBLE
        binding.itemsList.visibility = View.GONE
    }

    override fun onStop() {
        super.onStop()
        presenter.onStop()
    }
}