package com.example.requests

//Для авторизации и регистрации
data class ModelIdentity(
    val token: String,
    val user: User
)
data class ModelError(
    val error: String
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
data class User(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val sex: String,
    val dateBirthDay: String
)
//Для работы с курсами
data class ModelTag(
    val id: Int,
    val name: String
)
data class Mentor(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val avatar: String
)
data class ModelCourse(
    val id: Int,
    val title: String,
    val tags: List<Int>,
    val cover: String,
    val price: Int
)
data class ModelTask(
    val title: String,
    val description: String,
    val duration: Int
)
data class ModelMaterial(
    val id: Int,
    val title: String,
    val cover: String,
    val url: String
)
data class ModelDataCourse(
    val id: Int,
    val price: Int,
    val title: String,
    val tags: List<Int>,
    val description: String,
    val mentors: List<Mentor>,
    val cover: String,
    val plan: List<ModelTask>
)
//Для работы с рабочим пространством
data class ModelObjective(
    val id: Int,
    val idCourse: Int,
    val title: String,
    val description: String,
    val isComplete: Boolean,
    val datetime: String,
    val duration: Int
)
data class Profile(
    val id: Int,
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val sex: String,
    val dateBirthDay: String,
    val avatar: String
)
data class EditProfile(
    val firstname: String,
    val lastname: String,
    val patronymic: String,
    val email: String,
    val dateBirthDay: String,
    val sex: String
)


