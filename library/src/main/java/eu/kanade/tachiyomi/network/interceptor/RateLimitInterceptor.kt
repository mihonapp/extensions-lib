package eu.kanade.tachiyomi.network.interceptor

import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

/**
 * An OkHttp interceptor that handles rate limiting.
 *
 * This uses `java.time` APIs and is the legacy method, kept
 * for compatibility reasons with existing extensions.
 *
 * Examples:
 *
 * permits = 5,  period = 1, unit = seconds  =>  5 requests per second
 * permits = 10, period = 2, unit = minutes  =>  10 requests per 2 minutes
 *
 * @since extension-lib 1.3
 *
 * @param permits [Int]   Number of requests allowed within a period of units.
 * @param period [Long]   The limiting duration. Defaults to 1.
 * @param unit [TimeUnit] The unit of time for the period. Defaults to seconds.
 */
@Suppress("DeprecatedCallableAddReplaceWith")
@Deprecated("Use the version with kotlin.time APIs instead.")
fun OkHttpClient.Builder.rateLimit(
    permits: Int,
    period: Long = 1,
    unit: TimeUnit = TimeUnit.SECONDS,
): OkHttpClient.Builder = throw Exception("Stub!")

/**
 * An OkHttp interceptor that enforces rate limiting across all requests.
 *
 * Examples:
 *
 * permits = 5,  period = 1.seconds  =>  5 requests per second
 * permits = 10, period = 2.minutes  =>  10 requests per 2 minutes
 *
 * @since extension-lib 1.6
 *
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 */
fun OkHttpClient.Builder.rateLimit(
    permits: Int,
    period: Duration = 1.seconds
): OkHttpClient.Builder = throw Exception("Stub!")

/**
 * An OkHttp interceptor that handles given url host's rate limiting.
 *
 * Examples:
 *
 * url = "https://api.manga.com", permits = 5, period = 1.seconds =>  5 requests per second to api.manga.com
 * url = "https://imagecdn.manga.com", permits = 10, period = 2.minutes  =>  10 requests per 2 minutes to imagecdn.manga.com
 *
 * @since extension-lib 1.6
 *
 * @param url [String]      The url host that this interceptor should handle. Will get url's host by using HttpUrl.host()
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 */
fun OkHttpClient.Builder.rateLimit(
    url: String,
    permits: Int,
    period: Duration = 1.seconds,
): OkHttpClient.Builder = throw Exception("Stub!")

/**
 * An OkHttp interceptor that handles given url host's rate limiting.
 *
 * Examples:
 *
 * httpUrl = "https://api.manga.com".toHttpUrlOrNull(), permits = 5, period = 1.seconds =>  5 requests per second to api.manga.com
 * httpUrl = "https://imagecdn.manga.com".toHttpUrlOrNull(), permits = 10, period = 2.minutes  =>  10 requests per 2 minutes to imagecdn.manga.com
 *
 * @since extension-lib 1.6
 *
 * @param httpUrl [HttpUrl] The url host that this interceptor should handle. Will get url's host by using HttpUrl.host()
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 */
fun OkHttpClient.Builder.rateLimit(
    httpUrl: HttpUrl,
    permits: Int,
    period: Duration = 1.seconds,
): OkHttpClient.Builder = throw Exception("Stub!")

/**
 * An OkHttp interceptor that enforces conditional rate limiting based on a given condition.
 *
 * Examples:
 *
 * permits = 5, period = 1.seconds, shouldLimit = { it.host == "api.manga.com" } => 5 requests per second to api.manga.com.
 * permits = 10, period = 2.minutes, shouldLimit = { it.encodedPath.startsWith("/images/") } => 10 requests per 2 minutes to paths starting with "/images/".
 *
 * @since extension-lib 1.6
 *
 * @param permits [Int]     Number of requests allowed within a period of units.
 * @param period [Duration] The limiting duration. Defaults to 1.seconds.
 * @param shouldLimit       A predicate to determine whether the rate limit should apply to a given request.
 */
fun OkHttpClient.Builder.rateLimit(
    permits: Int,
    period: Duration = 1.seconds,
    shouldLimit: (HttpUrl) -> Boolean,
): OkHttpClient.Builder = throw Exception("Stub!")
