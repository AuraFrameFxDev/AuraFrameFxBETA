/*
 * AuraFrameFX Ecosystem API
 * A comprehensive API for interacting with the AuraFrameFX AI Super Dimensional Ecosystem. Provides access to generative AI capabilities, system customization, user management, and core application features.
 *
 * The version of the OpenAPI document: 1.0.0
 * Contact: support@auraframefx.com
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.model;

import java.util.Objects;

import com.google.gson.annotations.SerializedName;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.JsonElement;
import com.google.gson.annotations.JsonAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

/**
 * Type of AI agent
 */
@JsonAdapter(AgentType.Adapter.class)
public enum AgentType {

    AURA("Aura"),

    KAI("Kai"),

    GENESIS("Genesis"),

    CASCADE("Cascade"),

    NEURAL_WHISPER("NeuralWhisper"),

    AURA_SHIELD("AuraShield"),

    GEN_KIT_MASTER("GenKitMaster");

    private String value;

    AgentType(String value) {
        this.value = value;
    }

    public static AgentType fromValue(String value) {
        for (AgentType b : AgentType.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value '" + value + "'");
    }

    public static void validateJsonElement(JsonElement jsonElement) throws IOException {
        String value = jsonElement.getAsString();
        AgentType.fromValue(value);
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

    public static class Adapter extends TypeAdapter<AgentType> {
        @Override
        public void write(final JsonWriter jsonWriter, final AgentType enumeration) throws IOException {
            jsonWriter.value(enumeration.getValue());
        }

        @Override
        public AgentType read(final JsonReader jsonReader) throws IOException {
            String value = jsonReader.nextString();
            return AgentType.fromValue(value);
        }
    }
}

