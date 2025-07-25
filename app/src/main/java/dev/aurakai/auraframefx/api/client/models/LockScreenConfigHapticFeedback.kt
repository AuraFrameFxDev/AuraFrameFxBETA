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
 * @param enabled
 * @param intensity
 */
@Serializable

data class LockScreenConfigHapticFeedback(

    @SerialName(value = "enabled")
    val enabled: kotlin.Boolean? = null,

    @SerialName(value = "intensity")
    val intensity: kotlin.Int? = null,

    ) : kotlin.collections.HashMap<String, kotlin.Any>()

