package com.rockthejvm

object FunctionalProgramming extends App {

  // Scala is OO
  class Person(name: String) {
    def apply(age: Int) = println(s"I have aged $age years")
  }

  private val bob = new Person("Bob")
  bob.apply(43)
  bob(43) // Invoking bob as a function === bob.apply(43)

  /*
    Scala runs on the JVM, JVM fundamentally built for Java
    Functional Programming:
    - compose functions
    - pass functions as args
    - return functions as results

    Conclusion: FunctionX = Function1, Function2, ..., Function22
  */

  private val simpleIncrementer = new Function1[Int, Int] {
    override def apply(arg: Int): Int = arg + 1
  }

  simpleIncrementer.apply(23) // 24
  simpleIncrementer(23) // 24 => simpleIncrementer.apply(23)
  // defined a function and act like a function

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTIONX TYPES

  // function with 2 arguments and a return type
  private val stringConcatenator = new Function2[String, String, String] {
    override def apply(v1: String, v2: String): String = v1 + v2
  }

  stringConcatenator("I love", " Scala") // I love Scala

  // syntax sugar => alternative for boiler plate codes
  private var doubler: Int => Int = (x: Int) => x * 2
  doubler(4) // 8

  /**
    equivalent to much longer:

    var doubler: Function1[Int, Int] = new Function1[Int, Int] {
      override def apply(x: Int): Int = 2 * x
   }
   */

  // higher-order functions: take functions as args/return functions as results or both
  val aMappedList = List(1,2,3).map(x => x + 1) // HOF
  val aFlatMappedList = List(1,2,3).flatMap{ x =>
    List(x, 2 * x)
  } // alternative syntax, same as .flatMap(x => List(x, 2 * x))
  val aFilteredList = List(1,2,3,4,5).filter(_ <= 3) // equivalent to x => x <= 3

  // all pairs between the number 1,2,3 and the letters 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap(number => List('a', 'b', 'c').map(letter => s"$number-$letter"))

  // for comprehensions
  val alternativePairs = for {
    number <- List(1,2,3)
    letter <- List('a', 'b', 'c')
  } yield s"$number-$letter"
  // equivalent to the map/flatMap above

  /*
  * Collections
  */

  // lists
  val aList = List(1,2,3,4,5)
  val firstElement = aList.head
  val rest = aList.tail
  val aPrependedList = 0 :: aList // List(0,1,2,3,4,5)
  val anExtendedList = 0 +: aList :+ 6 // List(0,1,2,3,4,5,6)

  // sequences
  private val aSequence: Seq[Int] = Seq(1,2,3) // Seq.apply(1,2,3)
  val accessedElement = aSequence(1) // the element at index 1: 2

  // vectors: fast seq implementation
  val aVector = Vector(1,2,3,4,5)

  // sets = no duplicates, unordered
  private val aSet = Set(1,2,3,4,1,2,3) // Set(1,2,3)
  val setHas5 = aSet.contains(5) // false
  val anAddedSet = aSet + 5 //  Set(1,2,3,4,5)
  val aRemovedSet = aSet - 3 //  Set(1,2,4)

  // ranges
  private val aRange = 1 to 1000
  val twoByTwo = aRange.map(x => 2 * x).toList // List(2,4,6,8,....,2000)

  // tuples = group of values under the same value
  val aTuple = ("Bon Jovi", "Rock", 1982)

  // maps
  val aPhonebook: Map[String, Int] = Map(
    ("AJ", 1245125),
    "Jane" -> 129481025 // equivalent to ("Jane", 129481025)
  )
}
