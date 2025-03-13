package fi.oamk.livecurrency.model

data class CurrencyResponse(
    val error: Int,           // 0 表示成功
    val error_message: String, // 错误信息
    val amount: Double        // 转换后的金额
)