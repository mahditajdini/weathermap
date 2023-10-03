package com.the_tj.weather.utils.di

import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.the_tj.weather.ui.weather.WeatherContracts
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
object ContractsModule {
    @Provides
    fun weatherView(fragment: Fragment):WeatherContracts.View{
        return fragment as WeatherContracts.View
    }
}