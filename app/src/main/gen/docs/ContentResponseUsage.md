# ContentResponseUsage


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**prompt_tokens** | **int** |  | [optional] 
**completion_tokens** | **int** |  | [optional] 
**total_tokens** | **int** |  | [optional] 

## Example

```python
from openapi_client.models.content_response_usage import ContentResponseUsage

# TODO update the JSON string below
json = "{}"
# create an instance of ContentResponseUsage from a JSON string
content_response_usage_instance = ContentResponseUsage.from_json(json)
# print the JSON string representation of the object
print(ContentResponseUsage.to_json())

# convert the object into a dict
content_response_usage_dict = content_response_usage_instance.to_dict()
# create an instance of ContentResponseUsage from a dict
content_response_usage_from_dict = ContentResponseUsage.from_dict(content_response_usage_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


