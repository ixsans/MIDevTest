package mi.mobile.midevtest.feature.deliveryList

import mi.mobile.midevtest.model.Delivery

/**
 * Created by ikhsan on 21/11/17.
 */
class DeliveryListContract
{
    interface View
    {
        fun showLoading(loading: Boolean = true)

        fun showData(deliveries: List<Delivery>)

        fun onEmptyData()

        fun onError(errorMessage: String)
    }

    interface Presenter
    {
        fun fetchDeliveries()
    }
}