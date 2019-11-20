package com.singaporepsi.ui.base

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.singaporepsi.R
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

abstract class BaseActivity constructor(@LayoutRes contentLayoutId: Int) :
    AppCompatActivity(contentLayoutId), HasAndroidInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector(): AndroidInjector<Any> {
        return androidInjector
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    fun showMessage(message: String?, view: View = findViewById(android.R.id.content)) {
        message?.let {
            val snackBar = Snackbar.make(view, it, Snackbar.LENGTH_LONG)
            snackBar.changeFont()
            snackBar.changeBackground()
            snackBar.show()
        }
    }
}

fun Snackbar.changeFont() {
    val tv = view.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    val font = ResourcesCompat.getFont(context, R.font.gilroy_semibold)
    tv.typeface = font
}

fun Snackbar.changeBackground() {
    //view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary))
    //view.setBackgroundResource(R.drawable.bg_snackbar)
}