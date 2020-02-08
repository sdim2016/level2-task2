package com.slepnev.swipequiz

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionAdapter {
    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question) {
            itemView.tvQuestion.text = question.questionText
        }
    }
}