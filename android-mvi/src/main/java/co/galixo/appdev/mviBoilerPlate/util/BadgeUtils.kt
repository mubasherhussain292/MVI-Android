package co.galixo.appdev.mviBoilerPlate.util

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo

object BadgeUtils {

    fun setBadge(context: Context, count: Int) {
        setBadgeUniversal(context, count)

    }

    fun clearBadge(context: Context) {
        setBadgeUniversal(context, 0)

    }

    fun setBadgeUniversal(context: Context, count: Int) {
        val launcherClassName = getLauncherClassName(context) ?: return
        val intent = Intent("android.intent.action.BADGE_COUNT_UPDATE")
        intent.putExtra("badge_count", count)
        intent.putExtra("badge_count_package_name", context.packageName)
        intent.putExtra("badge_count_class_name", launcherClassName)
        context.sendBroadcast(intent)
    }


    fun getLauncherClassName(context: Context): String? {
        val pm: PackageManager = context.packageManager
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_LAUNCHER)
        val resolveInfos: List<ResolveInfo> = pm.queryIntentActivities(intent, 0)
        for (resolveInfo in resolveInfos) {
            val pkgName: String = resolveInfo.activityInfo.applicationInfo.packageName
            if (pkgName.equals(context.packageName, ignoreCase = true)) {
                return resolveInfo.activityInfo.name
            }
        }
        return null
    }
}