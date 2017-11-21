package mi.mobile.midevtest.feature.deliveryDetail

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import mi.mobile.midevtest.R
import mi.mobile.midevtest.model.Delivery

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [DeliveryListActivity]
 * in two-pane mode (on tablets) or a [DeliveryDetailActivity]
 * on handsets.
 */
class DeliveryDetailFragment : Fragment() {

    private var mItem: Delivery? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.containsKey(ARG_ITEM)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            /*mItem = arguments.getParcelable<ARG_ITEM>()
            mItem?.let {
                activity.toolbar_layout?.title = it.description
            }*/
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        // Show the dummy content as text in a TextView.
        mItem?.let {
            rootView.item_detail.text = it.description
        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM = "item"
    }
}
