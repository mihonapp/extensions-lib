package eu.kanade.tachiyomi.network.interceptor

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * An OkHttp interceptor that handles given url host's rate limiting.
 *
 * This uses Java Time APIs and is the legacy method, kept
 * for compatibility reasons with existing extensions.
 *
 * Examples:
 *
 * httpUrl = "https://api.manga.com".toHttpUrlOrNull(), permits = 5, period = 1, unit = seconds  =>  5 requests per second to api.manga.com
 * httpUrl = "https://imagecdn.manga.com".toHttpUrlOrNull(), permits = 10, period = 2, unit = minutes  =>  10 requests per 2 minutes to imagecdn.manga.com
 *
 * @since extension-lib 1.3
 *
 * @param httpUrl [HttpUrl] The url host that this interceptor should handle. Will get url's host by using HttpUrl.host()
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Long]     The limiting duration. Defaults to 1.
 * @param unit [TimeUnit]   The unit of time for the period. Defaults to seconds.
 */
@Deprecated("Use the version with kotlin.time [rateLimit] APIs instead.")
fun OkHttpClient.Builder.rateLimitHost(
    httpUrl: HttpUrl,
    permits: Int,
    period: Long = 1,
    unit: TimeUnit = TimeUnit.SECONDS,
): OkHttpClient.Builder = throw Exception("Stub!")
