package com.company.userservice.controllers

import com.company.userservice.dao.UserDao
import com.company.userservice.model.User
import org.json.JSONObject
import org.springframework.web.bind.annotation.*

@RestController("/user")
class UserController(
        private val userDao: UserDao
) {

    @PostMapping
    fun addUser(@RequestBody user: User): String {
        userDao.save(user)
        return JSONObject()
                .put("status", "ok")
                .put("id", user.id)
                .toString()
    }

    @DeleteMapping
    fun deleteUser(@RequestBody id: String): String {
        userDao.delete(id)
        return JSONObject()
                .put("status", "ok")
                .toString()
    }

    @GetMapping()
    fun searchUserByEmail(@RequestParam("email") email: String): List<User> {
        return userDao.searchByEmail(email)
    }

}
