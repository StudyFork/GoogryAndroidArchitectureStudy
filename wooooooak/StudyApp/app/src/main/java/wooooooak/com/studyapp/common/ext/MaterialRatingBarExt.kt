package wooooooak.com.studyapp.common.ext

import androidx.databinding.BindingAdapter
import me.zhanghai.android.materialratingbar.MaterialRatingBar

@BindingAdapter("rating")
fun MaterialRatingBar.setRating(_rating: Float) {
    rating = _rating / 2
}