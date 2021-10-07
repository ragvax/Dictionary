package com.ragvax.dictionary.data.source.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Word(
    val word: String,
    val phonetics: List<Phonetic>?,
    val meanings: List<Meaning>?
) : Parcelable

@Parcelize
data class Phonetic(
    val text: String?,
    val audio: String?
) : Parcelable

@Parcelize
data class Meaning(
    val definitions: List<Definition>?,
    val partOfSpeech: String?
) : Parcelable

@Parcelize
data class Definition(
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?
) : Parcelable