package com.example.couponsystem.domain

import jakarta.persistence.*

@Entity()
class Coupon(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(nullable = false)
    val userId: Int = 0,
)