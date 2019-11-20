package com.singaporepsi.ui.base

import android.app.ProgressDialog
import android.content.Context
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.singaporepsi.result.EventObserver
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

abstract class BaseFragment constructor(@LayoutRes contentLayoutId: Int) :
    Fragment(contentLayoutId), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    lateinit var baseActivity: BaseActivity
    private var progress: ProgressDialog? = null

    abstract fun getViewModel(): BaseViewModel

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is BaseActivity) {
            baseActivity = context
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getViewModel().messageLiveData.observe(viewLifecycleOwner, EventObserver {
            it?.let {
                showMessage(it)
            }
        })
        getViewModel().showDialogLiveData.observe(viewLifecycleOwner, EventObserver {
            showLoadingDialog()
        })
        getViewModel().hideDialogLiveData.observe(viewLifecycleOwner, EventObserver {
            dismissLoadingDialog()
        })
    }

    fun showMessage(message: String?) {
        baseActivity.showMessage(message)
    }

    fun showLoadingDialog() {
        if (progress == null) {
            progress = ProgressDialog(context)
            progress?.setMessage("Please wait...")
            progress?.setCancelable(false)
            progress?.setCanceledOnTouchOutside(false)
        }
        progress?.show()
    }

    fun dismissLoadingDialog() {
        if (progress?.isShowing == true) {
            progress?.dismiss()
        }
    }
}