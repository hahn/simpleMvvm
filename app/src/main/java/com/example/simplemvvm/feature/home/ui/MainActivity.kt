package com.example.simplemvvm.feature.home.ui

import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.simplemvvm.base.BaseActivity
import com.example.simplemvvm.base.BaseAdapter
import com.example.simplemvvm.databinding.ActivityMainBinding
import com.example.simplemvvm.feature.home.di.MyHomeInjector
import com.example.simplemvvm.feature.home.model.GithubUser
import com.example.simplemvvm.utils.hideView
import com.example.simplemvvm.utils.showView
import timber.log.Timber

class MainActivity :BaseActivity<ActivityMainBinding, MainViewModel>() {

    private lateinit var adapter: BaseAdapter<GithubUser, UserViewHolder>

    override val bindingInflater: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate

    override fun setupView(binding: ActivityMainBinding) {
        setupAdapter()
        setupObserver()
    }

    override fun setData() {
        return viewModel.getUsers()
    }

    override fun setupInjector() {
        MyHomeInjector.of(this)
    }

    override fun setViewModel(): Class<MainViewModel> {
        return MainViewModel::class.java
    }

    override fun setObserver(): FragmentActivity {
        return this
    }

    override fun onResume() {
        super.onResume()
        viewModel.getUsers()
    }

    private fun setupAdapter() {
        adapter = BaseAdapter({parent, _ ->
            UserViewHolder.inflate(parent)
        }, { viewHolder, _, item ->
            viewHolder.bind(item)
        })
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.adapter = adapter
    }

    private fun setupObserver() {
        viewModel.state.observe(this, Observer { state ->
            when(state) {
                is MainViewModel.HomeViewState.ShowLoading -> {
                    Timber.d("loading")
                    binding.progressBar.showView()
                    binding.rvUser.hideView()
                }
                is MainViewModel.HomeViewState.HideLoading -> {
                    binding.progressBar.hideView()
                }
                is MainViewModel.HomeViewState.EmptyResult -> {
                    binding.tvEmpty.showView()
                    binding.rvUser.hideView()
                }
                is MainViewModel.HomeViewState.Error -> {
                    Timber.d("error: ${state.message}")
                    binding.tvEmpty.showView()
                    binding.tvEmpty.text = "error"
                    binding.rvUser.hideView()
                }
                is MainViewModel.HomeViewState.UserLoaded -> {
                    binding.rvUser.showView()
                    adapter.safeAddAll(state.data)
                    binding.tvEmpty.hideView()
                }
            }
        })
    }
}