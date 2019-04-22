package com.tcorredo.myportifolio.di.component

import android.app.Application
import com.tcorredo.myportifolio.MyApplication
import com.tcorredo.myportifolio.di.module.ActivityModule
import com.tcorredo.myportifolio.di.module.AppModule
import com.tcorredo.myportifolio.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * @author Thiago Corredo
 * @since 2019-03-06
 */
@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AppModule::class, ActivityModule::class,
      ViewModelModule::class]
)
interface AppComponent {

  @Component.Builder
  interface Builder {

    // provide Application instance into DI
    @BindsInstance
    fun application(application: Application): Builder

    fun build(): AppComponent
  }

  fun inject(application: MyApplication)
}