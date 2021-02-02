package com.appcraft.testapp.app.ui.fragment.fromWeb

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appcraft.domain.model.TvShowItemMP
import com.appcraft.testapp.R
import com.appcraft.testapp.app.global.ui.fragment.BaseFragment
import com.appcraft.testapp.app.presentation.fromWeb.FromWebPresenter
import com.appcraft.testapp.app.presentation.fromWeb.FromWebView
import com.appcraft.testapp.app.ui.item.TvShowItem
import com.mikepenz.fastadapter.FastAdapter
import com.mikepenz.fastadapter.IItem
import com.mikepenz.fastadapter.adapters.ItemAdapter
import com.mikepenz.fastadapter.listeners.ClickEventHook
import kotlinx.android.synthetic.main.fragment_from_web.*
import kotlinx.android.synthetic.main.item_recycler.view.*
import moxy.presenter.InjectPresenter

class FromWebFragment : BaseFragment(R.layout.fragment_from_web), FromWebView {

    override var isLightStatusBar = false
    override var isLightNavigationBar: Boolean = false

    @InjectPresenter
    lateinit var presenter: FromWebPresenter

    private lateinit var itemAdapter: ItemAdapter<IItem<*>>
    private lateinit var fastAdapter: FastAdapter<IItem<*>>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        presenter.getTvShow(0)
    }

    private fun setupRecyclerView() {
        itemAdapter = ItemAdapter()
        fastAdapter = FastAdapter.with(itemAdapter)

        fastAdapter.onClickListener = {_, _, item, _ ->
            presenter.navigateToDetailFragment((item as TvShowItem).tvItem)
            false
        }

        fastAdapter.addEventHook(object : ClickEventHook<TvShowItem>() {
            override fun onBind(viewHolder: RecyclerView.ViewHolder): View? {
                //return the views on which you want to bind this event
                return if (viewHolder is TvShowItem.ViewHolder) {
                    viewHolder.containerView.rootView.favorite
                } else {
                    null
                }
            }

            override fun onClick(v: View, position: Int, fastAdapter: FastAdapter<TvShowItem>, item: TvShowItem) {
                //react on the click event
                presenter.saveTvShow(item.tvItem)
            }
        })

        recycler_view.adapter = fastAdapter
        recycler_view.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setListInAdapter(list: List<TvShowItemMP>) {
        itemAdapter.setNewList(list.map { TvShowItem(it) })
    }

    companion object {
        fun newInstance(): FromWebFragment {
            return FromWebFragment()
        }
    }
}