package com.example.shapes

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint

import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

//import kotlinx.androidx.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bitmap: Bitmap = Bitmap.createBitmap(700, 1000, Bitmap.Config.ARGB_8888)
        val canvas: Canvas = Canvas(bitmap)
        val paint=Paint()
        paint.color=Color.parseColor("#009943")
        canvas.drawOval(100f,700f,600f,800f,paint)
        paint.color=Color.parseColor("#009943")
        canvas.drawCircle(200f,450f,150f,paint)
        paint.color=Color.parseColor("#009943")
        canvas.drawRect(300f,150f,50f,200f,paint)

        // set bitmap as background to ImageView
        val imageV: ImageView = findViewById(R.id.imageV)
        imageV.setImageBitmap(bitmap)

    }
}
