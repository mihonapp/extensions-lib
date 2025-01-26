@file:Suppress("UNUSED", "UNUSED_PARAMETER", "UnusedReceiverParameter", "DEPRECATION")

package eu.kanade.tachiyomi.source.online

import eu.kanade.tachiyomi.network.NetworkHelper
import eu.kanade.tachiyomi.source.CatalogueSource
import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.model.MangasPage
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import mihonx.source.model.UserAgentType
import okhttp3.Headers
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import rx.Observable

/**
 * A simple implementation for sources from a website.
 */
abstract class HttpSource : CatalogueSource {

    /**
     * Base url of the website without the trailing slash, like: http://mysite.com
     */
    abstract val baseUrl: String

    /**
     * Version id used to generate the source id. If the site completely changes and urls are
     * incompatible, you may increase this value and it'll be considered as a new source.
     */
    open val versionId: Int = throw RuntimeException("Stub!")

    /**
     * Id of the source. By default it uses a generated id using the first 16 characters (64 bits)
     * of the MD5 of the string: sourcename/language/versionId
     * Note the generated id sets the sign bit to 0.
     */
    override val id: Long = throw RuntimeException("Stub!")

    /**
     * Network service.
     */
    protected val network: NetworkHelper = throw RuntimeException("Stub!")


    /**
     * Headers used for requests.
     */
    val headers: Headers = throw RuntimeException("Stub!")

    /**
     * Default network client for doing requests.
     */
    open val client: OkHttpClient = throw RuntimeException("Stub!")

    /**
     * Type of UserAgent a source needs
     */
    protected open val supportedUserAgentType: UserAgentType = UserAgentType.Universal

    /**
     * @since extensions-lib 1.6
     */
    protected fun getUserAgent(): String = throw RuntimeException("Stub!")

    /**
     * Headers builder for requests. Implementations can override this method for custom headers.
     */
    protected open fun headersBuilder(): Headers.Builder {
        throw RuntimeException("Stub!")
    }

    override suspend fun getDefaultMangaList(page: Int): MangasPage = throw RuntimeException("Stub!")

    /**
     * Returns the request for the default manga list for given page.
     *
     * @param page the page number to retrieve.
     */
    open fun defaultMangaListRequest(page: Int): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response of default manga list and returns a [MangasPage] object.
     *
     * @param response the response from the site.
     */
    open fun defaultMangaListParse(response: Response): MangasPage = throw RuntimeException("Stub!")

    override suspend fun getLatestMangaList(page: Int): MangasPage = throw RuntimeException("Stub!")

    /**
     * Returns the request for latest manga list for given page.
     *
     * @param page the page number to retrieve.
     */
    open fun latestMangaListRequest(page: Int): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response of latest manga list and returns a [MangasPage] object.
     *
     * @param response the response from the site.
     */
    open fun latestMangaListParse(response: Response): MangasPage = throw RuntimeException("Stub!")

    override suspend fun getMangaList(query: String, filters: FilterList, page: Int): MangasPage = throw RuntimeException("Stub!")

    /**
     * Returns the request for the searched and filtered manga list for given page.
     *
     * @param query the search query.
     * @param filters the list of filters to apply.
     * @param page the page number to retrieve.
     */
    open fun mangaListRequest(query: String, filters: FilterList, page: Int): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response of the searched and filtered manga list and returns a [MangasPage] object.
     *
     * @param response the response from the site.
     */
    open fun mangaListParse(response: Response): MangasPage = throw RuntimeException("Stub!")

    override suspend fun getMangaDetails(
        manga: SManga,
        updateManga: Boolean,
        fetchChapters: Boolean
    ): Pair<SManga, List<SChapter>> = throw RuntimeException("Stub!")

    /**
     * Returns the request for the details of a manga. Override only if it's needed to change the
     * url, send different headers or request method like POST.
     *
     * @param manga the manga to be updated.
     */
    open fun mangaDetailsRequest(manga: SManga): Request {
        throw RuntimeException("Stub!")
    }

    /**
     * Parses the response from the site and returns the details of a manga.
     *
     * @param response the response from the site.
     */
    protected abstract fun mangaDetailsParse(response: Response): SManga

    /**
     * Returns the request for updating the chapter list. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     *
     * @param manga the manga to look for chapters.
     */
    protected open fun chapterListRequest(manga: SManga): Request {
        throw RuntimeException("Stub!")
    }

    /**
     * Parses the response from the site and returns a list of chapters.
     *
     * @param response the response from the site.
     */
    protected abstract fun chapterListParse(response: Response): List<SChapter>
    
    override suspend fun getPageList(chapter: SChapter): List<Page> = throw RuntimeException("Stub!")

    /**
     * Returns the request for getting the page list. Override only if it's needed to override the
     * url, send different headers or request method like POST.
     *
     * @param chapter the chapter whose page list has to be fetched.
     */
    protected open fun pageListRequest(chapter: SChapter): Request {
        throw RuntimeException("Stub!")
    }

    /**
     * Parses the response from the site and returns a list of pages.
     *
     * @param response the response from the site.
     */
    protected abstract fun pageListParse(response: Response): List<Page>

