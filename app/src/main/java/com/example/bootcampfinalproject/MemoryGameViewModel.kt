// MemoryGameViewModel.kt

// ViewModel Factory'ye ihtiyacımız olacak, bu yüzden bunun için bir sınıf oluşturalım.
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.bootcampfinalproject.CardItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

// ViewModel'i güncelliyoruz
class MemoryGameViewModel(private val difficulty: String) : ViewModel() {

    // Kart sayılarını zorluğa göre belirleyelim
    private val numCards: Int = if (difficulty == "HARD") 24 else 16
    private val numUniqueNumbers: Int = numCards / 2 // Her sayıdan 2 tane olacağı için

    private val _cards = mutableStateListOf<CardItem>()
    val cards: List<CardItem> = _cards

    private val _score = mutableStateOf(0)
    val score: State<Int> = _score

    private val _timeLeft = mutableStateOf(60)
    val timeLeft: State<Int> = _timeLeft

    private val _selectedCards = mutableStateListOf<CardItem>()

    private val _gameEnded = mutableStateOf(false)
    val gameEnded: State<Boolean> = _gameEnded

    private val _gameWon = mutableStateOf(false)
    val gameWon: State<Boolean> = _gameWon

    init {
        startGame()
        startTimer()
    }

    private fun generateCards(): List<CardItem> {
        val numbers = mutableListOf<Int>()
        val random = Random()
        // Belirlenen sayıda farklı sayı oluştur
        while (numbers.size < numUniqueNumbers) {
            val randomNumber = random.nextInt(100) + 1
            if (randomNumber !in numbers) {
                numbers.add(randomNumber)
            }
        }

        // Her sayıdan ikişer tane olacak şekilde kartları oluştur
        val generatedCards = mutableListOf<CardItem>()
        var idCounter = 0
        numbers.forEach { number ->
            generatedCards.add(CardItem(id = idCounter++, number = number))
            generatedCards.add(CardItem(id = idCounter++, number = number))
        }

        // Kartları karıştır
        generatedCards.shuffle()
        return generatedCards
    }

    private fun startGame() {
        _cards.clear()
        _cards.addAll(generateCards())
        _score.value = 0
        _timeLeft.value = 60
        _selectedCards.clear()
        _gameEnded.value = false
        _gameWon.value = false
    }

    private fun startTimer() {
        viewModelScope.launch {
            while (_timeLeft.value > 0 && !_gameEnded.value) {
                delay(1000) // 1 saniye bekle
                _timeLeft.value--
            }
            if (_timeLeft.value == 0 && !_gameWon.value) {
                _gameEnded.value = true
                _gameWon.value = false // Süre bitti, oyun kaybedildi
            }
        }
    }

    fun onCardClicked(clickedCard: CardItem) {
        if (clickedCard.isFaceUp || clickedCard.isMatched || _selectedCards.size >= 2 || _gameEnded.value) {
            return // Zaten açık, eşleşmiş, iki kart açık veya oyun bitmişse işlem yapma
        }

        val index = _cards.indexOfFirst { it.id == clickedCard.id }
        if (index != -1) {
            // Kartı aç (animasyon burada yapılmaz, Composable'da yapılır)
            _cards[index] = _cards[index].copy(isFaceUp = true)
            _selectedCards.add(_cards[index])

            if (_selectedCards.size == 2) {
                // İki kart açık olduğunda eşleşme kontrolü yap
                viewModelScope.launch {
                    delay(800) // Kısa bir bekleme (kartların görünmesi için)
                    checkMatch()
                }
            }
        }
    }

    private fun checkMatch() {
        if (_selectedCards.size == 2) {
            val card1 = _selectedCards[0]
            val card2 = _selectedCards[1]

            if (card1.number == card2.number) {
                // Eşleşti: Kartları eşleşmiş olarak işaretle ve yeşil renge dönüştür
                val index1 = _cards.indexOfFirst { it.id == card1.id }
                val index2 = _cards.indexOfFirst { it.id == card2.id }
                if (index1 != -1 && index2 != -1) {
                    _cards[index1] = _cards[index1].copy(isMatched = true)
                    _cards[index2] = _cards[index2].copy(isMatched = true)
                }
                _score.value += 10 // Puan ekle
            } else {
                // Eşleşmedi: Kartları kısa bir süre sonra kapat
                val index1 = _cards.indexOfFirst { it.id == card1.id }
                val index2 = _cards.indexOfFirst { it.id == card2.id }
                if (index1 != -1 && index2 != -1) {
                    _cards[index1] = _cards[index1].copy(isFaceUp = false)
                    _cards[index2] = _cards[index2].copy(isFaceUp = false)
                }
            }
            _selectedCards.clear() // Açık kartları temizle
            checkGameEnd()
        }
    }

    private fun checkGameEnd() {
        if (_cards.all { it.isMatched }) {
            _gameEnded.value = true
            _gameWon.value = true
        }
    }


    fun resetGame() {
        startGame()
        startTimer()
    }

    // ViewModelFactory sınıfı, ViewModel'i parametre ile oluşturabilmek için
    class Factory(private val difficulty: String) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MemoryGameViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MemoryGameViewModel(difficulty) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}