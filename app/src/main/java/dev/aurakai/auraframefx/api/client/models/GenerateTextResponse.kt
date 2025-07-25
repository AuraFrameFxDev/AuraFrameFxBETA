/**
 *
 * Please note:
 * This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * Do not edit this file manually.
 *
 */

@file:Suppress(
    "ArrayInDataClass",
    "EnumEntryName",
    "RemoveRedundantQualifierName",
    "UnusedImport"
)

package dev.aurakai.auraframefx.api.client.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 *
 *
 * @param generatedText The AI-generated text.
 * @param finishReason Reason for generation completion (e.g., STOP, MAX_TOKENS).
 */
@Serializable

data class GenerateTextResponse(

    /* The AI-generated text. */
    @SerialName(value = "generatedText")
    val generatedText: kotlin.String,

    /* Reason for generation completion (e.g., STOP, MAX_TOKENS). */
    @SerialName(value = "finishReason")
    val finishReason: kotlin.String? = null,

    )

