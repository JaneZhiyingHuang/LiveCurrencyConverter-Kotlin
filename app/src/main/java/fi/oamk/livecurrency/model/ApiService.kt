package fi.oamk.livecurrency.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.amdoren.com/")  // API 的基础 URL
        .addConverterFactory(GsonConverterFactory.create()) // JSON 解析
        .build()

    val api: CurrencyApi = retrofit.create(CurrencyApi::class.java)
}