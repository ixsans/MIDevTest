package mi.mobile.midevtest.model

import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by ikhsan on 21/11/17.
 */
@RealmClass
open class Location ( open var lat: Double? = null,
                      open var lng: Double? = null,
                      open var address: String? = "") : RealmObject()