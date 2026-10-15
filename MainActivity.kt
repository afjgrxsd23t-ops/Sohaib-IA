package com.sohaib.ia

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ScrollView

class MainActivity : Activity() {
private val blue = Color.rgb(49, 89, 167)

override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    window.statusBarColor = Color.WHITE
    window.navigationBarColor = Color.WHITE
    window.decorView.systemUiVisibility =
        android.view.View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR

    val root = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(24, 24, 24, 16)
        setBackgroundColor(Color.rgb(247, 249, 253))
    }

    val header = TextView(this).apply {
        text = "صهيب IA"
        textSize = 28f
        setTextColor(blue)
        gravity = Gravity.CENTER
        setPadding(8, 20, 8, 8)
    }
    root.addView(header)

    val subtitle = TextView(this).apply {
        text = "مساعدك الذكي في مكان واحد"
        textSize = 15f
        setTextColor(Color.DKGRAY)
        gravity = Gravity.CENTER
        setPadding(8, 0, 8, 24)
    }
    root.addView(subtitle)

    val conversation = LinearLayout(this).apply {
        orientation = LinearLayout.VERTICAL
        setPadding(14, 14, 14, 14)
    }

    val scroll = ScrollView(this)
    scroll.addView(conversation)
    root.addView(
        scroll,
        LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT, 0, 1f
        )
    )

    addMessage(conversation, "أهلًا بك! أنا صهيب IA، كيف أساعدك؟")

    val inputRow = LinearLayout(this).apply {
        orientation = LinearLayout.HORIZONTAL
        gravity = Gravity.CENTER_VERTICAL
    }

    val input = EditText(this).apply {
        hint = "اكتب رسالتك هنا..."
        textSize = 15f
        setSingleLine(true)
    }

    val send = Button(this).apply {
        text = "إرسال"
        setTextColor(Color.WHITE)
        setBackgroundTintList(
            android.content.res.ColorStateList.valueOf(blue)
        )
    }

    inputRow.addView(
        input,
        LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f)
    )
    inputRow.addView(send)
    root.addView(inputRow)

    send.setOnClickListener {
        val message = input.text.toString().trim()
        if (message.isNotEmpty()) {
            addMessage(conversation, "أنت: $message")
            addMessage(
                conversation,
                "وصلت رسالتك! ربط الذكاء الاصطناعي سنضيفه في الخطوات القادمة."
            )
            input.text.clear()
            scroll.post { scroll.fullScroll(ScrollView.FOCUS_DOWN) }
        }
    }

    setContentView(root)
}

private fun addMessage(container: LinearLayout, message: String) {
    val text = TextView(this).apply {
        this.text = message
        textSize = 16f
        setTextColor(Color.rgb(35, 43, 58))
        setPadding(16, 14, 16, 14)
        setBackgroundColor(Color.WHITE)
    }

    val params = LinearLayout.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT
    )
    params.setMargins(0, 8, 0, 8)
    container.addView(text, params)
}

}
