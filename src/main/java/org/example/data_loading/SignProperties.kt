package org.example.data_loading

/** Properties of a sign.
 * @param colors List of colors of the sign with their share on the total sign.
 * @param cornersCountNormalized The number of corners normalized to a value between 0 and 1.
 * @param centerSymbolAnalyzerResult The result of the center symbol analysis.
 * */
class SignProperties(private val colors: List<SignColor>, private val cornersCountNormalized: Double, private val centerSymbolAnalyzerResult: CenterSymbolAnalyzerResult) {
    init {
       require(colors.size == ApproximatedColor.entries.size) { "List of colors must contain all colors" }
         require(cornersCountNormalized in 0.0..1.0) { "Corners count must be between 0 and 1" }
    }

    fun toFeatureVector() : List<Double> {
        return listOf(colors.sortedBy { it.getApproximatedColor().ordinal }.map { it.getShareOnSign() }, listOf(cornersCountNormalized), listOf(centerSymbolAnalyzerResult.getCenterSymbolColorAvg(), centerSymbolAnalyzerResult.getPercentagePixelsLeft(), centerSymbolAnalyzerResult.getPercentagePixelsRight())).flatten()
    }

    companion object {fun fromFeatureVector(featureVector: List<Double>): SignProperties {
        val colors = featureVector.subList(0, ApproximatedColor.entries.size).mapIndexed { index, shareOnSign -> SignColor(
            ApproximatedColor.values()[index], shareOnSign) }
        val cornersCountNormalized = featureVector[ApproximatedColor.entries.size]
        val centerSymbolAnalyzerResult = CenterSymbolAnalyzerResult(featureVector[ApproximatedColor.entries.size + 1], featureVector[ApproximatedColor.entries.size + 2], featureVector[ApproximatedColor.entries.size + 3])
        return SignProperties(colors, cornersCountNormalized, centerSymbolAnalyzerResult)
    }}
}