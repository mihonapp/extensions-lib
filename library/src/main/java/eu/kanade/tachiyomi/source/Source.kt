package eu.kanade.tachiyomi.source

import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.model.MangasPage
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import rx.Observable

/**
 * A basic interface for creating a source. It could be an online source, a local source, etc...
 */
@Suppress("unused")
interface Source {

    /**
     * Id for the source. Must be unique.
     */
    val id: Long

    /**
     * Name of the source.
     */
    val name: String

    /**
     * Represents an IETF BCP 47 compliant language tag.
     * Special cases include:
     * - [Language.MULTI]: Indicates multiple languages.
     * - [Language.OTHER]: Refers to a language not explicitly defined.
     * - 'all': Indicates multiple language.
     * 
     * Usage of 'all' is highly discouraged and is only supported due to legacy reasons.
     *
     * @since extensions-lib 1.5
     */
    val language: String

    /**
     * Returns the list of filters for the source.
     *
     * @since extensions-lib 1.5
     */
    fun getFilterList(): FilterList

    /**
     * Get a page with a list of manga.
     *
     * @since extensions-lib 1.5
     * @param query the search query.
     * @param filters the list of filters to apply.
     * @param page the page number to retrieve.
     */
    suspend fun getMangaList(query: String, filters: FilterList, page: Int): MangasPage = throw Exception("Stub!")

    /**
     * Get the updated details for a manga and its chapters
     *
     * @since extensions-lib 1.5
     * @param manga manga to get details and chapters for
     * @return the updated manga and its chapters
     */
    suspend fun getMangaDetailsAndChapters(manga: SManga): Pair<SManga, List<SChapter>> = throw Exception("Stub!")

    /**
     * Get the updated details for a manga.
     *
     * @since extensions-lib 1.4
     * @param manga the manga to update.
     * @return the updated manga.
     */
    suspend fun getMangaDetails(manga: SManga): SManga = throw Exception("Stub!")

    /**
     * Get all the available chapters for a manga.
     *
     * @since extensions-lib 1.4
     * @param manga the manga to update.
     * @return the chapters for the manga.
     */
    suspend fun getChapterList(manga: SManga): List<SChapter> = throw Exception("Stub!")

    /**
     * Get the list of pages a chapter has. Pages should be returned
     * in the expected order; the index is ignored.
     *
     * @since extensions-lib 1.5
     * @param chapter the chapter.
     * @return the pages for the chapter.
     */
    suspend fun getPageList(chapter: SChapter): List<Page> = throw Exception("Stub!")

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getMangaDetails"),
    )
    fun fetchMangaDetails(manga: SManga): Observable<SManga> = throw Exception("Stub!")

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getChapterList"),
    )
    fun fetchChapterList(manga: SManga): Observable<List<SChapter>> = throw Exception("Stub!")

    @Deprecated(
        "Use the non-RxJava API instead",
        ReplaceWith("getPageList"),
    )
    fun fetchPageList(chapter: SChapter): Observable<List<Page>> = throw Exception("Stub!")

    object Language {
        const val MULTI = "multi"
        const val OTHER = "other"
    }
}
