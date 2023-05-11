package com.example.mvvmapp.network.model

import com.google.gson.annotations.SerializedName

class RecipeDto (

    @SerializedName("pk")
    var pk: Int? = null,

    @SerializedName("tittle")
    var tittle: String? = null,

    @SerializedName("featured_image")
    var featuredImage: String? = null,

    @SerializedName("publisher")
    var publisher: String? = null,

    @SerializedName("rating")
    var rating: Int? = null,

    @SerializedName("source_url")
    var sourceUrl: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("cookingInstructions")
    var cookingInstructions: String? = null,

    @SerializedName("ingredients")
    var ingredients: List<String>? = null,

    @SerializedName("date_added")
    var dateAdded: String? = null,

    @SerializedName("date_updated")
    var dateUpdated: String? = null,
)