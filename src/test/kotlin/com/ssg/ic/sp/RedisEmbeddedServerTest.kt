package com.ssg.ic.sp

import io.lettuce.core.RedisClient
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RedisEmbeddedServerTest : RedisTest() {
    var test : String? = null

    @BeforeEach
    fun before(){
        test = "before"
    }

    @Test
    fun `embedded server log`() {
        Assertions.assertNotNull(test)

        var redisClient : RedisClient = RedisClient.create("redis://localhost:${port}/0");
        var connection = redisClient.connect();

        var syncCmd = connection.sync();
        val ret = syncCmd.ping()
        Assertions.assertNotNull(ret)
        println("ping : ${ret!!}")
        syncCmd.set("key", "Hello, Redis!");

        val value = syncCmd.get("key");
        println(value)
        Assertions.assertEquals("Hello, Redis!" , value)
        syncCmd.hset("recordName", "FirstName", "John")
        syncCmd.hset("recordName", "LastName", "Smith")
        val record: Map<String, String> = syncCmd.hgetall("recordName")
        println(record)
        redisClient.shutdown()
    }
//    https://www.baeldung.com/java-redis-lettuce
}