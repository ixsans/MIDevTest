package mi.mobile.midevtest.feature.deliveryDetail

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.gms.maps.OnMapReadyCallback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.row_delivery_item.*
import mi.mobile.midevtest.R
import mi.mobile.midevtest.model.Delivery
import mi.mobile.midevtest.util.RoundedTransformation
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import org.jetbrains.anko.support.v4.toast


class DeliveryDetailFragment : Fragment() {

    companion object {
        const val ARG_ITEM = "item"
        const val MAP_ZOOM_LEVEL = 16F
        const val MAP_ANIMATION_SPEED = 1000
    }

    private var mItem: Delivery? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments.containsKey(ARG_ITEM)) {
            mItem = arguments.getParcelable(DeliveryDetailFragment.ARG_ITEM)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_detail, container, false)

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View?, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)

        mItem?.let {
            text_address.text = it.location?.address + " (${it.location?.lat},${it.location?.lng})"
            text_description.text = it.description
            Picasso.with(context)
                    .load(it.imageUrl)
                    .error(R.drawable.default_thumbnail)
                    .placeholder(R.drawable.default_thumbnail)
                    .transform(RoundedTransformation(5, 5))
                    .resize(300, 300)
                    .onlyScaleDown()
                    .into(image_thumbnail)

            val mapFragment = childFragmentManager.findFragmentById(R.id.fragment_map)

            if (mapFragment != null)
            {
                mapFragment as SupportMapFragment
                mapFragment.getMapAsync({
                    map ->
                        val location = LatLng(mItem?.location?.lat!!, mItem?.location?.lng!!)
                        val marker = MarkerOptions()
                        marker.position(location)
                        marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_marker))
                        map.addMarker(marker)

                        val cameraPosition = CameraPosition.Builder()
                                .target(location)
                                .zoom(MAP_ZOOM_LEVEL)
                                .build()

                        map.animateCamera(
                                CameraUpdateFactory.newCameraPosition(cameraPosition),
                                MAP_ANIMATION_SPEED,
                                object : GoogleMap.CancelableCallback {
                                    override fun onFinish() {}
                                    override fun onCancel() {}
                                }
                        )
                })
            }else
            {
                toast("SSSS")
            }
        }
    }


}
