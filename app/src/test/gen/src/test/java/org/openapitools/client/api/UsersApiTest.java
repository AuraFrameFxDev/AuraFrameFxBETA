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


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.ApiError;
import org.openapitools.client.model.User;
import org.openapitools.client.model.UserPreferencesUpdate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for UsersApi
 */
@Disabled
public class UsersApiTest {

    private final UsersApi api = new UsersApi();

    /**
     * Get current user information
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userGetTest() throws ApiException {
        User response = api.userGet();
        // TODO: test validations
    }

    /**
     * Update user preferences
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void userPreferencesPutTest() throws ApiException {
        UserPreferencesUpdate userPreferencesUpdate = null;
        api.userPreferencesPut(userPreferencesUpdate);
        // TODO: test validations
    }

}
