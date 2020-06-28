package r.test.data.model

import android.os.Build
import android.text.Html
import android.text.Spanned

data class MovieVo(
    val items: List<Item>,
    val errorMessage: String
)

class Item(
    val image: String,
    val link: String,
    val title: String
) {

    fun getHtmlTitle(): Spanned {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(title, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(title)
        }
    }
}