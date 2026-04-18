package com.bruno.esus

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SusApplication

fun main(args: Array<String>) {
    runApplication<SusApplication>(*args)
}