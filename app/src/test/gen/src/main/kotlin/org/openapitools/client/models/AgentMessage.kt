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

package org.openapitools.client.models

import org.openapitools.client.models.AgentType

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @param sender
 * @param message The content of the message
 * @param timestamp ISO 8601 timestamp of the message
 */


data class AgentMessage(

    @Json(name = "sender")
    val sender: AgentType,

    /* The content of the message */
    @Json(name = "message")
    val message: kotlin.String,

    /* ISO 8601 timestamp of the message */
    @Json(name = "timestamp")
    val timestamp: java.time.OffsetDateTime

) {


}

