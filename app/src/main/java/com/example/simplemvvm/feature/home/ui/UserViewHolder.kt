package com.example.simplemvvm.feature.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.simplemvvm.databinding.ItemUserBinding
import com.example.simplemvvm.feature.home.model.GithubUser
import com.example.simplemvvm.utils.loadImage

class UserViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
    companion object {
        fun inflate(parent: ViewGroup): UserViewHolder {
            val itemBinding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return UserViewHolder(itemBinding)
        }
    }

    fun bind(data: GithubUser) {
        with(binding) {
            ivUser.loadImage(data.avatarUrl.orEmpty())
            tvUser.text = data.login.orEmpty()
            tvUrl.text = data.url.orEmpty()
        }
    }

    fun itemClick(click: (position: Int) -> Unit) {
        binding.root.setOnClickListener {
            click.invoke(adapterPosition)
        }
    }
}