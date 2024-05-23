package com.hongwui.nyt.ui

import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.hongwui.nyt.R
import com.hongwui.nyt.base.BaseFragment
import com.hongwui.nyt.databinding.FragmentSearchBinding

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {
    override fun setToolbarTitle(): String = getString(R.string.search)
    override fun showToolbarBackButton(): Boolean = true
    override fun setToolbarBackAction() = requireActivity().onBackPressed()
    override fun onViewCreated() {
        binding.apply {
            btnSearch.setOnClickListener {
                if (etSearch.text!!.isNotBlank()) {
                    findNavController().navigate(
                        R.id.action_list,
                        bundleOf("type" to 1, "keyword" to etSearch.text.toString())
                    )
                } else {
                    Toast.makeText(requireContext(), "Keyword is empty", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}