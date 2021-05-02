package com.app.aisle.extension

import android.app.Application
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.AndroidViewModel
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.app.aisle.R
import com.app.aisle.Result
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

fun <T> AndroidViewModel.error(
    errorMsg: Int
): Result<T> {
    return Result
        .Error(getApplication<Application>()
            .getString(errorMsg)
            .let {
                Exception(it)
            })
}

fun Boolean?.ifTrueThen(block : () ->Unit) : Boolean?{
    this?.let {
        if(it){
            block()
        }
    }
    return this
}

fun Boolean?.orElse(block : () -> Unit) {
    if(this==null){
        block()
    }
    this?.let {
        if(!it){
            block()
        }
    }
}

fun String?.indiaCodePrefix() : String{
    return "+91$this"
}

fun <T> Boolean?.ifElse(ifBlock : () -> T,elseBlock : () -> T) : T {
    this?.let {
        if(it){
            return ifBlock()
        }
    }
    return elseBlock()
}

fun <T> String?.ifElse(ifBlock : (it : String) -> T,elseBlock : () -> T) : T {
    this?.let {
        if(it.trim().isNotEmpty()){
            return ifBlock(it)
        }
    }
    return elseBlock()
}

@BindingAdapter("android:src")
fun ImageView.src(imageUrl : String?) {
    Glide.with(this)
        .load(imageUrl)
        .override(width,width)
        .apply(RequestOptions.bitmapTransform( RoundedCorners(resources.getDimensionPixelSize(R.dimen.eight_dp))))
        .into(this)
}

fun ImageView.imgsrc(imageUrl : String?) {
    Glide.with(this)
        .load(imageUrl)
        .centerCrop()
        .into(this)
}













