import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplicationviewbinding.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val moves = listOf("剪刀", "石頭", "布")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupPlayButton()
    }

    private fun setupPlayButton() {
        binding.btnMora.setOnClickListener {
            if (binding.edName.text.isEmpty()) {
                binding.tvText.text = "請輸入玩家姓名"
                return@setOnClickListener
            }
            playGame()
        }
    }

    private fun playGame() {
        val playerName = binding.edName.text.toString()
        val playerMove = getPlayerMove()
        val computerMove = (0..2).random()

        displayResults(playerName, playerMove, computerMove)
        determineWinner(playerName, playerMove, computerMove)
    }

    private fun getPlayerMove(): Int {
        return when (binding.radioGroup.checkedRadioButtonId) {
            binding.btnScissor.id -> 0
            binding.btnStone.id -> 1
            else -> 2
        }
    }

    private fun displayResults(playerName: String, playerMove: Int, computerMove: Int) {
        binding.tvName.text = "名字\n$playerName"
        binding.tvMmora.text = "我方出拳\n${moves[playerMove]}"
        binding.tvCmora.text = "電腦出拳\n${moves[computerMove]}"
    }

    private fun determineWinner(playerName: String, playerMove: Int, computerMove: Int) {
        when {
            playerMove == computerMove -> {
                binding.tvWinner.text = "勝利者\n平手"
                binding.tvText.text = "˚　　　　✦　　　.　　. 　˚　.\n平局，請再玩一次！\n . ✦　　　 　˚　　　　 . ★⋆."
            }
            (playerMove == 0 && computerMove == 2) ||
                    (playerMove == 1 && computerMove == 0) ||
                    (playerMove == 2 && computerMove == 1) -> {
                binding.tvWinner.text = "勝利者\n$playerName"
                binding.tvText.text = "恭喜你獲勝了！！！\n\n/ ᐢ⑅ᐢ \\   ♡   ₊˚\n     ꒰ ˶• ༝ •˶꒱  ♡‧₊˚    ♡\n./づ~ :¨·.·¨:     ₊˚\n                `·..·‘    ₊˚   ♡"
            }
            else -> {
                binding.tvWinner.text = "勝利者\n電腦"
                binding.tvText.text = "可惜，電腦獲勝了(˃̣̣̥ᯅ˂̣̣̥)\n\n    ⣴⠟⢷⣤⡞⢳⣴⡟⢳⡄⣿⠳⡔⣧⠀⣼⠆\n    ⠛⢷⡝⣿⠀⢈⣯⣇⣴⠗⣿⣠⡟⠸⣿⠃\n⢸⣦⣿⠹⣦⡼⠻⡟⠷⣤⣿⠿⣄⠀⣿"
            }
        }
    }
}