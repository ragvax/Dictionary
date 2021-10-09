package com.ragvax.dictionary.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordDefinition(
    val word: String,
    val phonetics: List<Phonetics>,
    val meanings: List<Meaning>,
    val isFavorite: Boolean,
) : Parcelable {

    @Parcelize
    data class Phonetics(
        val text: String,
        val audio: String,
    ) : Parcelable

    @Parcelize
    data class Meaning(
        val definitions: List<Definition>,
        val partOfSpeech: String,
    ) : Parcelable {

        @Parcelize
        data class Definition(
            val definition: String,
            val example: String,
        ) : Parcelable
    }
}