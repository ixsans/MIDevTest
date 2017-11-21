package mi.mobile.midevtest.model

import android.os.Parcel
import android.os.Parcelable
import io.realm.RealmObject
import io.realm.annotations.RealmClass

/**
 * Created by ikhsan on 21/11/17.
 */
@RealmClass
open class Location ( open var lat: Double? = null,
                      open var lng: Double? = null,
                      open var address: String? = "") : RealmObject(), Parcelable
{
    constructor(parcel: Parcel) : this(
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readValue(Double::class.java.classLoader) as? Double,
            parcel.readString())

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(lat)
        parcel.writeValue(lng)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Location> {
        override fun createFromParcel(parcel: Parcel): Location {
            return Location(parcel)
        }

        override fun newArray(size: Int): Array<Location?> {
            return arrayOfNulls(size)
        }
    }
}