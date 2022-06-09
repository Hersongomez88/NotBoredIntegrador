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
import java.util.*

class DetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val activityType = intent.getStringExtra("ActivityType")?.lowercase()
        val participants = intent.getStringExtra("Participants")?.toInt()

        loadActivity(activityType, participants)

        binding.btnTryAnother.setOnClickListener {
            loadActivity(activityType, participants)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
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
                        activityType?.let {
                            TvActicityType.text = activityInfo?.category?.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            } ?: ""
                            binding.containerActivity.visibility = View.GONE
                        }?:
                        run {
                            TvActicityType.text = "Random"
                            binding.containerActivity.visibility = View.VISIBLE
                            binding.TvActicity2.text=
                                activityInfo?.category?.replaceFirstChar {
                                    if (it.isLowerCase()) it.titlecase(
                                        Locale.getDefault()
                                    ) else it.toString()
                                } ?: ""
                        }

                        TvActicity.text = activityInfo?.description?: ""
                        TvParticipants.text = activityInfo?.participants.toString()
                        TvPrice.text = getPrice(activityInfo?.price)

                        btnTryAnother.text = "Try another"

                        //containerDetails.visibility = View.VISIBLE
                        //tvErrorMessage.visibility = View.GONE
                    }
                }

            } else {
                runOnUiThread {
                   // binding.containerDetails.visibility = View.GONE

                    //binding.tvErrorMessage.visibility = View.VISIBLE

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