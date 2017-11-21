package mi.mobile.midevtest.model

import android.os.Parcel
import android.os.Parcelable
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
        open var location: Location? = null) : RealmObject(), Parcelable
{
    constructor(parcel: Parcel) : this(
            parcel.readValue(Int::class.java.classLoader) as? Int,
            parcel.readString(),
            parcel.readString(),
            parcel.readParcelable(Location::class.java.classLoader))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(imageUrl)
        parcel.writeString(description)
        parcel.writeParcelable(location, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Delivery> {
        override fun createFromParcel(parcel: Parcel): Delivery {
            return Delivery(parcel)
        }

        override fun newArray(size: Int): Array<Delivery?> {
            return arrayOfNulls(size)
        }
    }
}

