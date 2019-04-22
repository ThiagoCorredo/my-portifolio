package com.tcorredo.myportifolio

import android.app.Activity
import android.app.Application
import com.tcorredo.myportifolio.di.component.DaggerAppComponent
import com.tcorredo.myportifolio.util.logging.MyDebugTree
import com.tcorredo.myportifolio.util.logging.ReleaseTree
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

/**
 * @author Thiago Corredo
 * @since 2019-03-04
 */
class MyApplication : Application(), HasActivityInjector {

  @Inject
  lateinit var activityInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()

    DaggerAppComponent.builder()
        .application(this)
        .build()
        .inject(this)

    if (BuildConfig.DEBUG) {
      Timber.plant(MyDebugTree())
    } else {
      Timber.plant(ReleaseTree())
    }
  }

  // this is required to setup Dagger2 for Activity
  override fun activityInjector(): AndroidInjector<Activity> = activityInjector
}
