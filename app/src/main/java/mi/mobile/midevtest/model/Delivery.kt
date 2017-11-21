package mi.mobile.midevtest.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by ikhsan on 21/11/17.
 */
open class Delivery(open var description: String? = null,
                    open val imageUrl: String? = null,
                    open val location: Location? = null) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            TODO("location"))

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(description)
        parcel.writeString(imageUrl)
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

