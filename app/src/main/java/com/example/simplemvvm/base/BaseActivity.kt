package com.example.simplemvvm.base

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View.inflate
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.example.simplemvvm.R
import com.example.simplemvvm.di.ViewModelFactory
import javax.inject.Inject

abstract class BaseActivity<T: ViewBinding, V: ViewModel> : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    lateinit var viewModel: V

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater) -> T

    @Suppress("UNCHECKED_CAST")
    protected val binding: T
        get() = _binding as T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupInjector()
        _binding = bindingInflater.invoke(layoutInflater)
        viewModel = ViewModelProvider(setObserver(), viewModelFactory).get(setViewModel())
        setContentView(requireNotNull(_binding).root)
        setUpStatusBarColor()

    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        setupView(binding)
        setData()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun setupView(binding: T)

    abstract fun setData()

    abstract fun setupInjector()

    abstract fun setViewModel(): Class<V>

    abstract fun setObserver(): FragmentActivity

    private fun setUpStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(
                applicationContext,
                R.color.design_default_color_on_background
            )
        }
    }
}