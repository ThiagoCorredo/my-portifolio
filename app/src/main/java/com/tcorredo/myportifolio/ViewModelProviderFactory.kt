package com.tcorredo.myportifolio

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @author Thiago Corredo
 * @since 2019-04-18
 */
@Singleton
class ViewModelProviderFactory @Inject
constructor(private val viewModels: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
    ViewModelProvider.Factory {

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    val creator = viewModels[modelClass]
        ?: viewModels.asIterable().firstOrNull { modelClass.isAssignableFrom(it.key) }?.value
        ?: throw IllegalArgumentException("unknown model class $modelClass")
    return try {
      creator.get() as T
    } catch (e: Exception) {
      throw RuntimeException(e)
    }
  }
}