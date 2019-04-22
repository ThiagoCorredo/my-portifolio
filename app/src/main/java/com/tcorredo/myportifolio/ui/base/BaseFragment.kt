package com.tcorredo.myportifolio.ui.base

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresPermission
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import dagger.android.support.AndroidSupportInjection

/**
 * @author Thiago Corredo
 * @since 2019-04-18
 */
abstract class BaseFragment<T : ViewDataBinding, V : BaseViewModel<*>> : Fragment() {

  private var baseActivity: BaseActivity<*, *>? = null
  private var viewDataBinding: T? = null
  private var mViewModel: V? = null
  private var mRootView: View? = null

  val isNetworkConnected: Boolean
    @RequiresPermission(Manifest.permission.ACCESS_NETWORK_STATE)
    get() = baseActivity != null && baseActivity!!.isNetworkConnected

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
    mViewModel = viewModel
    setHasOptionsMenu(false)
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    viewDataBinding = DataBindingUtil.inflate(inflater, layoutId, container, false)
    mRootView = viewDataBinding!!.root
    return mRootView
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    viewDataBinding!!.setVariable(bindingVariable, mViewModel)
    viewDataBinding!!.executePendingBindings()
  }

  override fun onAttach(context: Context) {
    super.onAttach(context)
    if (context is BaseActivity<*, *>) {
      val activity = context as BaseActivity<*, *>?
      this.baseActivity = activity
      activity!!.onFragmentAttached()
    }
  }

  override fun onDetach() {
    baseActivity = null
    super.onDetach()
  }

  fun hideKeyboard() {
    if (baseActivity != null) {
      baseActivity!!.hideKeyboard()
    }
  }

  private fun performDependencyInjection() {
    AndroidSupportInjection.inject(this)
  }

  interface Callback {

    fun onFragmentAttached()

    fun onFragmentDetached(tag: String)
  }
}