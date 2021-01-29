package com.appcraft.testapp.ui.fragment.detail

import android.os.Bundle
import android.view.View
import com.appcraft.domain.model.TvDetailModel
import com.appcraft.testapp.R
import com.appcraft.testapp.global.ui.fragment.BaseFragment
import com.appcraft.testapp.presentation.detail.DetailPresenter
import com.appcraft.testapp.presentation.detail.DetailView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import moxy.presenter.InjectPresenter

class DetailFragment : BaseFragment(R.layout.fragment_detail), DetailView {

    @InjectPresenter
    lateinit var presenter: DetailPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            presenter.id = it.getInt(ID)
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