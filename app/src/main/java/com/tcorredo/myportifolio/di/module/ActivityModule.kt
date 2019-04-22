package com.tcorredo.myportifolio.di.module

import com.tcorredo.myportifolio.ui.splash.SplashScreenActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * @author Thiago Corredo
 * @since 2019-03-13
 */
@Module
abstract class ActivityModule {

  @ContributesAndroidInjector
  internal abstract fun contributeSplashScreen(): SplashScreenActivity
//
//  @ContributesAndroidInjector
//  internal abstract fun contributeHomeActivity(): HomeActivity
}