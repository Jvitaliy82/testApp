package com.appCraft.testApp.ui.fragment.detail

import android.os.Bundle
import android.view.View
import com.appCraft.domain.model.TvDetailModel
import com.appCraft.testApp.R
import com.appCraft.testApp.global.ui.fragment.BaseFragment
import com.appCraft.testApp.presentation.detail.DetailPresenter
import com.appCraft.testApp.presentation.detail.DetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import moxy.presenter.InjectPresenter

class DetailFragment : BaseFragment(R.layout.fragment_detail), DetailView {

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            presenter.id = it.getString(ID, "")
        }
        presenter.getTvDetail()
    }

    override fun setData(tvDetail: TvDetailModel) {
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