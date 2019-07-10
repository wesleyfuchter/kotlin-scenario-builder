package com.wesleyfuchter.kotlinscenariobuilder.demo.dbtest

import org.springframework.data.jpa.repository.JpaRepository

data class DatabaseRegister (private val repositories: Map<Any, ScenarioJpaRepositoryAdapter>) {

    class Builder {

        private val _repositories:  MutableMap<Any, ScenarioJpaRepositoryAdapter> = HashMap()

//        fun registry(clazz: Class<>, repository: ScenarioJpaRepositoryAdapter)
//                = _repositories.put(clazz, repository)

        fun build() = DatabaseRegister(repositories = _repositories)

        fun adapter(scenarioRepository: ScenarioRepository, jpaRepository: JpaRepository<Any, Any>) =
                ScenarioJpaRepositoryAdapter(scenarioRepository, jpaRepository)

//        fun registryDefaults() =

    }

    companion object {
        fun with(buildScenario: DatabaseRegister.Builder.() -> Unit)
                = DatabaseRegister.Builder().apply(buildScenario).build()
    }

}

data class ScenarioJpaRepositoryAdapter(
        private val scenarioRepository: ScenarioRepository,
        private val jpaRepository: JpaRepository<Any, Any>
)