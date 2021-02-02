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
import pro.appcraft.lib.utils.dialog.AlertDialogAction
import pro.appcraft.lib.utils.dialog.AlertDialogType
import pro.appcraft.lib.utils.dialog.showAlertDialog
import java.util.*

class SavedFragment: BaseFragment(R.layout.fragment_saved), SavedView, TvShowXAdapter.OnItemClickListener {

    override var isLightStatusBar = false
    override var isLightNavigationBar: Boolean = false

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
        presenter.navigateToDetailFragment(tvShowItemMP)
    }

    override fun onFavoriteClick(tvShowItemMP: TvShowItemMP) {
        showBookGroupDeletionDialog(tvShowItemMP)
    }

    override fun setListInAdapter(list: List<TvShowItemMP>) {
        tvShowXAdapter.submitList(list)
    }

    private fun showBookGroupDeletionDialog(tvShowItemMP: TvShowItemMP) {
        requireContext().showAlertDialog(
            type = AlertDialogType.ALERT_HORIZONTAL_2_OPTIONS_RIGHT_ACCENT,
            header = getString(R.string.delete_dialog_header),
            message = String.format(
                Locale.getDefault(), getString(R.string.delete_item_dialog_message),
                tvShowItemMP.name
            ),
            actions = listOf(
                AlertDialogAction(getString(R.string.cancel)) {
                    it.dismiss()
                },
                AlertDialogAction(getString(R.string.delete)) {
                    it.dismiss()
                    presenter.deleteItem(tvShowItemMP)
                }
            )
        )
    }
}