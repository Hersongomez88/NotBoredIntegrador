package com.example.notboredintegrador

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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

        // Obtain the activity type and the number of participants
        val activityType = intent.getStringExtra("ActivityType")?.lowercase()
        val participants = intent.getStringExtra("Participants")?.toInt()

        // Fetch from API and render UI
        loadActivity(activityType, participants)

        binding.btnTryAnother.setOnClickListener {
            loadActivity(activityType, participants)
        }

        binding.ivBack.setOnClickListener {
            onBackPressed()
        }

    }

    /**
     * Fetches an activity from the API using the activity type and number of participants
     * received. If the response is successful render the info to the UI.
     * When the activity type is null it shows a random type of activity.
     * */
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
                            TvActivityType.text = activityInfo?.category?.replaceFirstChar {
                                if (it.isLowerCase()) it.titlecase(
                                    Locale.getDefault()
                                ) else it.toString()
                            } ?: ""
                            binding.containerActivity.visibility = View.GONE
                        }?:
                        run {
                            TvActivityType.text = getString(R.string.random)
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

                        btnTryAnother.text = getString(R.string.try_another)

                        //containerDetails.visibility = View.VISIBLE
                        //tvErrorMessage.visibility = View.GONE
                    }
                }

            } else {
                runOnUiThread {
                   // binding.containerDetails.visibility = View.GONE

                    //binding.tvErrorMessage.visibility = View.VISIBLE

                    binding.btnTryAnother.text = getString(R.string.try_again)
                    //binding.btnTryAnother.text = getString(R.string.try_again)

                }
            }

        }

    }

    /**
     * Maps the price to a string.
     * Price must be a value between 0 and 1 or can be null.
     * Always returns a string. */
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

    /**
     * Builds an instance of Retrofit and Gson as the converter factory
     * */
    private fun getRetroFit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://www.boredapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}