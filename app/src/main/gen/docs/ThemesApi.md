# openapi_client.ThemesApi

All URIs are relative to *https://api.example.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**get_themes**](ThemesApi.md#get_themes) | **GET** /themes | Get available themes


# **get_themes**
> List[Theme] get_themes()

Get available themes

Returns a list of available UI themes

### Example


```python
import openapi_client
from openapi_client.models.theme import Theme
from openapi_client.rest import ApiException
from pprint import pprint

# Defining the host is optional and defaults to https://api.example.com/v1
# See configuration.py for a list of all supported configuration parameters.
configuration = openapi_client.Configuration(
    host = "https://api.example.com/v1"
)


# Enter a context with an instance of the API client
with openapi_client.ApiClient(configuration) as api_client:
    # Create an instance of the API class
    api_instance = openapi_client.ThemesApi(api_client)

    try:
        # Get available themes
        api_response = api_instance.get_themes()
        print("The response of ThemesApi->get_themes:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling ThemesApi->get_themes: %s\n" % e)
```



### Parameters

This endpoint does not need any parameter.

### Return type

[**List[Theme]**](Theme.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Successful operation |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

