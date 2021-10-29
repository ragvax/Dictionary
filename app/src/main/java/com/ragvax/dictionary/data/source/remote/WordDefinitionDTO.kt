package com.ragvax.dictionary.data.source.remote

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WordDefinitionDTO(
    val word: String,
    val origin: String?,
    val phonetics: List<PhoneticDTO>?,
    val meanings: List<MeaningDTO>?
) : Parcelable

@Parcelize
data class PhoneticDTO(
    val text: String?,
    val audio: String?
) : Parcelable

@Parcelize
data class MeaningDTO(
    val definitions: List<DefinitionDTO>?,
    val partOfSpeech: String?
) : Parcelable

@Parcelize
data class DefinitionDTO(
    val definition: String?,
    val example: String?,
    val synonyms: List<String>?,
    val antonyms: List<String>?
) : Parcelable