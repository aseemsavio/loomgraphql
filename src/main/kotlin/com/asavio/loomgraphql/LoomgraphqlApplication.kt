package com.asavio.loomgraphql

import org.apache.coyote.ProtocolHandler
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.task.TaskExecutionAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.web.embedded.tomcat.TomcatProtocolHandlerCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.AsyncTaskExecutor
import org.springframework.core.task.support.TaskExecutorAdapter
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.util.concurrent.Executors


@SpringBootApplication
class LoomgraphqlApplication

fun main(args: Array<String>) {
    runApplication<LoomgraphqlApplication>(*args)
}

@Configuration
class LoomConfig {
    @Bean(TaskExecutionAutoConfiguration.APPLICATION_TASK_EXECUTOR_BEAN_NAME)
    fun asyncTaskExecutor(): AsyncTaskExecutor? {
        return TaskExecutorAdapter(Executors.newVirtualThreadPerTaskExecutor())
    }

    @Bean
    fun protocolHandlerVirtualThreadExecutorCustomizer(): TomcatProtocolHandlerCustomizer<*>? {
        return TomcatProtocolHandlerCustomizer { protocolHandler: ProtocolHandler ->
            protocolHandler.executor = Executors.newVirtualThreadPerTaskExecutor()
        }
    }
}

@Controller
class PeopleController {

    @QueryMapping
    fun people(): List<Person> {
        println("Is Virtual Thread: ${Thread.currentThread().isVirtual} | ${Thread.currentThread()}")
        return listOf(
            Person("jdgahjdagdahjdg", "Jon", 25),
            Person("ddgahjdagdahjdg", "Snow", 26),
            Person("gdgahjdagdahjdg", "Arya", 23),
            Person("qdgahjdagdahjdg", "Stark", 21),
        )
    }

}

data class Person(val id: String, val name: String, val age: Int)

