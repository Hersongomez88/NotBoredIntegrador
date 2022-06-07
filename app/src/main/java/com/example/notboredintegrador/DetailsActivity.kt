package com.example.notboredintegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.notboredintegrador.databinding.ActivityDetailsBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityType = intent.getStringExtra("ActivityType")
        val participants = intent.getStringExtra("Participants")?.toInt()

        println("Mensaje --> $activityType recibido y el numero de participantes es: $participants")
        val texto = findViewById<TextView>(R.id.TvActicityType)
        texto.setText("La actividad elegida es: ${activityType} \ny el numero de participantes para esta actividad es: $participants")

        loadActivity(activityType, participants)
        getRetroFit()


    }

    private fun loadActivity(activityType: String?, participants: Int?) {

        CoroutineScope(Dispatchers.IO).launch {

            val apiResponse = getRetroFit()
                .create(APIService::class.java)
                .getActivity(participants,activityType)

            val activityInfo = apiResponse.body()

            if (apiResponse.isSuccessful){

                runOnUiThread{

                    binding.TvActicity.text = activityInfo?.description ?: ""
                    binding.TvActicityType.text = activityInfo?.category ?: ""
                    binding.TvParticipants.text = activityInfo?.participants.toString() ?: "0"
                    binding.TvPrice.text = activityInfo?.price.toString()
                }



            }else{

            }

        }

    }

    private fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}