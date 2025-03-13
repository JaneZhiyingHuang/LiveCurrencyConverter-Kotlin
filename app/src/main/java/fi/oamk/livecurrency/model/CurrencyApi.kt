package fi.oamk.livecurrency.model

import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {
    @GET("api/currency.php")
    suspend fun convertCurrency(
        @Query("api_key") apiKey: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double
    ): CurrencyResponse
}