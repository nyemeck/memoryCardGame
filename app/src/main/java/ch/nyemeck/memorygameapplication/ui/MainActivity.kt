package ch.nyemeck.memorygameapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ch.nyemeck.memorygameapplication.data.GameBoard
import ch.nyemeck.memorygameapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }

    private lateinit var mainActivityBinding : ActivityMainBinding
    private lateinit var recyclerView : RecyclerView
    private lateinit var buttonPlay : Button
    private lateinit var gameBoard : GameBoard
    private lateinit var memoryGameAdapter : MemoryGameAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainActivityBinding.root)
        recyclerView = mainActivityBinding.recyclerViewId
        buttonPlay = mainActivityBinding.buttonPlayId

        //Building the board game
        setTheGame()
        mainActivityBinding.buttonPlayId.setOnClickListener{
            Log.d(TAG, "onCreate onclick finish: ${gameBoard.finish()}")
            if (gameBoard.finish()){
                setTheGame()
            }
        }
    }

    private fun setTheGame() {
        gameBoard = GameBoard()

        //Init the recycler view
        memoryGameAdapter = MemoryGameAdapter(gameBoard.level, gameBoard.cardItems) { position ->
            updateBoard(position)
        }

        val gridLayoutManager = GridLayoutManager(this, gameBoard.level.getColumn())
        recyclerView.apply {
            adapter = memoryGameAdapter
            setHasFixedSize(true)
            layoutManager = gridLayoutManager
        }
    }

    private fun updateBoard(position: Int) {
        if(gameBoard.finish()){

            return
        }
        if(gameBoard.cardItemFacingUp(position)){
            return
        }
        gameBoard.flipCard(position)
        memoryGameAdapter.notifyDataSetChanged()
    }
}