package com.example.couponsystem.service

import com.example.couponsystem.repository.CouponCountRepository
import com.example.couponsystem.repository.CouponRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@SpringBootTest
class ApplyServiceTest(
    @Autowired private val applyService: ApplyService,
    @Autowired private val couponRepository: CouponRepository,
    @Autowired private val couponCountRepository: CouponCountRepository
) {
    @AfterEach
    fun deleteAll() {
        couponRepository.deleteAll()
        couponCountRepository.flushDb()
    }

    @Test
    fun apply_once() {
        applyService.apply(1)

        val count: Long = couponRepository.count()

        assertThat(count).isEqualTo(1)
    }

    @Test
    fun apply_multiple_times() {
        val threadCount = 1000
        val executorService: ExecutorService = Executors.newFixedThreadPool(32)

        val latch = CountDownLatch(threadCount)

        for (i in 0 until threadCount) {
            executorService.submit {
                try {
                    applyService.apply(userId = i)
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        val count = couponRepository.count()

        assertThat(count).isEqualTo(100)
    }
}