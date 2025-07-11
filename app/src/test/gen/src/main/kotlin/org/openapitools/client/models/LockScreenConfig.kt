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

import org.openapitools.client.models.LockScreenConfigAnimation
import org.openapitools.client.models.LockScreenConfigClockConfig
import org.openapitools.client.models.LockScreenConfigHapticFeedback

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 *
 * @param clockConfig
 * @param animation
 * @param hapticFeedback
 */


data class LockScreenConfig(

    @Json(name = "clockConfig")
    val clockConfig: LockScreenConfigClockConfig? = null,

    @Json(name = "animation")
    val animation: LockScreenConfigAnimation? = null,

    @Json(name = "hapticFeedback")
    val hapticFeedback: LockScreenConfigHapticFeedback? = null

) {


}

