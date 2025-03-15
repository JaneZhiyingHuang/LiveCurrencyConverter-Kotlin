package fi.oamk.livecurrency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import fi.oamk.livecurrency.model.CurrencyApi
import fi.oamk.livecurrency.model.LiveCurrency
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val api = CurrencyApi.create()
    val exchangeRates = MutableLiveData<Map<String, Double>>() // 存储 5 种汇率

    // 目标货币列表
    private val currencies = listOf("USD", "GBP", "JPY", "CAD", "AUD")

    init {
        fetchExchangeRates()
    }

    // 获取欧元兑 5 种货币的最新汇率
    private fun fetchExchangeRates() {
        viewModelScope.launch {
            val rates = mutableMapOf<String, Double>()
            for (currency in currencies) {
                try {
                    val response = api.getCurrency("wVan8DU7STKQcyrBz3iRQqryMhahkV", "EUR", currency)
                    if (response.error == 0) {
                        rates[currency] = response.amount
                    }
                } catch (e: Exception) {
                    rates[currency] = -1.0 // 表示错误
                }
            }
            exchangeRates.postValue(rates)
        }
    }
}
