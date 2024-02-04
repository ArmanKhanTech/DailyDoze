package com.android.dailydoze.Activity

import android.os.Bundle
import android.view.View
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.android.dailydoze.Adapter.FAQAdapter
import com.android.dailydoze.Model.FAQItem
import com.android.dailydoze.R

class FAQActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_faq)

        val faqListView = findViewById<ListView>(R.id.faq_list)

        val faqItems: MutableList<FAQItem> = ArrayList()
        faqItems.add(
            FAQItem(
                "What is DailyDoze?",
                "This app is a simple FAQ example."
            )
        )
        faqItems.add(
            FAQItem(
                "How does DailyDoze work?",
                "It displays a list of frequently asked questions and their answers."
            )

        )
        faqItems.add(
            FAQItem(
                "What is 21 Tweaks?",
                "It displays a list of frequently asked questions and their answers."
            )
        )
        faqItems.add(
            FAQItem(
                "How does 21 Tweaks work?",
                "It displays a list of frequently asked questions and their answers."
            )
        )

        val adapter = FAQAdapter(this, faqItems)
        faqListView.adapter = adapter
    }

    fun faqFinish(view: View) {
        finish()
    }
}