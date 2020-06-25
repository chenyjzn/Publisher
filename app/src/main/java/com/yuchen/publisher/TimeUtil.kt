package com.yuchen.publisher

import java.text.SimpleDateFormat
import java.util.*

object TimeUtil {
    @JvmStatic
    fun StampToDate(time: Long): String {
        // 進來的time以秒為單位，Date輸入為毫秒為單位，要注意

        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return simpleDateFormat.format(Date(time))
    }

    @JvmStatic
    fun DateToStamp(date: String): Long {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        /// 輸出為毫秒為單位
        return simpleDateFormat.parse(date).time
    }
}