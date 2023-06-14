import java.util.Scanner

class ParkingLot( capacity: Int) {
    val capacity = capacity
    private val spots = Array<Car?>(capacity) { null }
    init {
        println("Created a parking lot with $capacity spots.")
    }

    fun park(registrationNumber: String, color: String) {
        val spotNumber = findNextAvailableSpot()
        if (spotNumber == -1) {
            println("Sorry, the parking lot is full.")
        } else {
            val car = Car(registrationNumber, color)
            spots[spotNumber] = car
            println("$color car parked in spot ${spotNumber + 1}.")
        }
    }
    /*fun status() {
        if (spots.isEmpty()) println("Parking lot is empty.")
        for (i in spots.indices) {
            val car: Car? = spots[i]
            if (car != null) {
                println("Spot ${i + 1}: ${car.registrationNumber} ${car.color}")
            } else {
                continue
            }
        }
    }*/
    fun status() {
        var isEmpty = true

        for ((index, car) in spots.withIndex()) {
            if (car != null) {
                isEmpty = false
                println("${index + 1} ${car.registrationNumber} ${car.color}")
            }
        }

        if (isEmpty) {
            println("Parking lot is empty.")
        }
    }

    fun leave(spotNumber: Int) {
        if (spotNumber < 1 || spotNumber > capacity) {
            println("Invalid spot number.")
        } else {
            val index = spotNumber - 1
            val car = spots[index]
            if (car == null) {
                println("Spot $spotNumber is free. ")
            }
            else {
                spots[index] = null
                println("Spot $spotNumber is free.")
            }
        }
    }
    fun regByColor(color: String) {
        val matchingCars = spots.filter { it?.color?.equals(color, ignoreCase = true) == true }
        if (matchingCars.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            val registrationNumbers = matchingCars.mapNotNull { it?.registrationNumber }
            val registrationNumbersString = registrationNumbers.joinToString(", ")
            println(registrationNumbersString)
        }
    }

    fun spotByColor(color: String) {
        val matchingSpots = spots.withIndex()
            .filter { it.value?.color?.equals(color, ignoreCase = true) == true }
            .map { it.index + 1 }
        if (matchingSpots.isEmpty()) {
            println("No cars with color $color were found.")
        } else {
            val spotsString = matchingSpots.joinToString(", ")
            println(spotsString)
        }
    }

    fun spotByReg(registrationNumber: String) {
        val matchingSpot = spots.withIndex()
            .firstOrNull { it.value?.registrationNumber == registrationNumber }
        if (matchingSpot == null) {
            println("No cars with registration number $registrationNumber were found.")
        } else {
            val spotNumber = matchingSpot.index + 1
            println(spotNumber)
        }
    }
    private fun findNextAvailableSpot(): Int {
        for (i in 0..spots.size-1) {
            if (spots[i] == null) {
                return i
            }
        }
        return -1
    }
}

data class Car(val registrationNumber: String, val color: String)

fun main() {


    // parkingLot.park("registrationNumber", "White ")
    //val scanner = Scanner(System.`in`)
    var parkingLot: ParkingLot?= null

    while (true) {
        //print("Enter command (park, leave, exit): ")
        val input = readln()
        val parts = input.split(" ")



        when (parts[0]) {

            "create" -> {
                var capacity = parts[1].toInt()
                parkingLot = ParkingLot(capacity)
               // println("A new parking lot with capacity $capacity has been created. Previous parking state reset.")
            }
            "status" -> {
                if (parkingLot == null) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    parkingLot.status()
                }
            }


            "park" -> {
                val registrationNumber = parts[1]
                val color = parts[2]
                if (parkingLot == null) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    parkingLot.park(registrationNumber, color)
                }
            }

            "leave" -> {
                val spotNumber = parts[1].toIntOrNull()
                if (parkingLot == null) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    if (spotNumber != null) {
                        parkingLot.leave(spotNumber)
                    } else {
                        println("Invalid spot number.")
                    }
                }
            }
            "reg_by_color" -> {
                val color = parts[1]
                if (parkingLot == null) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    parkingLot.regByColor(color)
                }
            }

            "spot_by_color" -> {
                val color = parts[1]
                if (parkingLot == null) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    parkingLot.spotByColor(color)
                }
            }

            "spot_by_reg" -> {
                val registrationNumber = parts[1]
                if (parkingLot == null) {
                    println("Sorry, a parking lot has not been created.")
                } else {
                    parkingLot.spotByReg(registrationNumber)
                }
            }

            "exit" -> {
                break
            }

            else -> {
                println("Invalid command.")
            }
        }

    }
    // scanner.close()
}
