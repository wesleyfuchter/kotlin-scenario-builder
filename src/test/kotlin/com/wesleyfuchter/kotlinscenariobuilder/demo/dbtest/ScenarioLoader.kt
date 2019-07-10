package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

import org.springframework.stereotype.Component

@Component
class ScenarioLoader {

    operator fun invoke(scenario: Scenario, schema: DatabaseSchema, runTest: DatabaseScenarioRunner.() -> Unit) {
        load(scenario, schema)
        DatabaseScenarioRunner(scenario, schema).runTest()
    }

    fun load(scenario: Scenario, schema: DatabaseSchema) {
//        schema.cities.saveAll(scenario.cities.map { city -> city.parse() })
    }

    class DatabaseScenarioRunner(val scenario: Scenario, private val schema: DatabaseSchema): DatabaseSchema by schema



}