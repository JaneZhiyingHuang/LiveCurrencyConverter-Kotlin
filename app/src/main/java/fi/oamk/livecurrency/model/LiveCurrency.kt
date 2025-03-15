package fi.oamk.livecurrency.model

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// API data
data class CurrencyResponse(
    val date: String,
    val base: String,
    val rates: Map<String, Double>
)

//API interface
interface CurrencyApi {
    @GET("rates/latest")
    suspend fun getExchangeRates(
        @Query("apikey") apiKey: String
    ): CurrencyResponse

    companion object {
        private const val BASE_URL = "https://api.currencyfreaks.com/v2.0/"

        fun create(): CurrencyApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CurrencyApi::class.java)
        }
    }
}