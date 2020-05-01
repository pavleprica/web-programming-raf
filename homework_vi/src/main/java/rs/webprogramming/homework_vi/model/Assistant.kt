package rs.webprogramming.homework_vi.model

data class Assistant(
        val name: String,
        val score: Int?,
        val averageScore: Float?
) {
    override fun toString(): String {
        return "$name - score: $averageScore"
    }
}