    /**
     * Returns the image url for the provided [page]. The function is only called if [Page.imageUrl] is null.
     *
     * @param page the page whose source image has to be fetched.
     */
    open suspend fun getImageUrl(page: Page): String {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the request for getting the url to the source image. Override only if it's needed to
     * override the url, send different headers or request method like POST.
     *
     * @param page the chapter whose page list has to be fetched
     */
    protected open fun imageUrlRequest(page: Page): Request {
        throw RuntimeException("Stub!")
    }

    /**
     * Parses the response from the site and returns the absolute url to the source image.
     *
     * @param response the response from the site.
     */
    protected abstract fun imageUrlParse(response: Response): String

    /**
     * Returns the request for getting the source image. Override only if it's needed to override
     * the url, send different headers or request method like POST.
     *
     * @param page the chapter whose page list has to be fetched
     */
    protected open fun imageRequest(page: Page): Request {
        throw RuntimeException("Stub!")
    }

    /**
     * Assigns the url of the chapter without the scheme and domain. It saves some redundancy from
     * database and the urls could still work after a domain change.
     *
     * @param url the full url to the chapter.
     */
    fun SChapter.setUrlWithoutDomain(url: String) {
        throw RuntimeException("Stub!")
    }

    /**
     * Assigns the url of the manga without the scheme and domain. It saves some redundancy from
     * database and the urls could still work after a domain change.
     *
     * @param url the full url to the manga.
     */
    fun SManga.setUrlWithoutDomain(url: String) {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the url of the provided manga
     *
     * @since extensions-lib 1.4
     * @param manga the manga
     * @return url of the manga
     */
    open fun getMangaUrl(manga: SManga): String {
        throw RuntimeException("Stub!")
    }

    /**
     * Returns the url of the provided chapter
     *
     * @since extensions-lib 1.4
     * @param chapter the chapter
     * @return url of the chapter
     */
    open fun getChapterUrl(chapter: SChapter): String {
        throw RuntimeException("Stub!")
    }

    override fun toString(): String {
        throw RuntimeException("Stub!")
    }

    @Deprecated("Use the new suspend API instead", replaceWith = ReplaceWith("getDefaultMangaList"))
    override fun fetchPopularManga(page: Int): Observable<MangasPage> = throw RuntimeException("Stub!")

    /**
     * Returns the request for the popular manga given the page.
     *
     * @param page the page number to retrieve.
     */
    @Deprecated("Use the new API instead", replaceWith = ReplaceWith("defaultMangaListRequest"))
    open fun popularMangaRequest(page: Int): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a [MangasPage] object.
     *
     * @param response the response from the site.
     */
    @Deprecated("Use the new API instead", replaceWith = ReplaceWith("defaultMangaListParse"))
    open fun popularMangaParse(response: Response): MangasPage = throw RuntimeException("Stub!")

    @Deprecated("Use the new suspend API instead", replaceWith = ReplaceWith("getLatestMangaList"))
    override fun fetchLatestUpdates(page: Int): Observable<MangasPage> = throw RuntimeException("Stub!")

    /**
     * Returns the request for latest manga given the page.
     *
     * @param page the page number to retrieve.
     */
    @Deprecated("Use the new API instead", replaceWith = ReplaceWith("latestMangaListRequest"))
    open fun latestUpdatesRequest(page: Int): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a [MangasPage] object.
     *
     * @param response the response from the site.
     */
    @Deprecated("Use the new API instead", replaceWith = ReplaceWith("latestMangaListParse"))
    open fun latestUpdatesParse(response: Response): MangasPage = throw RuntimeException("Stub!")

    @Deprecated("Use the new suspend API instead", replaceWith = ReplaceWith("getMangaList"))
    override fun fetchSearchManga(
        page: Int,
        query: String,
        filters: FilterList
    ): Observable<MangasPage> = throw RuntimeException("Stub!")

    /**
     * Returns the request for the search manga given the page.
     *
     * @param page the page number to retrieve.
     * @param query the search query.
     * @param filters the list of filters to apply.
     */
    @Deprecated("Use the new API instead", replaceWith = ReplaceWith("mangaListRequest"))
    open fun searchMangaRequest(page: Int, query: String, filters: FilterList): Request = throw RuntimeException("Stub!")

    /**
     * Parses the response from the site and returns a [MangasPage] object.
     *
     * @param response the response from the site.
     */
    @Deprecated("Use the new API instead", replaceWith = ReplaceWith("mangaListParse"))
    open fun searchMangaParse(response: Response): MangasPage = throw RuntimeException("Stub!")

    /**
     * Returns an observable with the page containing the source url of the image. If there's any
     * error, it will return null instead of throwing an exception.
     *
     * @param page the page whose source image has to be fetched.
     */
    @Deprecated("Use the new suspend API instead", ReplaceWith("getImageUrl"))
    open fun fetchImageUrl(page: Page): Observable<String> {
        throw RuntimeException("Stub!")
    }

    /**
     * Called before inserting a new chapter into database. Use it if you need to override chapter
     * fields, like the title or the chapter number. Do not change anything to [manga].
     *
     * @param chapter the chapter to be added.
     * @param manga the manga of the chapter.
     */
    @Deprecated("All these modification should be done when constructing the chapter")
    open fun prepareNewChapter(chapter: SChapter, manga: SManga) {}
}
