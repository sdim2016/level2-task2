package com.slepnev.swipequiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val questions = arrayListOf<Question>(
        Question("Ostriches bury their heads up to 18 inches in the sand.", false),
        Question("Louis Braille, creator of the Braille system of writing for the blind, was himself blind.", true),
        Question("According to the Declaration of Independence, the people \"have nothing to lose but their chains.\"", false),
        Question("The Oscar-winning film Guess Who's Coming to Dinner (1967) deals with interracial marriage.", true),
        Question("Flight recorders onboard planes are painted black boxes.",false),
        Question("The \"China Syndrome\" is what hostages experience when they begin to feel empathy for their captors.",false),
        Question("A penny dropped from a tall building can reach sufficient velocity to kill a pedestrian below.",false),
        Question("Harry Potter's archenemy is Tom Marvolo Riddle.",true),
        Question("Other than water, coffee is the world's most popular drink.", true)
    )
    private val questionAdapter = QuestionAdapter(questions)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        rvQuestions.layoutManager = LinearLayoutManager(this@MainActivity, RecyclerView.VERTICAL, false)
        rvQuestions.adapter = questionAdapter
        rvQuestions.addItemDecoration(DividerItemDecoration(this@MainActivity, DividerItemDecoration.VERTICAL))
        createItemTouchHelper().attachToRecyclerView(rvQuestions)
    }

    private fun createItemTouchHelper(): ItemTouchHelper {

        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {

            // Enables or Disables the ability to move items up and down.
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            // Callback triggered when a user swiped an item.
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                if (((direction == ItemTouchHelper.RIGHT) and (questions[position].rightAnswer)) ||
                 ((direction == ItemTouchHelper.LEFT) and (!questions[position].rightAnswer))){
                    questions.removeAt(position)
                } else {
                    Snackbar.make(constraintLayout, "The question won't be removed", Snackbar.LENGTH_SHORT).show()
                }

                questionAdapter.notifyDataSetChanged()
            }
        }
        return ItemTouchHelper(callback)
    }

}
