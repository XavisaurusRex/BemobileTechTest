package cat.devsofthecoast.bemobiletechtest.common.extensions.context

import android.content.Context
import java.io.BufferedReader

fun Context.readAssetFileToString(fileName: String): String =
    this.assets
        .open(fileName)
        .bufferedReader()
        .use(BufferedReader::readText)