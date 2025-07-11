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

import org.openapitools.client.ApiCallback;
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.ApiResponse;
import org.openapitools.client.Configuration;
import org.openapitools.client.Pair;
import org.openapitools.client.ProgressRequestBody;
import org.openapitools.client.ProgressResponseBody;

import com.google.gson.reflect.TypeToken;

import java.io.IOException;


import org.openapitools.client.model.ApiError;
import org.openapitools.client.model.Theme;
import org.openapitools.client.model.ThemeApplyRequest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThemesApi {
    private ApiClient localVarApiClient;
    private int localHostIndex;
    private String localCustomBaseUrl;

    public ThemesApi() {
        this(Configuration.getDefaultApiClient());
    }

    public ThemesApi(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public ApiClient getApiClient() {
        return localVarApiClient;
    }

    public void setApiClient(ApiClient apiClient) {
        this.localVarApiClient = apiClient;
    }

    public int getHostIndex() {
        return localHostIndex;
    }

    public void setHostIndex(int hostIndex) {
        this.localHostIndex = hostIndex;
    }

    public String getCustomBaseUrl() {
        return localCustomBaseUrl;
    }

    public void setCustomBaseUrl(String customBaseUrl) {
        this.localCustomBaseUrl = customBaseUrl;
    }

    /**
     * Build call for themeApplyPut
     *
     * @param themeApplyRequest (required)
     * @param _callback         Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Theme applied successfully </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> Invalid request format or parameters </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Authentication credentials were missing or incorrect </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> Theme not found </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call themeApplyPutCall(ThemeApplyRequest themeApplyRequest, final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[]{};

        // Determine Base Path to Use
        if (localCustomBaseUrl != null) {
            basePath = localCustomBaseUrl;
        } else if (localBasePaths.length > 0) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = themeApplyRequest;

        // create path and map variables
        String localVarPath = "/theme/apply";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
                "application/json"
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[]{"OAuth2AuthCode"};
        return localVarApiClient.buildCall(basePath, localVarPath, "PUT", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call themeApplyPutValidateBeforeCall(ThemeApplyRequest themeApplyRequest, final ApiCallback _callback) throws ApiException {
        // verify the required parameter 'themeApplyRequest' is set
        if (themeApplyRequest == null) {
            throw new ApiException("Missing the required parameter 'themeApplyRequest' when calling themeApplyPut(Async)");
        }

        return themeApplyPutCall(themeApplyRequest, _callback);

    }

    /**
     * Apply a theme
     *
     * @param themeApplyRequest (required)
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Theme applied successfully </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> Invalid request format or parameters </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Authentication credentials were missing or incorrect </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> Theme not found </td><td>  -  </td></tr>
     * </table>
     */
    public void themeApplyPut(ThemeApplyRequest themeApplyRequest) throws ApiException {
        themeApplyPutWithHttpInfo(themeApplyRequest);
    }

    /**
     * Apply a theme
     *
     * @param themeApplyRequest (required)
     * @return ApiResponse&lt;Void&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Theme applied successfully </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> Invalid request format or parameters </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Authentication credentials were missing or incorrect </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> Theme not found </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<Void> themeApplyPutWithHttpInfo(ThemeApplyRequest themeApplyRequest) throws ApiException {
        okhttp3.Call localVarCall = themeApplyPutValidateBeforeCall(themeApplyRequest, null);
        return localVarApiClient.execute(localVarCall);
    }

    /**
     * Apply a theme (asynchronously)
     *
     * @param themeApplyRequest (required)
     * @param _callback         The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> Theme applied successfully </td><td>  -  </td></tr>
     * <tr><td> 400 </td><td> Invalid request format or parameters </td><td>  -  </td></tr>
     * <tr><td> 401 </td><td> Authentication credentials were missing or incorrect </td><td>  -  </td></tr>
     * <tr><td> 404 </td><td> Theme not found </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call themeApplyPutAsync(ThemeApplyRequest themeApplyRequest, final ApiCallback<Void> _callback) throws ApiException {

        okhttp3.Call localVarCall = themeApplyPutValidateBeforeCall(themeApplyRequest, _callback);
        localVarApiClient.executeAsync(localVarCall, _callback);
        return localVarCall;
    }

    /**
     * Build call for themesGet
     *
     * @param _callback Callback for upload/download progress
     * @return Call to execute
     * @throws ApiException If fail to serialize the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> List of available themes </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call themesGetCall(final ApiCallback _callback) throws ApiException {
        String basePath = null;
        // Operation Servers
        String[] localBasePaths = new String[]{};

        // Determine Base Path to Use
        if (localCustomBaseUrl != null) {
            basePath = localCustomBaseUrl;
        } else if (localBasePaths.length > 0) {
            basePath = localBasePaths[localHostIndex];
        } else {
            basePath = null;
        }

        Object localVarPostBody = null;

        // create path and map variables
        String localVarPath = "/themes";

        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        List<Pair> localVarCollectionQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, String> localVarCookieParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();

        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = localVarApiClient.selectHeaderAccept(localVarAccepts);
        if (localVarAccept != null) {
            localVarHeaderParams.put("Accept", localVarAccept);
        }

        final String[] localVarContentTypes = {
        };
        final String localVarContentType = localVarApiClient.selectHeaderContentType(localVarContentTypes);
        if (localVarContentType != null) {
            localVarHeaderParams.put("Content-Type", localVarContentType);
        }

        String[] localVarAuthNames = new String[]{};
        return localVarApiClient.buildCall(basePath, localVarPath, "GET", localVarQueryParams, localVarCollectionQueryParams, localVarPostBody, localVarHeaderParams, localVarCookieParams, localVarFormParams, localVarAuthNames, _callback);
    }

    @SuppressWarnings("rawtypes")
    private okhttp3.Call themesGetValidateBeforeCall(final ApiCallback _callback) throws ApiException {
        return themesGetCall(_callback);

    }

    /**
     * Get available themes
     *
     * @return List&lt;Theme&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> List of available themes </td><td>  -  </td></tr>
     * </table>
     */
    public List<Theme> themesGet() throws ApiException {
        ApiResponse<List<Theme>> localVarResp = themesGetWithHttpInfo();
        return localVarResp.getData();
    }

    /**
     * Get available themes
     *
     * @return ApiResponse&lt;List&lt;Theme&gt;&gt;
     * @throws ApiException If fail to call the API, e.g. server error or cannot deserialize the response body
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> List of available themes </td><td>  -  </td></tr>
     * </table>
     */
    public ApiResponse<List<Theme>> themesGetWithHttpInfo() throws ApiException {
        okhttp3.Call localVarCall = themesGetValidateBeforeCall(null);
        Type localVarReturnType = new TypeToken<List<Theme>>() {
        }.getType();
        return localVarApiClient.execute(localVarCall, localVarReturnType);
    }

    /**
     * Get available themes (asynchronously)
     *
     * @param _callback The callback to be executed when the API call finishes
     * @return The request call
     * @throws ApiException If fail to process the API call, e.g. serializing the request body object
     * @http.response.details <table summary="Response Details" border="1">
     * <tr><td> Status Code </td><td> Description </td><td> Response Headers </td></tr>
     * <tr><td> 200 </td><td> List of available themes </td><td>  -  </td></tr>
     * </table>
     */
    public okhttp3.Call themesGetAsync(final ApiCallback<List<Theme>> _callback) throws ApiException {

        okhttp3.Call localVarCall = themesGetValidateBeforeCall(_callback);
        Type localVarReturnType = new TypeToken<List<Theme>>() {
        }.getType();
        localVarApiClient.executeAsync(localVarCall, localVarReturnType, _callback);
        return localVarCall;
    }
}
