package co.galixo.appdev.mviBoilerPlate.util

import java.util.regex.Matcher
import java.util.regex.Pattern

class ValidationUtilis {

    fun validateName(name: String?, n: Int, m: Int): Boolean {
        if (name == null || name.length < n || name.length > m) {
            return false
        }
        val pattern: Pattern = Pattern.compile("[a-zA-Z\\s]*")
        val matcher: Matcher = pattern.matcher(name)
        return matcher.matches()
    }
}