package edu.uw.basickotlin

class Library {
    // This is just here as a placeholder, to make sure tests run and pass
    // before you add any code
    fun someLibraryMethod(): Boolean {
        return true
    }
}

// write a "whenFn" that takes an arg of type "Any" and returns a String
fun whenFn(arg : Any): String {
    return when (arg) {
        "Hello" -> "world"
        is String -> "Say what?"
        0 -> "zero"
        1 -> "one"
        in 2..10 -> "low number"
        is Int -> "a number"
        else -> "I don't understand"
    }
}

// write an "add" function that takes two Ints, returns an Int, and adds the values
fun add(a: Int, b: Int): Int {
    return a + b
}
// write a "sub" function that takes two Ints, returns an Int, and subtracts the values
fun sub(a: Int, b: Int): Int {
    return a - b
}

// write a "mathOp" function that takes two Ints and a function (that takes two Ints and returns an Int), returns an Int, and applies the passed-in-function to the arguments
fun mathOp(a: Int, b: Int, func: (Int, Int) -> Int): Int {
    return func(a,b)
}
// write a class "Person" with first name, last name and age
class Person (val firstName: String, val lastName: String, val age: Int){
  val debugString: String
        get() = "[Person firstName:$firstName lastName:$lastName age:$age]"
}
// write a class "Money" with amount and currency, and define a convert() method and the "+" operator
class Money (val amount: Int, val currency: String) {
    init {
    if (amount < 0) {
        throw IllegalArgumentException("Invalid amount")
    }
    if(!listOf("USD","EUR","CAN","GBP").contains(currency)) {
          throw IllegalArgumentException("Invalid currency")
    }
    }
    fun convert(type: String): Money {
        val toUsd: Double = when(currency) {
            "EUR" ->  2.0 / 3.0 // eur to usd
            "CAN" -> 4.0 / 5.0 // can to usd
            "GBP" ->  2.0 / 1.0 // gbp to usd
            else -> 1.0
        }
        val toFinal: Double = when(type) {
            "EUR" -> 3.0 / 2.0 // usd to eur
            "CAN" -> 5.0 / 4.0 // usd to can
            "GBP" ->  1.0 / 2.0 // usd to gbp
            else -> 1.0
        }
        val rate = toUsd * toFinal
        val converted = (amount * rate).toInt()

        return Money(converted, type)
    }
    operator fun plus(other: Money): Money {
        if(this.currency != other.currency) {
            return Money(this.amount + other.convert(this.currency).amount, this.currency)
        } else {
            return Money(this.amount + other.amount, this.currency)
        }
    }
}