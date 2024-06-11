package com.example.expandandcollapsecardviewwithrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

//https://android-dev.tistory.com/59

class MainActivity : AppCompatActivity() {

    private lateinit var personList: List<Person>
    private lateinit var adapter: ExpandableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_list)

        personList = ArrayList()
        personList = loadData()

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ExpandableAdapter(personList)
        recyclerView.adapter = adapter

    }

    private fun loadData(): List<Person> {
        val people = ArrayList<Person>()

        val persons = resources.getStringArray(R.array.people)
        val descripts = resources.getStringArray(R.array.descript)

        for (i in persons.indices) {
            val person = Person().apply {
                name = persons[i]
                description = descripts.getOrNull(i) ?: "" // Get the corresponding description or empty string if index is out of bounds

            }
            people.add(person)
        }

        return people
    }
}