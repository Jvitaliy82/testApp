package com.appCraft.testApp.ui.fragment.fromWeb

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.appCraft.domain.model.TvShowModel
import com.appCraft.testApp.R
import com.appCraft.testApp.global.ui.fragment.BaseFragment
import com.appCraft.testApp.presentation.fromWeb.FromWebPresenter
import com.appCraft.testApp.presentation.fromWeb.FromWebView
import kotlinx.android.synthetic.main.fragment_from_web.*
import moxy.presenter.InjectPresenter

class FromWebFragment : BaseFragment(R.layout.fragment_from_web), FromWebView, TvShowXAdapter.OnItemClickListener {

    @InjectPresenter
    lateinit var presenter: FromWebPresenter

    private lateinit var tvShowXAdapter: TvShowXAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.getTvShow(0)
    }

    private fun setupRecyclerView() = recycler_view.apply {
        tvShowXAdapter = TvShowXAdapter(this@FromWebFragment)
        adapter = tvShowXAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setListInAdapter(list: List<TvShowModel.TvShowX>) {
        tvShowXAdapter.submitList(list)
    }

    override fun onItemClick(tvShow: TvShowModel.TvShowX) {
        presenter.navigateToDetailFragment(tvShow.id)
    }

    override fun addFavorite(tvShow: TvShowModel.TvShowX) {
        presenter.saveTvShow(tvShow)
    }

    companion object {
        fun newInstance(): FromWebFragment {
            return FromWebFragment()
        }
    }
}