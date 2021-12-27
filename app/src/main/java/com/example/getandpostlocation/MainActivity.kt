package com.example.getandpostlocation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(){

    lateinit var name:EditText
    lateinit var location:EditText
    lateinit var nameSearch:EditText
    lateinit var showLocation:TextView
    lateinit var saveUser:Button
    lateinit var searchDetails:Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        name = findViewById(R.id.edName)
        location = findViewById(R.id.edLocation)
        nameSearch = findViewById(R.id.edNameSearch)
        showLocation = findViewById(R.id.tvLocation)
        saveUser = findViewById(R.id.btnSave)
        searchDetails = findViewById(R.id.btnSearch)


        saveUser.setOnClickListener {
            addNewUser()
        }

        searchDetails.setOnClickListener {
            getDetails()
        }
    }

 private fun addNewUser() {
        val apiInterface = APIClient().getClient()?.create(APIinterface::class.java)

     apiInterface?.addUser(PersonDetails.PersonDetailsItem(name.text.toString(),location.text.toString(),
         1))?.enqueue(object : Callback<PersonDetails.PersonDetailsItem?> {
         override fun onResponse(
             call: Call<PersonDetails.PersonDetailsItem?>,response: Response<PersonDetails.PersonDetailsItem?>) {
             Toast.makeText(applicationContext, "add Successfully", Toast.LENGTH_SHORT).show()
         }

         override fun onFailure(call: Call<PersonDetails.PersonDetailsItem?>, t: Throwable) {
             Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
         }
     })
    }
    private fun getDetails(){

        val apiInterface = APIClient().getClient()?.create(APIinterface::class.java)

        apiInterface!!.getUser()?.enqueue(object : Callback<PersonDetails?> {
            override fun onResponse(call: Call<PersonDetails?>,response: Response<PersonDetails?>) {
                for(index in response.body()!!) {
                    if (nameSearch.text.toString() != index.name) continue
                    showLocation.text="${index.location}"
                }
            }
            override fun onFailure(call: Call<PersonDetails?>, t: Throwable) {
                Toast.makeText(applicationContext, "${t.message}", Toast.LENGTH_SHORT).show()
            }
        })
    }



}