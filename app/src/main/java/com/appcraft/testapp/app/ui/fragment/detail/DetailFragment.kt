package com.appcraft.testapp.app.ui.fragment.detail

import android.os.Bundle
import android.view.View
import com.appcraft.domain.model.TvDetail
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.appcraft.testapp.app.presentation.detail.DetailPresenter
import com.appcraft.testapp.app.presentation.detail.DetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import moxy.presenter.InjectPresenter
import pro.appcraft.lib.utils.common.addSystemWindowInsetToMargin

class DetailFragment : BaseFragment(R.layout.fragment_detail), DetailView {

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        container.addSystemWindowInsetToMargin(top = true, bottom = true)
        arguments?.let {
            presenter.currentItem = it.getParcelable(ID)

        }
        presenter.getTvDetail()
        save_button.setOnClickListener {
            presenter.saveCurrent()
        }
    }

    override fun setData(tvDetail: TvDetail) {
        Glide.with(requireView())
            .load(tvDetail.tvShow.image_path)
            .into(imageTVShow)
        textName.text = tvDetail.tvShow.name
        textNetworkCountry.text = tvDetail.tvShow.country
        textStatus.text = tvDetail.tvShow.status
        textStarted.text = tvDetail.tvShow.start_date
        textDescription.text = tvDetail.tvShow.description
    }

    companion object {
        const val ID = "ID"
    }

}