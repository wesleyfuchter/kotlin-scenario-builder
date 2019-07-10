package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

class ScenarioLoader {

    operator fun invoke(scenario: Scenario, runTest: DatabaseScenarioRunner.() -> Unit)
            = DatabaseScenarioRunner(scenario).runTest()

    class DatabaseScenarioRunner(val scenario: Scenario)

}