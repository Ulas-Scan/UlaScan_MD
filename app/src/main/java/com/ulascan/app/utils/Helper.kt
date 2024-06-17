package com.ulascan.app.utils

import android.os.Build
import android.text.format.DateUtils
import androidx.annotation.RequiresApi
import com.ulascan.app.data.remote.response.AnalysisData
import com.ulascan.app.data.remote.response.HistoriesItem
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.TimeZone

object Helper {
    fun generateAnalysisData(): AnalysisData {
        return AnalysisData(
            productName = "Tropicana Slim Cafe Latte (10 sch) - Kopi Bebas Gula",
            productDescription = "Tropicana Slim Cafe Latte adalah paduan kopi susu yang sempurna dengan rasa manis yang bisa dinikmati tanpa rasa khawatir karena diformulasikan tanpa penambahan gula pasir sehingga aman untuk diabetesi dan cocok untuk diet. \\nSemangati harimu dengan secangkir Tropicana Slim Cafe Latte!\\n\\nMengapa Tropicana Slim Cafe Latte?\\n- Rasa kopi susu yang nikmat\\n- Tanpa penambahan gula pasir \\n- Aman untuk diabetesi dan cocok untuk diet\\n- Lebih rendah kafein, aman untuk ibu hamil dan menyusui.\\n\\nCara Penyajian dan Petunjuk Konsumsi :\\nLarutkan 1 sachet Tropicana Slim Cafe Latte dalam 200 ml air hangat.  Aduk merata, dan secangkir kopi hangat yang nikmat lezat pun siap Anda nikmati.\\n\\nNutrimart merekomendasikan Tropicana Slim Cafe Latte untuk kamu yang:\\n - Mencari KOPI NIKMAT ala CAFE, TANPA GULA PASIR\\n - Memulai POLA HIDUP SEHAT dengan membatasi asupan gula\\n - MenJAGA KADAR GULA DARAH\\n - Memiliki DIABETES\\n - Menjalankan DIET\\n - Mementingkan KREDIBILITAS produk dengan pengalaman hampir 50 TAHUN",
            rating = 100,
            ulasan = 93,
            bintang = 4.91,
            imageUrls =
            listOf(
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2023/7/26/e799c724-ed29-43dc-b723-2854421553f3.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/aef9ea0c-0e01-45f3-bc0f-8bc5b03dc581.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/26e86f2d-373b-46cb-bda9-1ab4bd427907.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/002e056c-cd07-4fbc-aefe-1f9a1d9ea739.jpg",
                "https://images.tokopedia.net/img/cache/700/VqbcmM/2022/6/24/07280c5d-b3de-4818-87be-440355233f6c.jpg"),
            shopName = "NutriMart",
            countNegative = 48,
            countPositive = 45,
            packaging = 84.62,
            delivery = 90.12,
            adminResponse = 100.00,
            productCondition = 92.59,
            summary =
            "Secara keseluruhan, produk Tropicana Slim Cafe Latte mendapatkan ulasan yang positif. Pengguna menyukai rasanya yang enak, meskipun beberapa menganggapnya terlalu manis. Pengiriman cepat dan pengemasan aman diapresiasi oleh banyak pengguna. Produk ini juga dipuji karena rendah kalori dan bebas gula, menjadikannya pilihan yang lebih sehat. Beberapa pengguna menyebutkan bahwa mereka telah menjadi pelanggan tetap dan akan membeli lagi.",
            shopAvatar = "https://images.tokopedia.net/img/cache/215-square/GAnVPX/2021/3/30/9ee6fbe9-1452-40f6-9b56-42a5bd14e5d5.png"
        )
    }
    
    fun generateHistoryItem(): HistoriesItem {
        return HistoriesItem(
            id =  "bdf7f512-5ae6-4c39-b951-ac2fbf0c0036",
            url = "https=//www.tokopedia.com/nutrimartid/tropicana-slim-cafe-latte-10-sch-kopi-bebas-gula",
            productId = "502182312",
            productName = "Tropicana Slim Cafe Latte (10 sch) - Kopi Bebas Gula",
            countPositive = 57,
            countNegative = 43,
            rating = 100,
            ulasan = 100,
            bintang = 4.91,
            packaging = 78.26,
            delivery = 94.74,
            adminResponse = 100.00,
            productCondition = 95.83,
            summary = "Secara keseluruhan, pembeli puas dengan produk Tropicana Slim Cafe Latte. Mereka memuji rasa kopi yang enak, pengiriman yang cepat, dan pengemasan yang aman. Beberapa pembeli mencatat bahwa rasa manisnya sedikit berlebihan, tetapi secara umum, mereka senang dengan produk ini, terutama karena merupakan pilihan yang lebih sehat dibandingkan kopi dengan gula.  Beberapa pembeli juga memuji respon cepat dari penjual dan diskon yang ditawarkan.",
            userId = "58a86fa4-3185-4f42-9bc5-ff63eb2cc2ff",
            createdAt = "2024-06-16T03=07=44.881647Z",
            updatedAt = "2024-06-16T03=07=44.881647Z",
            deletedAt = null
        )
    }
    
    fun convertHistoryDataToAnalysisData(item: HistoriesItem): AnalysisData {
        return AnalysisData (
            productName = item.productName,
            countNegative = item.countNegative,
            countPositive = item.countPositive,
            adminResponse = item.adminResponse,
            delivery = item.delivery,
            packaging = item.packaging,
            summary = item.summary,
            productCondition = item.productCondition,
            bintang = item.bintang,
            ulasan = item.ulasan,
            rating = item.rating,
            shopName = generateAnalysisData().shopName,
            productDescription = generateAnalysisData().productDescription,
            imageUrls = generateAnalysisData().imageUrls,
            shopAvatar = generateAnalysisData().shopAvatar
        )
    }

    fun categorizeQueries(queries: List<HistoriesItem>): Map<String, List<HistoriesItem>> {
        val today = mutableListOf<HistoriesItem>()
        val yesterday = mutableListOf<HistoriesItem>()
        val previously = mutableListOf<HistoriesItem>()
        
        queries.forEach { query ->
            when {
                DateUtils.isToday(query.instant.time) -> today.add(query)
                DateUtils.isToday(query.instant.time + DateUtils.DAY_IN_MILLIS)  -> yesterday.add(query)
                else -> previously.add(query)
            }
        }
        
        return mapOf(
            "Today" to today,
            "Yesterday" to yesterday,
            "Previously" to previously
        )
    }
}
