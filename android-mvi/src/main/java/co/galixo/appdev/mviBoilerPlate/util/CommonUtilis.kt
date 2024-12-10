package co.galixo.appdev.mviBoilerPlate.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.PermissionChecker.checkSelfPermission

class CommonUtilis {

    @SuppressLint("WrongConstant")
    fun hasPermission(permission: String?, context: Context): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(context,permission!!) == PackageManager.PERMISSION_GRANTED
    }
}