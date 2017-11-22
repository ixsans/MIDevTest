package mi.mobile.midevtest.model

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mi.mobile.midevtest.network.RestClient
import mi.mobile.midevtest.util.NetworkUtil

/**
 * Created by ikhsan on 21/11/17.
 */
class DeliveryListApi
{
    fun fetchDeliveries(onSuccess: (List<Delivery>) -> Unit, onFailed: (String) -> Unit): Disposable
    {
        val restClient = RestClient.create()
        return restClient
                .fetchDeliveries()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            result -> onSuccess.invoke(result)
                        },
                        {
                            e -> onFailed.invoke(NetworkUtil.getErrorMessage(e))
                        }
                )
    }
}