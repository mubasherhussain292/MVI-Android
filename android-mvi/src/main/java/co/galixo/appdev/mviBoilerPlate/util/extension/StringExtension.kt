package co.galixo.appdev.mviBoilerPlate.util.extension

import android.annotation.SuppressLint
import android.app.Activity
import android.app.DownloadManager
import android.content.Context
import android.net.Uri
import android.os.Environment
import androidx.core.app.ActivityCompat
import co.galixo.appdev.mviBoilerPlate.util.CommonUtilis
import java.text.SimpleDateFormat

fun String.saveTo(context: Context, path: String, fileName:String) {
    val activity = context as Activity
    val commonUtilis = CommonUtilis()
    if(commonUtilis.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE",context) && commonUtilis. hasPermission("android.permission.READ_EXTERNAL_STORAGE",context)) {
        val downloadmanager: DownloadManager = activity.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        val uri = Uri.parse(this)
        val request: DownloadManager.Request = DownloadManager.Request(uri)
        request.setTitle(fileName)
        request.setDescription("Downloading")
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        request.setDestinationUri(Uri.parse("file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).path + "/" + fileName))
        downloadmanager.enqueue(request)
    }
    else{
        ActivityCompat.requestPermissions(
            activity,
            arrayOf(
                "android.permission.WRITE_EXTERNAL_STORAGE",
                "android.permission.READ_EXTERNAL_STORAGE"
            ),
            100
        )
    }
}
@SuppressLint("SimpleDateFormat")
fun String.formatDate(fromFormat:String, toFormat:String) :String {
    val input = SimpleDateFormat(fromFormat)
    val output = SimpleDateFormat(toFormat)
    return output.format(input.parse(this))
}

