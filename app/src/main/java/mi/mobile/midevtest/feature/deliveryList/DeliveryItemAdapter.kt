package mi.mobile.midevtest.feature.deliveryList

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_delivery_item.view.*
import mi.mobile.midevtest.R
import mi.mobile.midevtest.feature.deliveryDetail.DeliveryDetailActivity
import mi.mobile.midevtest.feature.deliveryDetail.DeliveryDetailFragment
import mi.mobile.midevtest.model.Delivery
import mi.mobile.midevtest.util.RoundedTransformation
import org.jetbrains.anko.backgroundColor


/**
 * Created by ikhsan on 21/11/17.
 */
class DeliveryItemAdapter(private val mParentActivity: DeliveryListActivity,
                          private val mValues: List<Delivery>,
                          private val mTwoPane: Boolean) :
        RecyclerView.Adapter<DeliveryItemAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener
    private lateinit var mContext: Context
    private var selectedItem: Delivery? = null

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Delivery
            if (mTwoPane) {
                // set selection
                selectedItem = item
                notifyDataSetChanged()

                // show detail on the right pane
                val fragment = DeliveryDetailFragment().apply {
                    arguments = Bundle()
                    arguments.putParcelable(DeliveryDetailFragment.ARG_ITEM, item)
                }
                mParentActivity.supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.delivery_detail_container, fragment)
                        .commit()
            } else {
                val intent = Intent(v.context, DeliveryDetailActivity::class.java).apply {
                    putExtra(DeliveryDetailFragment.ARG_ITEM, item)
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        mContext = parent.context
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.row_delivery_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mDescriptionText.text = item.description
        holder.mAddressText.text = item.location?.address
        Picasso.with(mContext)
                .load(item.imageUrl)
                .error(R.drawable.ic_default_thumbnail)
                .placeholder(R.drawable.ic_default_thumbnail)
                .transform(RoundedTransformation(5, 5))
                .resize(300, 300)
                .onlyScaleDown()
                .into(holder.mThumbnailImage)

        // control selection for multi-pane layout
        if (selectedItem == item) {
            holder.itemView.backgroundColor = ContextCompat.getColor(mContext,
                    R.color.colorAccent)
        } else {
            holder.itemView.background = null
        }

        with(holder.itemView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
        val mDescriptionText: TextView = mView.text_description
        val mAddressText: TextView = mView.text_address
        val mThumbnailImage: ImageView = mView.image_thumbnail
    }
}