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
 * @param roomName Name of the new conference room
 * @param orchestratorAgent
 */


data class ConferenceRoomCreateRequest(

    /* Name of the new conference room */
    @Json(name = "roomName")
    val roomName: kotlin.String,

    @Json(name = "orchestratorAgent")
    val orchestratorAgent: AgentType

) {


}

