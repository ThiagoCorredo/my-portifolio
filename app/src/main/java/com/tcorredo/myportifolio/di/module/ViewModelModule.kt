package com.tcorredo.myportifolio.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tcorredo.myportifolio.ViewModelProviderFactory
import com.tcorredo.myportifolio.ui.splash.SplashViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * @author Thiago Corredo
 * @since 2019-04-20
 */
@Module
abstract class ViewModelModule {
  @Binds
  @IntoMap
  @ViewModelKey(SplashViewModel::class)
  abstract fun bindSplashViewModel(myViewModel: SplashViewModel): ViewModel

  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelProviderFactory): ViewModelProvider.Factory
}