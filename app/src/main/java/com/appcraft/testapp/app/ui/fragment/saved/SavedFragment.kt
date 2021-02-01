package com.appcraft.testapp.app.ui.fragment.saved

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.appcraft.testapp.app.presentation.saved.SavedPresenter
import com.appcraft.testapp.app.presentation.saved.SavedView
import com.appcraft.testapp.app.ui.fragment.fromWeb.TvShowXAdapter
import kotlinx.android.synthetic.main.fragment_from_web.*
import moxy.presenter.InjectPresenter

class SavedFragment: BaseFragment(R.layout.fragment_saved), SavedView, TvShowXAdapter.OnItemClickListener {

    @InjectPresenter
    lateinit var presenter: SavedPresenter

    private lateinit var tvShowXAdapter: TvShowXAdapter

    companion object {
        fun newInstance(): SavedFragment {
            return SavedFragment()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.getAll()
    }

    private fun setupRecyclerView() = recycler_view.apply {
        tvShowXAdapter = TvShowXAdapter(this@SavedFragment)
        adapter = tvShowXAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onItemClick(tvShowItemMP: TvShowItemMP) {
        TODO("Not yet implemented")
    }

    override fun addFavorite(tvShowItemMP: TvShowItemMP) {
        //no action
    }

    override fun setListInAdapter(list: List<TvShowItemMP>) {
        tvShowXAdapter.submitList(list)
    }
}