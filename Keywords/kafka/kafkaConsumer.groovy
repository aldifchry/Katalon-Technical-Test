package kafka

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class kafkaConsumer {
}
package kafka

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.StringDeserializer

import java.time.Duration
import java.util.Properties

class KafkaConsumer {

	static String consumeMessage(
	String bootstrapServers,
	String topic,
	String groupId,
	int timeoutSeconds = 15) {

		Properties props = new Properties()

		props.put(
		ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
		bootstrapServers
		)

		props.put(
		ConsumerConfig.GROUP_ID_CONFIG,
		groupId
		)

		props.put(
		ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
		StringDeserializer.class.getName()
		)

		props.put(
		ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
		StringDeserializer.class.getName()
		)

		props.put(
		ConsumerConfig.AUTO_OFFSET_RESET_CONFIG,
		"earliest"
		)

		KafkaConsumer<String, String> consumer =
		new KafkaConsumer<String, String>(props)

		consumer.subscribe([topic])

		String message = null

		long startTime = System.currentTimeMillis()

		while (
		message == null &&
		System.currentTimeMillis() - startTime <
		timeoutSeconds * 1000
		) {

			def records =
			consumer.poll(Duration.ofMillis(1000))

			records.each {
				record ->

				println(
				"Kafka message received: " +
				record.value()
				)

				message = record.value()
			}
		}

		consumer.close()

		return message
	}
}