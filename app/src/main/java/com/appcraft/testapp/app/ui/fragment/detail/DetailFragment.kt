package com.appcraft.testapp.app.ui.fragment.detail

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.appcraft.domain.model.TvDetailItem
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.appcraft.testapp.app.presentation.detail.DetailPresenter
import com.appcraft.testapp.app.presentation.detail.DetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import moxy.presenter.InjectPresenter
import pro.appcraft.lib.utils.common.addSystemWindowInsetToMargin

class DetailFragment : BaseFragment(R.layout.fragment_detail), DetailView {

    override var isLightStatusBar = false
    override var isLightNavigationBar: Boolean = false

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container.addSystemWindowInsetToMargin(top = true, bottom = true)
        presenter.setParams(
            arguments?.getParcelable(ID)!!
        )

        presenter.getTvDetail()
        save_button.setOnClickListener {
            presenter.saveCurrent()
        }
    }

    override fun setData(tvDetailItem: TvDetailItem) {
        Glide.with(requireView())
            .load(tvDetailItem.imagePath)
            .into(imageTVShow)
        textName.text = tvDetailItem.name
        textNetworkCountry.text = tvDetailItem.country
        textStatus.text = tvDetailItem.status
        textStarted.text = tvDetailItem.startDate
        textDescription.text = tvDetailItem.description
    }

    override fun visibleSaveButton(isVisible: Boolean) {
        save_button.isVisible = isVisible
    }

    companion object {
        const val ID = "ID"
    }

}