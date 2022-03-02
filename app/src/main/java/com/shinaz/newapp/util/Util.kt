
import android.text.TextUtils
import com.shinaz.base.constants.Constants
import com.shinaz.newapp.repository.Locale2
import java.util.HashMap

object Util {
    fun formHeader(type: String? = null): Map<String, String> {

        val headers: MutableMap<String, String> = HashMap()
        headers[Constants.CONTENT_TYPE_KEY] = Constants.CONTENT_TYPE_VALUE

        if(type === null) {
            if (!TextUtils.isEmpty(Locale2.accessToken)) {
                headers[Constants.AUTH_KEY] = Constants.BEARER_KEY + Locale2.accessToken
            } else {
                try {
                    //Fetching token - refresh logic will be handled here.
                    headers[Constants.AUTH_KEY] = Constants.BEARER_KEY + Locale2.accessToken
                } catch (ex: Exception) {
                }
            }
        }
        return headers
    }
}