package mi.mobile.midevtest.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmClass

/**
 * Created by ikhsan on 21/11/17.
 */
@RealmClass
open class Delivery(
        @PrimaryKey open var id: Int? = null,
        open var imageUrl: String? = null,
        open var description: String? = null,
        open var location: Location? = null) : RealmObject()

