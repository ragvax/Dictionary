package com.ragvax.dictionary.data.source.remote

import com.ragvax.dictionary.domain.model.WordDefinition
import com.ragvax.dictionary.utils.Mapper
import javax.inject.Inject

class DefinitionMapper @Inject constructor() : Mapper<WordDefinitionDTO, WordDefinition> {

    fun mapFromDTO(input: WordDefinitionDTO): WordDefinition {
        return WordDefinition(
            word = input.word,
            phonetics = input.phonetics?.mapPhonetics() ?: emptyList(),
            meanings = input.meanings?.mapMeanings() ?: emptyList(),
            isFavorite = false,
        )
    }

    private fun List<PhoneticDTO>.mapPhonetics(): List<WordDefinition.Phonetics> {
        return this.map {
            WordDefinition.Phonetics(
                text = it.text ?: "",
                audio = it.audio ?: "",
            )
        }
    }

    private fun List<MeaningDTO>.mapMeanings(): List<WordDefinition.Meaning> {
        return this.map {
            WordDefinition.Meaning(
                definitions = it.definitions?.mapDefinitions() ?: emptyList(),
                partOfSpeech = it.partOfSpeech ?: "",
            )
        }
    }

    private fun List<DefinitionDTO>.mapDefinitions(): List<WordDefinition.Meaning.Definition> {
        return this.map {
            WordDefinition.Meaning.Definition(
                definition = it.definition ?: "",
                example = it.example ?: "",
            )
        }
    }
}