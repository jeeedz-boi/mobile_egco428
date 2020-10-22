package com.egco.lab14_gesturezoom

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.MotionEvent
import android.view.ScaleGestureDetector
import android.view.View

class ImageViewWithZoom(context : Context): View(context) {
    private var image: Drawable? = null
    private var scaleFactor = 1.0f
    private var scaleGestureDetector: ScaleGestureDetector? = null

    init {
        ImageViewWithZoom(context)
    }

    private fun ImageViewWithZoom(context: Context){
        image = context.getDrawable(R.drawable.ic_launcher_background)
        isFocusable = true
        image!!.setBounds(0,0,image!!.intrinsicWidth, image!!.intrinsicHeight)
        scaleGestureDetector = ScaleGestureDetector(context, ScaleListener())
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        scaleGestureDetector!!.onTouchEvent(event)
        invalidate()
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas!!.save()
        canvas!!.scale(scaleFactor,scaleFactor)
        image!!.draw(canvas)
        canvas.restore()
    }

    private inner class ScaleListener: ScaleGestureDetector.SimpleOnScaleGestureListener(){
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor *= detector!!.scaleFactor
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 40f))
            invalidate()
            return true
        }
    }
}