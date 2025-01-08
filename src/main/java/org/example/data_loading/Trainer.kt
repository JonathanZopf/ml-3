package org.example.data_loading

import com.google.gson.Gson
import java.io.File

fun readTrainingData(): List<TrainingData> {
    val gson = Gson()
    val rawRes = getTrainingFile().readLines().map { line -> gson.fromJson(line, TrainingDataWithRawFeatureVector::class.java) }
    return rawRes.map { TrainingData(it.classification, SignProperties.fromFeatureVector(it.featureVector)) }
}

private fun getTrainingFile(): File {
    val classloader = Thread.currentThread().contextClassLoader
    val fileLocationFile = classloader.getResourceAsStream("training_data_location.txt")
        ?: throw IllegalArgumentException("There is no training_data_location.txt in the resources folder")
    val fileLocation = fileLocationFile.bufferedReader().use { it.readText() }

    val file = File(fileLocation)
    if (!file.exists()) {
        throw IllegalArgumentException("Invalid file location: $fileLocation")
    }
    return file
}