import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.raisproject.core.R
import com.raisproject.core.utils.Constant
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.loadImage(url: String?) {
    Glide.with(this.context)
        .load(Constant.IMAGE_URL + url)
        .into(this)
}

fun String.convertDate(inputFormat: String, outputFormat: String): String {
    val formatter = SimpleDateFormat(inputFormat, Locale.US)
    var formatParser = Date()
    if (this.isNotEmpty()) {
        formatParser = formatter.parse(this) ?: Date()
    }
    val newOutputFormat = SimpleDateFormat(outputFormat, Locale.US)
    return newOutputFormat.format(formatParser)
}