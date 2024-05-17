package com.example.alarm
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class AnalogClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val backgroundPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        style = Paint.Style.FILL
    }

    private val circlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.STROKE
        strokeWidth = 8f
    }

    private val hourPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        strokeWidth = 16f
        strokeCap = Paint.Cap.ROUND
    }

    private val minutePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        style = Paint.Style.FILL
        strokeWidth = 8f
        strokeCap = Paint.Cap.ROUND
    }

    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.BLACK
        textSize = 36f
        textAlign = Paint.Align.CENTER
    }

    private var radius: Float = 0f
    private var hour: Int = 0
    private var minute: Int = 0

    fun setTime(hour: Int, minute: Int) {
        this.hour = hour
        this.minute = minute
        invalidate()
    }

    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)
        radius = min(width, height) / 2f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val centerX = width / 2f
        val centerY = height / 2f

        // Draw background
        canvas.drawCircle(centerX, centerY, radius, backgroundPaint)

        // Draw circle outline
        canvas.drawCircle(centerX, centerY, radius - circlePaint.strokeWidth / 2, circlePaint)

        // Draw hour markers
        for (i in 0 until 12) {
            val hourMarkerX = centerX + radius * 0.9f * sin(Math.toRadians(i * 30.toDouble())).toFloat()
            val hourMarkerY = centerY - radius * 0.9f * cos(Math.toRadians(i * 30.toDouble())).toFloat()
            canvas.drawCircle(hourMarkerX, hourMarkerY, 20f, hourPaint)
        }

        // Draw minute markers
        for (i in 0 until 60 step 5) {
            val minuteMarkerX = centerX + radius * 0.95f * sin(Math.toRadians(i * 6.toDouble())).toFloat()
            val minuteMarkerY = centerY - radius * 0.95f * cos(Math.toRadians(i * 6.toDouble())).toFloat()
            canvas.drawCircle(minuteMarkerX, minuteMarkerY, 10f, minutePaint)
        }

        // Draw hour hand
        val hourAngle = Math.toRadians((hour % 12) * 30 + minute / 2.0)
        val hourHandLength = radius * 0.5f
        canvas.drawLine(
            centerX,
            centerY,
            centerX + hourHandLength * sin(hourAngle).toFloat(),
            centerY - hourHandLength * cos(hourAngle).toFloat(),
            hourPaint
        )

        // Draw minute hand
        val minuteAngle = Math.toRadians(minute * 6.0)
        val minuteHandLength = radius * 0.8f
        canvas.drawLine(
            centerX,
            centerY,
            centerX + minuteHandLength * sin(minuteAngle).toFloat(),
            centerY - minuteHandLength * cos(minuteAngle).toFloat(),
            minutePaint
        )

        // Draw current time text
        val currentTime = String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
        canvas.drawText(currentTime, centerX, centerY + textPaint.textSize / 3, textPaint)
    }
}
