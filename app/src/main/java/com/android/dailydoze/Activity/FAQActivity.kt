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
                "DailyDoze is a health-focused app designed as a " +
                        "checklist to inspire users to incorporate nutrient-rich " +
                        "foods into their diets. It encourages meal planning and " +
                        "improving the overall nutritional quality of meals. " +
                        "Customizable to individual preferences and needs, " +
                        "it serves as a personalized food diary, aiming to " +
                        "promote healthier eating habits."
            )
        )
        faqItems.add(
            FAQItem(
                "What is 21 Tweaks?",
                "21 Tweaks is a 21-day challenge focused on weight loss and " +
                        "health improvement through small, manageable adjustments to " +
                        "daily routines. It offers a simple and effective approach to " +
                        "achieving these goals by emphasizing gradual changes over the " +
                        "course of the challenge. By incorporating these tweaks into daily " +
                        "habits, participants can work towards their weight loss and health " +
                        "objectives in a structured and achievable manner."
            )
        )

        val adapter = FAQAdapter(this, faqItems)
        faqListView.adapter = adapter
    }

    fun faqFinish(view: View) {
        finish()
    }
}