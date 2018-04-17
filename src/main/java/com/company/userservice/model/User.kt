package com.company.userservice.model

import java.util.*

data class User(
        val id: String,
        val firstName: String,
        val lastName: String,
        val birthday: Date,
        val email: String,
        val password: String)