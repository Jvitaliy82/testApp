package com.appcraft.testapp.app.ui.fragment.fromWeb

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.appcraft.testapp.app.presentation.fromWeb.FromWebPresenter
import com.appcraft.testapp.app.presentation.fromWeb.FromWebView
import kotlinx.android.synthetic.main.fragment_from_web.*
import moxy.presenter.InjectPresenter

class FromWebFragment : BaseFragment(R.layout.fragment_from_web), FromWebView,
    TvShowXAdapter.OnItemClickListener {

//    override var isLightStatusBar = false

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

    override fun setListInAdapter(list: List<TvShowItemMP>) {
        tvShowXAdapter.submitList(list)
    }

    override fun onItemClick(tvShowModel: TvShowItemMP) {
        presenter.navigateToDetailFragment(tvShowModel.id)
    }

    override fun addFavorite(tvShowModel: TvShowItemMP) {
        presenter.saveTvShow(tvShowModel)
    }

    companion object {
        fun newInstance(): FromWebFragment {
            return FromWebFragment()
        }
    }
}