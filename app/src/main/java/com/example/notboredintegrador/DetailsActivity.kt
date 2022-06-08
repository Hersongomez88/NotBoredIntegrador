package com.example.notboredintegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
        val texto = binding.TvActicityType
        texto.setText("La actividad elegida es: ${activityType} \ny el numero de participantes para esta actividad es: $participants")

        loadActivity(activityType, participants)

        binding.btnTryAnother.setOnClickListener {
            loadActivity(activityType, participants)
        }

    }

    private fun loadActivity(activityType: String?, participants: Int?) {

        CoroutineScope(Dispatchers.IO).launch {

            val apiResponse = getRetroFit()
                .create(APIService::class.java)
                .getActivity(participants, activityType)

            val activityInfo = apiResponse.body()

            if (apiResponse.isSuccessful) {

                runOnUiThread {
                    with(binding) {
                        TvActicity.text = activityInfo?.description ?: ""
                        TvActicityType.text = activityInfo?.category ?: ""
                        TvParticipants.text = activityInfo?.participants.toString()
                        TvPrice.text = getPrice(activityInfo?.price)

                        binding.btnTryAnother.text = "Try another"

                        containerDetails.visibility = View.VISIBLE
                        tvErrorMessage.visibility = View.GONE
                    }
                }

            } else {
                runOnUiThread {
                    binding.containerDetails.visibility = View.GONE

                    binding.tvErrorMessage.visibility = View.VISIBLE

                    binding.btnTryAnother.text = "Try again"
                }
            }

        }

    }

    private fun getPrice(price: Double?): String {
        return when (price) {
            null -> ""
            0.0 -> "Free"
            in 0.0..0.3 -> "Low"
            in 0.3..0.6 -> "Medium"
            in 0.6..1.0 -> "High"
            else -> "Invalid price"
        }
    }

    private fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}