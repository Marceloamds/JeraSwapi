package com.engefour.jeraswapi


import android.app.Activity
import android.app.Dialog
import android.view.Window
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget

//Usado pra colocar um gif com a biblicoteca "Glide" dentro de uma caixa de dialogo
class LoadingDialog(private val activity: Activity) {
    private var dialog: Dialog? = null

    fun showDialog() {

        dialog = Dialog(activity)
        dialog?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        //...set cancelable false so that it's never get hidden
        dialog?.setCancelable(false)
        dialog?.setContentView(R.layout.custom_loading_layout)

        //...initialize the imageView form infalted layout
        val gifImageView = dialog?.findViewById<ImageView>(R.id.custom_loading_imageView)
        val imageViewTarget = DrawableImageViewTarget(gifImageView)

        //...now load that gif which we put inside the drawble folder here with the help of Glide

        Glide.with(activity)
            .load(R.drawable.loading)
            .centerCrop()
            .into(imageViewTarget)

        //...finaly show it
        dialog?.show()
    }

    //..also create a method which will hide the dialog when some work is done
    fun hideDialog() {
        dialog!!.dismiss()
    }

    fun isShowing():Boolean?{
        return dialog?.isShowing
    }


}