package com.example.couponsystem.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class CouponCountRepository(private val redisTemplate: RedisTemplate<String, String>) {
    fun increment(): Long? {
        return try {
            redisTemplate
                .opsForValue()
                .increment("coupon_count")
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }

    fun flushDb(): Unit? {
        return try {
            redisTemplate.execute { connection ->
                connection.serverCommands().flushDb()
            }
        } catch (e: Exception) {
            println(e)
            throw e
        }
    }
}