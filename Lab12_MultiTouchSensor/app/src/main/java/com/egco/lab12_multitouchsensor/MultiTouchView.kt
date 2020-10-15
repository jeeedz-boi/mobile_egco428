package com.egco.lab12_multitouchsensor

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View

class MultiTouchView(context: Context): View(context){

    private var mActivePointers: SparseArray<PointF>? = null
    private var mPaint: Paint
    private val colors = intArrayOf(Color.BLUE, Color.GREEN, Color.MAGENTA, Color.BLACK, Color.CYAN, Color.GRAY, Color.RED, Color.DKGRAY, Color.LTGRAY, Color.YELLOW)

    private var textPaint: Paint? = null

    init {
        mActivePointers = SparseArray<PointF>()
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        // set painter color to a color you like
        mPaint!!.color = Color.BLUE
        mPaint!!.style = Paint.Style.FILL_AND_STROKE
        textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        textPaint!!.textSize = 20F
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val pointerIndex = event!!.actionIndex
        val pointerId = event!!.getPointerId(pointerIndex)
        val maskedAction = event!!.actionMasked

        when(maskedAction){
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN ->{
                val f = PointF()
                f.x = event.getX(pointerIndex)
                f.y = event.getY(pointerIndex)
                mActivePointers!!.put(pointerId, f)
            }

            MotionEvent.ACTION_MOVE ->{
                val size = event.pointerCount
                var i = 0

                while(i<size){package com.egco.lab11_singletouchview

                    import android.content.Context
                            import android.graphics.Canvas
                            import android.graphics.Color
                            import android.graphics.Paint
                            import android.graphics.Path
                            import android.view.MotionEvent
                            import android.view.View

                    class SingleTouchView(context: Context): View(context) {
                        private var paint = Paint()
                        private var path = Path()
                        private var eventX:Float = 0f
                        private var eventY:Float = 0f
                        private var fingerDown = false
                        init {
                            paint.isAntiAlias = true
                            paint.strokeWidth = 6f
                            paint.color = Color.BLACK
                            paint.style = Paint.Style.STROKE
                            paint.strokeJoin = Paint.Join.ROUND
                        }

                        override fun onTouchEvent(event: MotionEvent?):Boolean{
                            eventX = event!!.x
                            eventY = event.y

                            when(event.action){
                                MotionEvent.ACTION_DOWN->{
                                    fingerDown = true
                                    path.moveTo(eventX,eventY)
                                    return true
                                }
                                MotionEvent.ACTION_MOVE->{
                                    path.lineTo(eventX,eventY)
                                }
                                MotionEvent.ACTION_UP->{
                                    fingerDown = false
                                }
                                else -> return false
                            }
                            invalidate() // set it to off
                            return super.onTouchEvent(event)
                        }

                        override fun onDraw(canvas: Canvas?){
                            super.onDraw(canvas)

                            canvas!!.drawPath(path,paint)
                            if (fingerDown){
                                canvas.drawCircle(eventX,eventY,20F,paint)
                            }

                        }

                    }


                    val point = mActivePointers!!.get(event.getPointerId(i))
                    if (point!=null){
                        point.x = event.getX(i)
                        point.y = event.getY(i)
                    }
                    i++
                }
            }

            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP ->{
                mActivePointers!!.remove(pointerId)
            }
        }
        invalidate()
//        return super.onTouchEvent(event)
        return true
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        val size = mActivePointers!!.size()
        var i = 0
        while(i<size){
            val point = mActivePointers!!.valueAt(i)
            if (point!=null){
                mPaint!!.color = colors[i%9]
            }
            canvas!!.drawCircle(point.x,point.y,40f,mPaint)
            i++
        }
        canvas!!.drawText("total pointer:"+mActivePointers!!.size(),10f,40f,textPaint!!)

    }
}