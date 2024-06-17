package com.example.couponsystem.service

import com.example.couponsystem.domain.Coupon
import com.example.couponsystem.repository.CouponRepository
import org.springframework.stereotype.Service

@Service
class ApplyService (private val couponRepository: CouponRepository){
    fun apply(userId: Int) {
        val count: Long = couponRepository.count()

        if (count > 100) {
            return
        }
        couponRepository.save(Coupon(userId))
    }
}