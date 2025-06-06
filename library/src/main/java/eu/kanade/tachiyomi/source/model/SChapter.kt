package eu.kanade.tachiyomi.source.model

@Suppress("unused")
interface SChapter {

    var url: String

    var name: String

    var date_upload: Long

    var chapter_number: Float

    var scanlator: String?

    companion object {
        fun create(): SChapter {
            throw RuntimeException("Stub!")
        }
    }
}