import com.kms.katalon.core.util.KeywordUtil

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords

import java.time.Duration
import java.util.Properties
import java.util.UUID

// =====================================================
// KAFKA CONFIGURATION
// =====================================================

String bootstrapServer = "localhost:9092"
String topic = "technical-test"

// Consumer group baru setiap execution
String consumerGroup =
	"katalon-consumer-" + UUID.randomUUID().toString()

KeywordUtil.logInfo("========================================")
KeywordUtil.logInfo("Kafka Consumer Test")
KeywordUtil.logInfo("========================================")
KeywordUtil.logInfo("Bootstrap Server: " + bootstrapServer)
KeywordUtil.logInfo("Topic: " + topic)
KeywordUtil.logInfo("Consumer Group: " + consumerGroup)

// =====================================================
// CONSUMER PROPERTIES
// =====================================================

Properties props = new Properties()

props.put(
	"bootstrap.servers",
	bootstrapServer
)

props.put(
	"group.id",
	consumerGroup
)

props.put(
	"key.deserializer",
	"org.apache.kafka.common.serialization.StringDeserializer"
)

props.put(
	"value.deserializer",
	"org.apache.kafka.common.serialization.StringDeserializer"
)

props.put(
	"auto.offset.reset",
	"earliest"
)

props.put(
	"enable.auto.commit",
	"false"
)

// =====================================================
// CREATE CONSUMER
// =====================================================

KafkaConsumer<String, String> consumer =
	new KafkaConsumer<String, String>(props)

try {

	// =================================================
	// SUBSCRIBE
	// =================================================

	consumer.subscribe([topic])

	KeywordUtil.logInfo(
		"Subscribed to topic: " + topic
	)

	KeywordUtil.logInfo(
		"Waiting for Kafka message..."
	)

	// =================================================
	// WAIT MAX 15 SECONDS
	// =================================================

	long timeout =
		System.currentTimeMillis() + 15000

	String receivedMessage = null

	while (
		System.currentTimeMillis() < timeout
		&& receivedMessage == null
	) {

		ConsumerRecords<String, String> records =
			consumer.poll(Duration.ofMillis(1000))

		KeywordUtil.logInfo(
			"Records received: " + records.count()
		)

		// =============================================
		// CHECK EACH RECORD
		// =============================================

		for (
			ConsumerRecord<String, String> record : records
		) {

			String value = record.value()

			KeywordUtil.logInfo(
				"----------------------------------------"
			)

			KeywordUtil.logInfo(
				"Received Message: " + value
			)

			KeywordUtil.logInfo(
				"Topic: " + record.topic()
			)

			KeywordUtil.logInfo(
				"Partition: " + record.partition()
			)

			KeywordUtil.logInfo(
				"Offset: " + record.offset()
			)

			// =========================================
			// CHECK EXPECTED EVENT
			// =========================================

			if (
				value != null &&
				value.contains("technical-test")
			) {

				receivedMessage = value

				KeywordUtil.logInfo(
					"Expected event found!"
				)

				break
			}
		}
	}

	// =================================================
	// VALIDATION
	// =================================================

	assert receivedMessage != null :
		"Kafka message containing 'technical-test' was not received within 15 seconds"

	assert receivedMessage.contains("technical-test") :
		"Kafka message does not contain expected event"

	// =================================================
	// PASS
	// =================================================

	KeywordUtil.logInfo(
		"========================================"
	)

	KeywordUtil.logInfo(
		"Kafka Message Validation: PASS"
	)

	KeywordUtil.logInfo(
		"Message: " + receivedMessage
	)

	KeywordUtil.logInfo(
		"========================================"
	)

	KeywordUtil.markPassed(
		"Kafka Consumer - PASS"
	)

} finally {

	// =================================================
	// CLOSE CONSUMER
	// =================================================

	consumer.close()

	KeywordUtil.logInfo(
		"Kafka Consumer closed"
	)
}