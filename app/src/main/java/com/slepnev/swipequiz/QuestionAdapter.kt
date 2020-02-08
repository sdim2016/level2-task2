package com.slepnev.swipequiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_question.view.*

class QuestionAdapter(private val questions:List<Question>) : RecyclerView.Adapter<QuestionAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bind(question: Question) {
            itemView.tvQuestion.text = question.questionText
            itemView.setOnClickListener {
                Toast.makeText(itemView.context, "This statement is "+question.rightAnswer.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }
}