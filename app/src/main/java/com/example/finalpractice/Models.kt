package com.example.finalpractice

data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val sex: String,
    val dateBirthDay: String
)
data class ModelIdentity(
    val token: String,
    val user: User
)
data class ModelAuth(
    val email: String,
    val password: String
)
data class ModelReg(
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val password: String,
    val dateBirthDay: String,
    val sex: String
)
data class ModelError(
    val error: String
)