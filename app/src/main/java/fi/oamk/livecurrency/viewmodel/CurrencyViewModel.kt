package fi.oamk.livecurrency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.MutableLiveData
import fi.oamk.livecurrency.model.CurrencyApi
import kotlinx.coroutines.launch

class CurrencyViewModel : ViewModel() {
    private val api = CurrencyApi.create()
    val exchangeRates = MutableLiveData<Map<String, Double>>()
    val isLoading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String?>()

    // some currencies example
    private val currencies = listOf(
        "USD", "GBP", "JPY", "CAD", "AUD", "CNY", "HKY",
        "EUR", "CHF", "NZD", "INR", "MXN", "SGD", "BRL",
        "SEK", "NOK", "DKK", "ZAR", "KRW", "TRY", "RUB"
    )

    init {
        fetchExchangeRates()
    }

    // get exchange rates
    private fun fetchExchangeRates() {
        isLoading.postValue(true)
        errorMessage.postValue(null)
        viewModelScope.launch {
            try {
                val response = api.getExchangeRates("2c35b9a5de28487daf5f8c93da9f5740")
                val rates = response.rates.filter { it.key in currencies }
                exchangeRates.postValue(rates)
            } catch (e: Exception) {
                errorMessage.postValue("Failed to load data: ${e.message}")
                exchangeRates.postValue(emptyMap())
            } finally {
                isLoading.postValue(false)
            }
        }
    }
}
