package co.galixo.appdev.mviBoilerPlate.util

import android.R
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.Spinner
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import coil.transform.CircleCropTransformation
import com.google.android.material.textfield.TextInputLayout


@BindingAdapter("setSpinnerData")
fun Spinner.setData(data: List<String>) {
    val dataAdapter = ArrayAdapter(context, R.layout.simple_spinner_item, data)
    dataAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
    adapter = dataAdapter
}

@BindingAdapter("setRecyclerAdapter")
fun RecyclerView.setRecyclerAdapter(customAdapter: RecyclerView.Adapter<*>) {
    setHasFixedSize(true)
    adapter = customAdapter
}

@BindingAdapter("setImageUrl")
fun ImageView.setImageUrl(imageUrl: String) {
    load(imageUrl) {
        crossfade(true)
        placeholder(R.drawable.ic_menu_crop)
        transformations(CircleCropTransformation())
    }
}

@BindingAdapter("android:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}