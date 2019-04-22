package com.tcorredo.myportifolio.scheduler

import io.reactivex.Scheduler

/**
 * @author Thiago Corredo
 * @since 2019-03-11
 */
interface SchedulerProvider {

  fun io(): Scheduler

  fun computation(): Scheduler

  fun ui(): Scheduler
}