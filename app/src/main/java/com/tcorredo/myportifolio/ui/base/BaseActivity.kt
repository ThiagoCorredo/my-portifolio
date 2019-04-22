package com.tcorredo.myportifolio.ui.base

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresPermission
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.tcorredo.myportifolio.util.NetworkUtils
import dagger.android.AndroidInjection

/**
 * @author Thiago Corredo
 * @since 2019-04-18
 */
abstract class BaseActivity<T : ViewDataBinding, V : BaseViewModel<*>> : AppCompatActivity(),
    BaseFragment.Callback {

  var viewDataBinding: T? = null
  private var mViewModel: V? = null

  val isNetworkConnected: Boolean
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    get() = NetworkUtils.isNetworkConnected(applicationContext)

  /**
   * Override for set view model
   *
   * @return view model instance
   */
  abstract val viewModel: V

  /**
   * Override for set binding variable
   *
   * @return variable id
   */
  abstract val bindingVariable: Int

  /**
   * @return layout resource id
   */
  @get:LayoutRes
  abstract val layoutId: Int

  override fun onCreate(savedInstanceState: Bundle?) {
    performDependencyInjection()
    super.onCreate(savedInstanceState)
    performDataBinding()
  }

  override fun onFragmentAttached() {

  }

  override fun onFragmentDetached(tag: String) {

  }

  fun performDependencyInjection() {
    AndroidInjection.inject(this)
  }

  private fun performDataBinding() {
    viewDataBinding = DataBindingUtil.setContentView(this, layoutId)
    this.mViewModel = if (mViewModel == null) viewModel else mViewModel
    viewDataBinding!!.setVariable(bindingVariable, mViewModel)
    viewDataBinding!!.executePendingBindings()
  }

  fun hideKeyboard() {
    val view = this.currentFocus
    if (view != null) {
      val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

      imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
  }
}