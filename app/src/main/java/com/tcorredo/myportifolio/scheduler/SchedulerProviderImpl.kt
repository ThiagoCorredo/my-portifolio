package com.tcorredo.myportifolio.scheduler

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @author Thiago Corredo
 * @since 2019-03-11
 */
class SchedulerProviderImpl : SchedulerProvider {

  override fun io(): Scheduler {
    return Schedulers.io()
  }

  override fun computation(): Scheduler {
    return Schedulers.computation()
  }

  override fun ui(): Scheduler {
    return AndroidSchedulers.mainThread()
  }
}