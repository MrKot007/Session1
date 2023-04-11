package com.example.requests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.requests.Connection.api

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        api.signIn(ModelAuth("arseniy.bobovnikov@gmail.com", "123456789")).push(
            object: OnGetData<ModelIdentity> {
                override fun onGet(data: ModelIdentity) {
                    Log.d("Auth body", data.toString())
                }

                override fun onError(error: String) {
                    Log.e("Authorization failed", error)
                }

            }
        , this)
        api.getTags().push(object: OnGetData<List<ModelTag>>{
            override fun onGet(data: List<ModelTag>) {
                Log.d("Tag list", data.toString())
            }

            override fun onError(error: String) {
                Log.e("Obtaining tags failed", error)
            }
        }, this)
        api.getCourses().push(object: OnGetData<List<ModelCourse>>{
            override fun onGet(data: List<ModelCourse>) {
                Log.d("Course list", data.toString())
            }

            override fun onError(error: String) {
                Log.e("Obtaining courses failed", error)
            }
        }, this)
        api.getCourse(5).push(object: OnGetData<ModelDataCourse>{
            override fun onGet(data: ModelDataCourse) {
                Log.d("Course data", data.toString())
            }

            override fun onError(error: String) {
                Log.e("Obtaining course data failed", error)
            }
        }, this)
        api.getMaterials().push(object: OnGetData<List<ModelMaterial>>{
            override fun onGet(data: List<ModelMaterial>) {
                Log.d("Material list", data.toString())
            }

            override fun onError(error: String) {
                Log.e("Obtaining materials failed", error)
            }
        }, this)

    }
}