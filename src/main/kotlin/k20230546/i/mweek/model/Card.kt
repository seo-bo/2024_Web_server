package k20230546.i.mweek.model

data class Card(val rank: String, val suit: String) {
    val imageName: String
        get() = "${rank}_of_${suit}.png"
}