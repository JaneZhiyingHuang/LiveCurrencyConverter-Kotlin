package fi.oamk.livecurrency.model

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// API 响应数据类
data class LiveCurrency(
    val error: Int,
    val error_message: String,
    val amount: Double
)

// API 接口
interface CurrencyApi {
    @GET("currency.php")
    suspend fun getCurrency(
        @Query("api_key") apiKey: String,
        @Query("from") from: String,
        @Query("to") to: String
    ): LiveCurrency

    companion object {
        private const val BASE_URL = "https://www.amdoren.com/api/"

        fun create(): CurrencyApi {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(CurrencyApi::class.java)
        }
    }
}
