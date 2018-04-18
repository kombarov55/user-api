package com.company.userservice.dao

import com.company.userservice.model.User
import org.apache.commons.codec.digest.DigestUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Component
import java.sql.ResultSet
import java.util.*

@Component
open class UserDao(
        private val namedParameterJdbcTemplate: NamedParameterJdbcTemplate
) {

    fun save(user: User) {
        user.id = UUID.randomUUID().toString()
        namedParameterJdbcTemplate.update(
                "insert into users(id, firstname, lastname, birthday, email, password) values (:id, :firstname, :lastname, :birthday, :email, :password)", mapOf(
                "id" to user.id,
                "firstname" to user.firstName,
                "lastname" to user.lastName,
                "birthday" to user.birthday,
                "email" to user.email,
                "password" to DigestUtils.md5Hex(user.password)
        ))
    }

    fun delete(id: String) {
        namedParameterJdbcTemplate.update("delete from users where id = :id", mapOf("id" to id))
    }

    fun searchByEmail(email: String) =
            namedParameterJdbcTemplate.query(
                    "select * from users where upper(email) like upper(:email) || '%'",
                    mapOf("email" to email),
                    mapper
            )

    private val mapper: RowMapper<User> = RowMapper { rs: ResultSet, _: Int ->
        User(
                id = rs.getString("id"),
                firstName = rs.getString("firstname"),
                lastName = rs.getString("lastname"),
                birthday = rs.getDate("birthday"),
                email = rs.getString("email"),
                password = rs.getString("password")
        )
    }

}