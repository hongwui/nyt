package com.hongwui.nyt.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.hongwui.nyt.R
import com.hongwui.nyt.base.BaseFragment
import com.hongwui.nyt.databinding.FragmentListBinding
import com.hongwui.nyt.domain.NytRepository
import com.hongwui.nyt.model.ArticleModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ListFragment : BaseFragment<FragmentListBinding>(FragmentListBinding::inflate) {

    private var type = 0
    private var keyword = ""
    private var viewModel = ArticleListViewModel(NytRepository())
    private lateinit var adapter: ArticleListAdapter

    private var isLoading = false
        set(value) {
            if (value) {
                binding.rvDefault.visibility = View.GONE
                binding.pbLoading.visibility = View.VISIBLE
            } else {
                binding.rvDefault.visibility = View.VISIBLE
                binding.pbLoading.visibility = View.GONE
            }
            field = value
        }

    override fun setToolbarTitle(): String = getTitle()

    override fun showToolbarBackButton(): Boolean = true

    override fun setToolbarBackAction() = requireActivity().onBackPressed()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            type = arguments?.getInt("type") ?: 0
            keyword = arguments?.getString("keyword") ?: ""
        }
    }

    override fun onViewCreated() {
        lifecycleScope.launch {
            viewModel.articleList.collectLatest {
                if (it.isNotEmpty()) {
                    adapter = ArticleListAdapter(requireContext(), it, ::onItemClicked)
                    binding.rvDefault.adapter = adapter
                    binding.rvDefault.layoutManager = LinearLayoutManager(requireContext())
                } else {
                    Toast.makeText(requireContext(), "No item found", Toast.LENGTH_SHORT).show()
                }
            }
        }

        lifecycleScope.launch {
            viewModel.error.collectLatest {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                requireActivity().onBackPressed()
            }
        }

        lifecycleScope.launch {
            viewModel.isLoading.collectLatest { isLoading = it }
        }

        when (type) {
            1 -> viewModel.searchArticle(keyword)
            2 -> viewModel.getPopularArticle("viewed")
            3 -> viewModel.getPopularArticle("shared")
            4 -> viewModel.getPopularArticle("emailed")
        }
    }

    private fun getTitle(): String {
        return when (type) {
            1 -> "Search in $keyword"
            2 -> getString(R.string.most_viewed)
            3 -> getString(R.string.most_shared)
            4 -> getString(R.string.most_emailed)
            else -> "NYT"
        }
    }

    private fun onItemClicked(data: ArticleModel) {
        Toast.makeText(requireContext(), "${data.title} is clicked", Toast.LENGTH_SHORT)
            .show()
    }
}