package com.wesleyfuchter.kotlinscenariobuilder.demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.client.getForEntity
import org.springframework.boot.test.web.client.getForObject
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [DatabaseConfiguration::class])
@EnableAutoConfiguration
class DemoApplicationTests {

	@Autowired
	private lateinit var testRestTemplate: TestRestTemplate

	@Test
	fun `should get all transactions`() {

		val transactions = testRestTemplate.getForObject<Any>("/transactions")

		println(transactions)

	}

}
