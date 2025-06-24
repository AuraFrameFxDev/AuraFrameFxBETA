# ContentRequest


## Properties

Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**prompt** | **str** | The prompt to generate content from | 
**max_length** | **int** | Maximum length of generated content | [optional] 
**options** | **Dict[str, object]** |  | [optional] 

## Example

```python
from openapi_client.models.content_request import ContentRequest

# TODO update the JSON string below
json = "{}"
# create an instance of ContentRequest from a JSON string
content_request_instance = ContentRequest.from_json(json)
# print the JSON string representation of the object
print(ContentRequest.to_json())

# convert the object into a dict
content_request_dict = content_request_instance.to_dict()
# create an instance of ContentRequest from a dict
content_request_from_dict = ContentRequest.from_dict(content_request_dict)
```
[[Back to Model list]](../README.md#documentation-for-models) [[Back to API list]](../README.md#documentation-for-api-endpoints) [[Back to README]](../README.md)


