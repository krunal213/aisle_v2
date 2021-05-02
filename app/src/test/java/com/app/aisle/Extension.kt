package com.app.aisle

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