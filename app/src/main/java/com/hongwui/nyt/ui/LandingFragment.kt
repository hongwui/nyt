package com.hongwui.nyt.ui

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.hongwui.nyt.R
import com.hongwui.nyt.base.BaseFragment
import com.hongwui.nyt.databinding.FragmentLandingBinding

class LandingFragment : BaseFragment<FragmentLandingBinding>(
    FragmentLandingBinding::inflate
) {
    override fun setToolbarTitle(): String = "NYT"

    override fun showToolbarBackButton(): Boolean = false

    override fun setToolbarBackAction() = Unit

    override fun onViewCreated() {
        binding.apply {
            tvSearch.setOnClickListener { findNavController().navigate(R.id.action_search) }
            tvViewed.setOnClickListener {
                findNavController().navigate(
                    R.id.action_list_by_type,
                    bundleOf("type" to 2, "keyword" to "")
                )
            }
            tvShared.setOnClickListener {
                findNavController().navigate(
                    R.id.action_list_by_type,
                    bundleOf("type" to 3, "keyword" to "")
                )
            }
            tvEmailed.setOnClickListener {
                findNavController().navigate(
                    R.id.action_list_by_type,
                    bundleOf("type" to 4, "keyword" to "")
                )
            }
        }
    }

}