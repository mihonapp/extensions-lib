package mihon.source.model

class Listing(val id: String, val name: String) {

    override fun equals(other: Any?) = other is Listing && id == other.id

    override fun hashCode() = id.hashCode()

    override fun toString() = "Listing(id=$id, name=$name)"

    companion object Preset {
        // Consumers should localize the presets separately
        val Default = Listing("default", "Default")
        val Popular = Listing("popular", "Popular")
        val Latest = Listing("latest", "Latest")
        val Ranking = Listing("ranking", "Ranking")
    }
}