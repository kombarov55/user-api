package com.company.userservice.model

import java.util.*

data class User(
        var id: String? = null,
        val firstName: String? = null,
        val lastName: String? = null,
        val birthday: Date? = null,
        val email: String? = null,
        val password: String? = null)