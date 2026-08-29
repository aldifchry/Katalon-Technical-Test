import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.util.KeywordUtil

def response = WS.sendRequest(
	findTestObject('Create User')
)

KeywordUtil.logInfo(
	"Response Status: " + response.getStatusCode()
)

KeywordUtil.logInfo(
	"Response Body: " + response.getResponseText()
)

WS.verifyResponseStatusCode(response, 201)

KeywordUtil.markPassed(
	"Create User API - PASS"
)