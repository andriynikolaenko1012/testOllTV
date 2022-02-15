package com.test.testoll.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

data class PagedResponse(
    @SerializedName("block_id") var blockID: String,
    @SerializedName("block_title") var blockTitle: String,
    @SerializedName("block_type") var blockType: String,
    @SerializedName("category_name") var categoryName: String,
    @SerializedName("first_now_index") var firstNowIndex: Int,
    @SerializedName("hasMore") var hasMore: Int,
    @SerializedName("items") var items: List<Item>,
    @SerializedName("items_number") var itemsNumber: Int,
    @SerializedName("offset") var offset: Int,
    @SerializedName("total") var total: Int
)

data class Item(
    @SerializedName("id") var id: Int,
    @SerializedName("channel_id") var channelID: Int,
    @SerializedName("channel_name") var channelName: String,
    @SerializedName("alias") var alias: String,
    @SerializedName("icon") var icon: String,
    @SerializedName("name") var name: String,
    @SerializedName("description") var description: String,
    @SerializedName("start") var start: String,
    @SerializedName("start_ts") var startTS: Int,
    @SerializedName("stop") var stop: String,
    @SerializedName("stop_ts") var stopTS: Int,
    @SerializedName("is_free") var isFree: Int,
    @SerializedName("is_favorite") var isFavorite: Int,
    @SerializedName("under_parental_protect") var underParentalProtect: Int,
    @SerializedName("dvr") var dvr: Int,
    @SerializedName("now") var now: Int,
    @SerializedName("FK_catalog") var FKcatalog: Int,
    @SerializedName("subs") var subs: Subs
)

data class Subs(
    @SerializedName("id") var id: Int,
    @SerializedName("price") var price: Int,
    @SerializedName("name") var name: String,
    @SerializedName("subsId") var subsId: Int,
    @SerializedName("type") var type: String
)