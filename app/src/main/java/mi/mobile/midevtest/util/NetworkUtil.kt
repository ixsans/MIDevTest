package mi.mobile.midevtest.util

import retrofit2.HttpException
import java.net.HttpURLConnection

/**
 * Created by ikhsan on 21/11/17.
 */
class NetworkUtil
{
    companion object {

        fun getErrorMessage(e: Throwable?): String
        {
            var errorMessage = "Connection failed."
            errorMessage = if (e is HttpException) {
                val errorCode = e.response().code()
                when (errorCode) {
                    HttpURLConnection.HTTP_NOT_FOUND -> errorMessage = "HTTP error, resource not found"
                    HttpURLConnection.HTTP_INTERNAL_ERROR -> errorMessage = "Internal server error"
                    HttpURLConnection.HTTP_FORBIDDEN -> errorMessage = "Access forbidden"
                }
                errorMessage
            }else if (e is IllegalStateException) {
                errorMessage + " " + e.localizedMessage
            }else {
                "Something went wrong"
            }
            return errorMessage
        }
    }
}