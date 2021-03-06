package mi.mobile.midevtest.feature.deliveryList

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.app_bar.*
import kotlinx.android.synthetic.main.item_list.*
import kotlinx.android.synthetic.main.view_empty.*
import kotlinx.android.synthetic.main.view_error.*
import mi.mobile.midevtest.R
import mi.mobile.midevtest.model.Delivery
import mi.mobile.midevtest.model.DeliveryListApi
import mi.mobile.midevtest.util.Util
import org.jetbrains.anko.support.v4.onRefresh


class DeliveryListActivity : AppCompatActivity(), DeliveryListContract.View, OnRefreshListener {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var mTwoPane: Boolean = false

    private lateinit var mPresenter: DeliveryListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delivery_list)

        setupViews()

        //init presenter
        mPresenter = DeliveryListPresenter(this, DeliveryListApi())

        //fetch data
        onRefresh()
    }

    private fun setupViews() {
        //setup toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.title = getString(R.string.title_delivery_list)
        Util.changeToolbarFont(toolbar, this)

        //define layout type
        if (delivery_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }

        //setup swipe refresh layour
        swipe_refresh.onRefresh { onRefresh() }
    }

    override fun onRefresh() {
        mPresenter.fetchDeliveries()
    }

    override fun showLoading(loading: Boolean) {
        swipe_refresh.post({
            swipe_refresh.isRefreshing = loading
        })
    }

    override fun showData(deliveries: List<Delivery>) {
        view_error.visibility = View.GONE
        item_list.visibility = View.VISIBLE
        item_list.adapter = DeliveryItemAdapter(this, deliveries, mTwoPane)
    }

    override fun onEmptyData() {
        view_error.visibility = View.GONE
        item_list.visibility = View.GONE
        view_empty.visibility = View.VISIBLE
        text_empty_message.text = getString(R.string.empty_message)
    }

    override fun onError(errorMessage: String) {
        if(item_list.adapter == null || item_list.adapter.itemCount == 0)
        {
            view_error.visibility = View.VISIBLE
            item_list.visibility = View.GONE
        }
        Snackbar.make(toolbar, errorMessage, Snackbar.LENGTH_LONG).show()
    }


}
