package ch.nyemeck.memorygameapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.nyemeck.memorygameapplication.data.BoardGameLevel
import ch.nyemeck.memorygameapplication.databinding.ActivityMainBinding
import ch.nyemeck.memorygameapplication.util.ICONS

class MainActivity : AppCompatActivity() {

    private lateinit var mainActivityBinding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var buttonPlay : Button
    private val boardGameLevel =  BoardGameLevel.EASY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        recyclerView = mainActivityBinding.recyclerViewId
        buttonPlay = mainActivityBinding.buttonPlayId

        //Building the board game
        val chosenCards = ICONS.shuffled().take(boardGameLevel.getNumberOfPairs())
        val cards = (chosenCards + chosenCards).shuffled()
        //Init the recycler view
        val memoryGameAdapter = MemoryGameAdapter(this, boardGameLevel, cards)
        val gridLayoutManager = GridLayoutManager(this,boardGameLevel.getColumn())
        recyclerView.apply {
            adapter = memoryGameAdapter
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
        }
        /*recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = MemoryGameAdapter(this, 8)
        recyclerView.setHasFixedSize(true)*/

    }
}