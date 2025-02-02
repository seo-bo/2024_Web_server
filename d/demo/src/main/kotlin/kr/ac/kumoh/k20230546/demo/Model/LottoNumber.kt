package kr.ac.kumoh.k20230546.demo.Model

data class LottoNumber(
    val numbers: IntArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LottoNumber

        return numbers.contentEquals(other.numbers)
    }

    override fun hashCode(): Int {
        return numbers.contentHashCode()
    }
}
