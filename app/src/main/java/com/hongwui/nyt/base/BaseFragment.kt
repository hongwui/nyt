package com.hongwui.nyt.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.hongwui.nyt.databinding.FragmentBaseBinding

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB: ViewBinding>(
    private val inflate: Inflate<VB>
) : Fragment(){

    private var _binding: VB? = null
    val binding get() = _binding!!

    abstract fun onViewCreated()
    abstract fun setToolbarTitle(): String
    abstract fun showToolbarBackButton(): Boolean
    abstract fun setToolbarBackAction(): Unit

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentBaseBinding.inflate(inflater, container, false).apply {
            _binding = inflate.invoke(inflater, container, false)
            tvTitle.text = setToolbarTitle()
            btnBack.visibility = if(showToolbarBackButton()) View.VISIBLE else View.INVISIBLE
            btnBack.setOnClickListener { setToolbarBackAction() }
            content.addView(binding.root)
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated()
    }
}