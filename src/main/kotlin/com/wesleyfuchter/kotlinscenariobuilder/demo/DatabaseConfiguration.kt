package com.wesleyfuchter.kotlinscenariobuilder.demo

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.PropertySource
import org.springframework.core.env.Environment
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean
import javax.persistence.EntityManagerFactory
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter
import org.springframework.transaction.annotation.EnableTransactionManagement
import org.springframework.jdbc.datasource.DriverManagerDataSource
import javax.sql.DataSource
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.orm.jpa.JpaTransactionManager
import java.util.*

//@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = ["com.wesleyfuchter.kotlinscenariobuilder.demo"])
@PropertySource("application.yml")
class DatabaseConfiguration @Autowired constructor(val env: Environment) {

    @Bean
    fun entityManagerFactory(): LocalContainerEntityManagerFactoryBean {
        val em = LocalContainerEntityManagerFactoryBean()
        em.dataSource = dataSource()
        em.setPackagesToScan("com.wesleyfuchter.kotlinscenariobuilder.demo")
        val vendorAdapter = HibernateJpaVendorAdapter()
        em.jpaVendorAdapter = vendorAdapter
        em.setJpaProperties(additionalProperties())
        return em
    }

    @Bean
    fun dataSource(): DataSource = DriverManagerDataSource().apply {
        setDriverClassName(env.getProperty("spring.datasource.driver-class-name")!!)
        url = env.getProperty("spring.datasource.url")
        username = env.getProperty("spring.datasource.username")
        password = env.getProperty("spring.datasource.password")
    }

    @Bean
    fun transactionManager(emf: EntityManagerFactory) = JpaTransactionManager().apply {
        entityManagerFactory = emf
    }

    @Bean
    fun exceptionTranslation(): PersistenceExceptionTranslationPostProcessor = PersistenceExceptionTranslationPostProcessor()

    fun additionalProperties(): Properties = Properties().apply {
        setProperty("hibernate.dialect", env.getProperty("spring.jpa.properties.hibernate.dialect"))
        setProperty("hibernate.jdbc.lob.non_contextual_creation", env.getProperty("spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation"))
    }

}