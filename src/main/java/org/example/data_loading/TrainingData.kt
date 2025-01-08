package org.example.data_loading

data class TrainingData (val classification: SignClassification, val signProperties: SignProperties)
data class TrainingDataWithRawFeatureVector (val classification: SignClassification, val featureVector: List<Double>)