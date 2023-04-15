package com.example.intelligent_shopping_cart.model

import android.os.Parcel
import android.os.Parcelable
import com.tencent.tencentmap.mapsdk.maps.model.LatLng

data class Mark(
    val icon: Int,
    val title: String,
    val snippet: String,
    val latLng: LatLng = LatLng()
) : Parcelable {

    // Parcelable的构造函数
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readParcelable(LatLng::class.java.classLoader)!!
    )

    // writeToParcel()方法，将对象写入Parcel中
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(icon)
        parcel.writeString(title)
        parcel.writeString(snippet)
        parcel.writeParcelable(latLng, flags)
    }

    // describeContents()方法，返回对象的描述信息，默认返回0即可
    override fun describeContents(): Int {
        return 0
    }

    // Companion object，用于创建Parcelable的实例
    companion object CREATOR : Parcelable.Creator<Mark> {
        override fun createFromParcel(parcel: Parcel): Mark {
            return Mark(parcel)
        }

        override fun newArray(size: Int): Array<Mark?> {
            return arrayOfNulls(size)
        }
    }
}
