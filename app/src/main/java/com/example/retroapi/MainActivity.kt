package com.example.retroapi

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.retroapi.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()

        binding.btnNext.setOnClickListener {
            getData()
        }
    }

    private fun getData() {

        val progressDialog=ProgressDialog(this)
        progressDialog.setMessage("Please wait while data is fetching")
        progressDialog.show()

        try{
            RetrofitInstance.apiInterface.getData().enqueue(object : Callback<responseDataClass?> {



                override fun onResponse(
                    call: Call<responseDataClass?>,
                    response: Response<responseDataClass?>,
                ) {
                    binding.title.text=response.body()?.title
                    binding.author.text=response.body()?.author
                    Glide.with(this@MainActivity).load(response.body()?.url).into(binding.image)
                    progressDialog.dismiss()
                }

                override fun onFailure(call: Call<responseDataClass?>, t: Throwable) {
                    Toast.makeText(this@MainActivity,"Error",Toast.LENGTH_SHORT).show()
                    progressDialog.dismiss()
                }
            })
        }catch (e: Exception){

        }
    }
}