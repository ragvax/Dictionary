package com.ragvax.dictionary.data.source.remote

import com.ragvax.dictionary.domain.model.WordDefinition
import com.ragvax.dictionary.utils.Mapper
import javax.inject.Inject

class DefinitionMapper @Inject constructor() : Mapper<WordDefinitionEntity, WordDefinition> {

    override fun mapFromEntity(input: WordDefinitionEntity): WordDefinition {
        return WordDefinition(
            word = input.word,
            phonetics = input.phonetics?.mapPhonetics() ?: emptyList(),
            meanings = input.meanings?.mapMeanings() ?: emptyList(),
            isFavorite = false,
        )
    }

    override fun mapToEntity(input: WordDefinition): WordDefinitionEntity {
        return WordDefinitionEntity("Empty", null, null)
    }

    private fun List<Phonetic>.mapPhonetics(): List<WordDefinition.Phonetics> {
        return this.map {
            WordDefinition.Phonetics(
                text = it.text ?: "",
                audio = it.audio ?: "",
            )
        }
    }
    private fun List<Meaning>.mapMeanings(): List<WordDefinition.Meaning> {
        return this.map {
            WordDefinition.Meaning(
                definitions = it.definitions?.mapDefinitions() ?: emptyList(),
                partOfSpeech = it.partOfSpeech ?: "",
            )
        }
    }

    private fun List<Definition>.mapDefinitions(): List<WordDefinition.Meaning.Definition> {
        return this.map {
            WordDefinition.Meaning.Definition(
                definition = it.definition ?: "",
                example = it.example ?: "",
            )
        }
    }
}