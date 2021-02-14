package com.hdv.danvoresume

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ValueAnimator
import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.WindowManager
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class StartActivity : AppCompatActivity(), GestureDetector.OnGestureListener {
    lateinit var gestureDetector: GestureDetector
    var x2: Float = 0.0f
    var x1: Float = 0.0f
    var y2: Float = 0.0f
    var y1: Float = 0.0f
    var swiped: Boolean = false
    lateinit var arrow: ImageView

    companion object {
        const val MIN_DIST = 150

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_start)

        val rootLayout = findViewById<ConstraintLayout>(R.id.root_layout)
        val animDraw = rootLayout.background as AnimationDrawable
        animDraw.setEnterFadeDuration(10)
        animDraw.setExitFadeDuration(3000)
        animDraw.start()

        gestureDetector = GestureDetector(this, this)
        arrow = findViewById(R.id.imageView)
        startAnim()
    }


    fun startAnim() {
        val slideAnimator = ValueAnimator.ofFloat(0f, -400f)
        slideAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            arrow.translationY = value


        }
        val fadeAnimator = ValueAnimator.ofFloat(0f, 1f)
        fadeAnimator.addUpdateListener {
            val value = it.animatedValue as Float
            arrow.alpha = value

        }
        val slowslideAnimator = ValueAnimator.ofFloat(-401f, -600f)
        slowslideAnimator.addUpdateListener {

            val value = it.animatedValue as Float
            arrow.translationY = value


        }
        slideAnimator.interpolator = AccelerateDecelerateInterpolator()
        slowslideAnimator.interpolator = AccelerateDecelerateInterpolator()
        fadeAnimator.interpolator = LinearInterpolator()


        val initSet = AnimatorSet()
        initSet.play(slideAnimator).with(fadeAnimator).before(slowslideAnimator)
        initSet.duration = 1000
        initSet.start()
        initSet.addListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
            }

            override fun onAnimationEnd(animation: Animator) {
                initSet.start()
            }

            override fun onAnimationCancel(animation: Animator) {}

            override fun onAnimationRepeat(animation: Animator) {}

        })


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        gestureDetector.onTouchEvent(event)
        when (event?.action) {
            0 -> {
                x1 = event.x
                y1 = event.y
            }
            1 -> {
                x2 = event.x
                y2 = event.y

                val valueX: Float = x2 - x1
                val valueY = y2 - y1


                if (Math.abs(valueY) > MIN_DIST) {
                    if (y1 > y2) {
                        swiped = true
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up)
                    }

                }
            }

        }
        return super.onTouchEvent(event)

    }

    override fun onShowPress(e: MotionEvent?) {


    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        return false
    }

    override fun onDown(e: MotionEvent?): Boolean {
        return false
    }

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean {
        return false
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
    }
}