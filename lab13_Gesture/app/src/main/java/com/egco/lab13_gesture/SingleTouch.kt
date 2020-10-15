package com.egco.lab11_singletouchview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View

class SingleTouch(context: Context): View(context) {
    private var paint1 = Paint()
    private var path1 = Path()
    private var paint2 = Paint()
    private var path2 = Path()
    private var paint3 = Paint()
    private var path3 = Path()

    private var pathUsed = path1
    private var paintUsed = paint1

    private var eventX:Float = 0f
    private var eventY:Float = 0f
    private var fingerDown = false

    private var i = 0
    init {
        paint1.isAntiAlias = true
        paint1.strokeWidth = 6f
        paint1.color = Color.BLACK
        paint1.style = Paint.Style.STROKE
        paint1.strokeJoin = Paint.Join.ROUND

        paint2.isAntiAlias = true
        paint2.strokeWidth = 6f
        paint2.color = Color.BLUE
        paint2.style = Paint.Style.STROKE
        paint2.strokeJoin = Paint.Join.ROUND

        paint3.isAntiAlias = true
        paint3.strokeWidth = 6f
        paint3.color = Color.RED
        paint3.style = Paint.Style.STROKE
        paint3.strokeJoin = Paint.Join.ROUND

    }

    override fun onTouchEvent(event: MotionEvent?):Boolean{
        eventX = event!!.x
        eventY = event.y

        when(event.action){
            MotionEvent.ACTION_DOWN->{
                fingerDown = true
                pathUsed.moveTo(eventX,eventY)
                return true
            }
            MotionEvent.ACTION_MOVE->{
                pathUsed.lineTo(eventX,eventY)
            }
            MotionEvent.ACTION_UP->{
                when (pathUsed) {
                    path3 -> {
                        pathUsed = path1
                        paintUsed = paint1
                    }
                    path1 -> {
                        pathUsed = path2
                        paintUsed = paint2
                    }
                    path2 -> {
                        pathUsed = path3
                        paintUsed = paint3
                    }
                }

                fingerDown = false
            }
            else -> return false
        }

            
        invalidate() // set it to off
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?){
        super.onDraw(canvas)
        canvas!!.drawPath(path1,paint1)
        canvas!!.drawPath(path2,paint2)
        canvas!!.drawPath(path3,paint3)

        if (fingerDown){
            canvas.drawCircle(eventX,eventY,20F,paintUsed)
        }

    }

}

