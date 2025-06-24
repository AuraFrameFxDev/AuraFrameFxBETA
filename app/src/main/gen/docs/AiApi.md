# openapi_client.AiApi

All URIs are relative to *https://api.example.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**generate_content**](AiApi.md#generate_content) | **POST** /generateContent | Generate AI content


# **generate_content**
> ContentResponse generate_content(content_request)

Generate AI content

Generate content using AI models

### Example


```python
import openapi_client
from openapi_client.models.content_request import ContentRequest
from openapi_client.models.content_response import ContentResponse
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
    api_instance = openapi_client.AiApi(api_client)
    content_request = openapi_client.ContentRequest() # ContentRequest | 

    try:
        # Generate AI content
        api_response = api_instance.generate_content(content_request)
        print("The response of AiApi->generate_content:\n")
        pprint(api_response)
    except Exception as e:
        print("Exception when calling AiApi->generate_content: %s\n" % e)
```



### Parameters


Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **content_request** | [**ContentRequest**](ContentRequest.md)|  | 

### Return type

[**ContentResponse**](ContentResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

### HTTP response details

| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | Content generated successfully |  -  |
**400** | Invalid request |  -  |

[[Back to top]](#) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to Model list]](../README.md#documentation-for-models) [[Back to README]](../README.md)

