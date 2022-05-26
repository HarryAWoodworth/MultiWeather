package com.harryawoodworth.multiweather.util

import java.io.InputStreamReader

/**
 * Class that opens a json resource files to read and save its contents.
 */
class MockResponseFileReader(path: String) {

    val content: String
    val reader: InputStreamReader

    /**
     * Open the mock json resource file using the passed in path and save its contents.
     */
    init {
        reader = InputStreamReader(this.javaClass.classLoader?.getResourceAsStream(path))
        content = reader.readText()
        reader.close()
    }



}