package com.example.couponsystem.service

import com.example.couponsystem.domain.Coupon
import com.example.couponsystem.repository.CouponCountRepository
import com.example.couponsystem.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService (
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository,
){
    fun apply(userId: Int) {
        val count: Long? = couponCountRepository.increment()
        println(count)
        if (count != null && count > 101) {
            return
        }
        couponRepository.save(Coupon(userId))
    }
}