package com.wesleyfuchter.kotlinscenariobuilder.demo

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = [DatabaseConfiguration::class])
class DemoApplicationTests {

	@Autowired
	private lateinit var testRestTemplate: TestRestTemplate

	@Test
	fun `should get all transactions`() {

//		val transactions = testRestTemplate.getForEntity("/transactions", Iterable::class.java)


	}

}
