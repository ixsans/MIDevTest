package mi.mobile.midevtest.feature.deliveryList

import mi.mobile.midevtest.db.DataStore
import mi.mobile.midevtest.model.DeliveryListApi

/**
 * Created by ikhsan on 21/11/17.
 */
class DeliveryListPresenter(private var view: DeliveryListContract.View,
                            private var api: DeliveryListApi) : DeliveryListContract.Presenter
{
    override fun fetchDeliveries()
    {
        view.showLoading()

        val items = DataStore.loadLocalDeliveryItems()
        if (items.isNotEmpty()) {
            view.showData(items)
        }

        api.fetchDeliveries({ result ->
            if (result.isNotEmpty()) DataStore.saveDeliveryItems(result)
            view.showData(result)
            view.showLoading(false)
        }, { error ->
            view.onError(error)
            view.showLoading(false)
        })


    }
}