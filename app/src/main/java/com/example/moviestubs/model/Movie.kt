package com.example.moviestubs.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    @SerializedName("Id")
    val id: Int,

    @SerializedName("Name")
    val name: String,

    @SerializedName("Rank")
    val rank: Int,

    @SerializedName("Duration")
    val duration: String,

    @SerializedName("Description")
    val description: String,

    @SerializedName("Director")
    val director: String,

    @SerializedName("Genres")
    val genres: Array<String>,

    @SerializedName("Actors")
    val actors: Array<String>,
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Movie

        if (id != other.id) return false
        if (name != other.name) return false
        if (rank != other.rank) return false
        if (duration != other.duration) return false
        if (description != other.description) return false
        if (director != other.director) return false
        if (!genres.contentEquals(other.genres)) return false
        if (!actors.contentEquals(other.actors)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + rank
        result = 31 * result + duration.hashCode()
        result = 31 * result + description.hashCode()
        result = 31 * result + director.hashCode()
        result = 31 * result + genres.contentHashCode()
        result = 31 * result + actors.contentHashCode()
        return result
    }
}