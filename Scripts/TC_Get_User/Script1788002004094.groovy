import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType

RequestObject request = new RequestObject()

request.setRestUrl('https://jsonplaceholder.typicode.com/users/1')
request.setRestRequestMethod('GET')

ResponseObject response = WS.sendRequest(request)

KeywordUtil.logInfo('Status: ' + response.getStatusCode())
KeywordUtil.logInfo('Response: ' + response.getResponseText())

WS.verifyResponseStatusCode(response, 200)

assert response.getResponseText().contains('"id": 1') :
	'Response tidak mengandung id = 1'

KeywordUtil.markPassed('Get User API - PASS')