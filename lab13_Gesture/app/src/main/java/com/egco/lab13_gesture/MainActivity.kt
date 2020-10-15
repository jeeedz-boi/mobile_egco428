package com.egco.lab13_gesture

import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat
import com.egco.lab11_singletouchview.SingleTouch
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {
    // Minimal x and y axis swipe distance.
    private val MIN_SWIPE_DISTANCE_X = 100
    private val MIN_SWIPE_DISTANCE_Y = 100

    // Maximal x and y axis swipe distance.
    private val MAX_SWIPE_DISTANCE_X = 1000
    private val MAX_SWIPE_DISTANCE_Y = 1000

    private var statusMessage: TextView? = null
    private var gestureDetector: GestureDetectorCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(SingleTouch(this))
        this.gestureDetector = GestureDetectorCompat(this,this)
        gestureDetector!!.setOnDoubleTapListener(this)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        this.gestureDetector!!.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onShowPress(p0: MotionEvent?) {
//        statusMsg.text = "onShowPress"
    }

    override fun onSingleTapUp(p0: MotionEvent?): Boolean {
//        statusMsg.text = "onSingleTapUp"
        return true
    }

    override fun onDown(p0: MotionEvent?): Boolean {
//        statusMsg.text = "onDown"
        return true
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, volocityY: Float): Boolean {
//        statusMsg.text = "onFling"

        // Get swipe delta value in x axis.
//        val deltaX = e1!!.x - e2!!.x
//
//        // Get swipe delta value in y axis.
//        val deltaY = e1!!.y - e2!!.y
//
//        // Get absolute value.
//        val deltaXAbs = Math.abs(deltaX)
//        val deltaYAbs = Math.abs(deltaY)
//
//        if((deltaXAbs >= MIN_SWIPE_DISTANCE_X) && (deltaXAbs <= MAX_SWIPE_DISTANCE_X))
//        {
//            if(deltaX > 0)
//            {
//                statusMsg!!.text ="Swipe to left"
//            }else
//            {
//                statusMsg!!.text ="Swipe to right";
//            }
//        }
//
//        if((deltaYAbs >= MIN_SWIPE_DISTANCE_Y) && (deltaYAbs <= MAX_SWIPE_DISTANCE_Y))
//        {
//            if(deltaY > 0)
//            {
//                statusMsg!!.text ="Swipe to up";
//            }else
//            {
//                statusMsg!!.text ="Swipe to down";
//            }
//        }

        return true
    }

    override fun onScroll(p0: MotionEvent?, p1: MotionEvent?, p2: Float, p3: Float): Boolean {
//        statusMsg.text = "onScroll"
        return true
    }

    override fun onLongPress(p0: MotionEvent?) {
//        statusMsg.text = "onLongPress"
    }

    override fun onDoubleTap(p0: MotionEvent?): Boolean {
//        statusMsg.text = "onDoubleTapEvent"
        return true
    }

    override fun onDoubleTapEvent(p0: MotionEvent?): Boolean {
//        statusMsg.text = "onDoubleTapEvent"
        return true
    }

    override fun onSingleTapConfirmed(p0: MotionEvent?): Boolean {
//        statusMsg.text = "onSingleTapConfirmed"
        return true
    }


}