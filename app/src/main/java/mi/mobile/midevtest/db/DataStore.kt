package mi.mobile.midevtest.db

import io.realm.Realm
import mi.mobile.midevtest.model.Delivery


/**
 * Created by ikhsan on 21/11/17.
 */
class DataStore
{
    companion object
    {
        fun saveDeliveryItems(items: List<Delivery>)
        {
            val realm = Realm.getDefaultInstance()
            realm.beginTransaction()
            for (i in 0 until items.size) {
                val item = items[i]
                item.id = i + 1
                realm.copyToRealmOrUpdate(item)
            }
            realm.commitTransaction()
        }

        fun loadLocalDeliveryItems() : List<Delivery>
        {
            val realm = Realm.getDefaultInstance()
            return realm.where(Delivery::class.java).findAll()
        }
    }
}