package com.ssg.ic.sp

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import redis.embedded.RedisServer

open class RedisTest (val port: Int = 49999){
    private val redisServer = RedisServer.builder().port(port)
        .setting("maxmemory 128M")
        .build()

    @BeforeEach
    fun startServer() {
        redisServer.start()
    }



    @AfterEach()
    fun endServer() {
        redisServer.stop()
    }
}