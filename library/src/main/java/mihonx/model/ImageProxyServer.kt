@file:Suppress("UNUSED")

package mihonx.model

import okhttp3.HttpUrl

abstract class ImageProxyServer internal constructor() {

    abstract fun apply(url: HttpUrl): HttpUrl

    class ZeroMS: ImageProxyServer() {
        override fun apply(url: HttpUrl): HttpUrl = throw RuntimeException("Stub!")
    }
}
