package com.tcorredo.myportifolio.ui.base

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import com.tcorredo.myportifolio.scheduler.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

/**
 * @author Thiago Corredo
 * @since 2019-04-10
 */
abstract class BaseViewModel<N>(val schedulerProvider: SchedulerProvider) : ViewModel() {

  private val isLoading = ObservableBoolean(false)
  private val compositeDisposable = CompositeDisposable()
  var navigator: N? = null

  fun setIsLoading(isLoading: Boolean) {
    this.isLoading.set(isLoading)
  }

  override fun onCleared() {
    compositeDisposable.dispose()
    super.onCleared()
  }
}