import kotlin.random.Random

fun makeDecision(): String {
    val x = Random.nextInt(1,4)
    when (x){
        1 -> return ("Rock")
        2 -> return ("Paper")
        else -> return ("Scissors")
    }

}
