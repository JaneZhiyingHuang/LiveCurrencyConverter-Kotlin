package fi.oamk.livecurrency.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import fi.oamk.livecurrency.model.ApiService
import fi.oamk.livecurrency.utils.Resource
import fi.oamk.livecurrency.model.CurrencyResponse

class CurrencyViewModel : ViewModel() {

    val exchangeResult = MutableLiveData<Resource<Double>>() // 结果状态 (成功/错误/加载中)

    fun convertCurrency(apiKey: String, from: String, to: String, amount: Double) {
        exchangeResult.value = Resource.Loading() // 设置加载状态
        viewModelScope.launch {
            try {
                val response = ApiService.api.convertCurrency(apiKey, from, to, amount)
                if (response.error == 0) {
                    exchangeResult.value = Resource.Success(response.amount) // 成功
                } else {
                    exchangeResult.value = Resource.Error(response.error_message) // 失败
                }
            } catch (e: Exception) {
                exchangeResult.value = Resource.Error("Network Error") // 网络错误
            }
        }
    }
}