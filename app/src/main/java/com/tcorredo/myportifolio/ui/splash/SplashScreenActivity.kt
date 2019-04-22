package com.tcorredo.myportifolio.ui.splash

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.tcorredo.myportifolio.BR
import com.tcorredo.myportifolio.R
import com.tcorredo.myportifolio.ViewModelProviderFactory
import com.tcorredo.myportifolio.databinding.ActivitySplashScreenBinding
import com.tcorredo.myportifolio.ui.base.BaseActivity
import javax.inject.Inject

/**
 * @author Thiago Corredo
 * @since 2019-03-04
 */
class SplashScreenActivity : BaseActivity<ActivitySplashScreenBinding, SplashViewModel>(),
    SplashNavigator {

  @Inject
  lateinit var factory: ViewModelProviderFactory

  private var mSplashViewModel: SplashViewModel? = null

  override val bindingVariable: Int
    get() = BR.viewModel

  override val layoutId: Int
    get() = R.layout.activity_splash_screen

  override val viewModel: SplashViewModel
    get() {
      mSplashViewModel = ViewModelProviders.of(this, factory)
          .get(SplashViewModel::class.java)
      return mSplashViewModel as SplashViewModel
    }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)

  }

  override fun openLoginActivity() {

  }

  override fun openMainActivity() {
//        Handler().postDelayed({
//            val intent = HomeActivity.newIntent(this)
//            intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)
//            startActivity(intent)
//        }, 5_000)
  }
}
