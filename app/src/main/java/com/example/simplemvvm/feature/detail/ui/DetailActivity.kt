package com.example.simplemvvm.feature.detail.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.FragmentActivity
import com.example.simplemvvm.base.BaseActivity
import com.example.simplemvvm.databinding.ActivityDetailBinding
import com.example.simplemvvm.feature.detail.di.DetailInjector
import com.example.simplemvvm.feature.home.model.GithubUser
import com.example.simplemvvm.utils.loadImage

class DetailActivity : BaseActivity<ActivityDetailBinding, DetailViewModel>() {

    private lateinit var data: GithubUser

    override val bindingInflater: (LayoutInflater) -> ActivityDetailBinding
        get() = ActivityDetailBinding::inflate

    override fun setupView(binding: ActivityDetailBinding) {

    }

    override fun setData() {
        data = intent.getParcelableExtra<GithubUser>(DATA_USER) as GithubUser
        with(binding) {
            tvUser.text = data.login
            ivUser.loadImage(data.avatarUrl.orEmpty())
        }
    }

    override fun setupInjector() {
        DetailInjector.of(this)
    }

    override fun setViewModel(): Class<DetailViewModel> = DetailViewModel::class.java

    override fun setObserver(): FragmentActivity = this

    companion object {
        const val DATA_USER = "DATA_USER"
        fun startWithData(context: Context, data: GithubUser) {
            context.startActivity(
                Intent(context, DetailActivity::class.java).putExtra(DATA_USER, data)
            )
        }
    }
}