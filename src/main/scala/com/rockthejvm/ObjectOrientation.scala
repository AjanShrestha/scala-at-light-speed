package com.rockthejvm

object ObjectOrientation extends App {
  // java equivalent: public static void main(String[] args

  // class and instance
  class Animal {
    // define fields
    val age: Int = 0
    // define methods
    def eat() = println("I'm eating")
  }
  val anAnimal = new Animal

  // inheritance
  class Dog(val name: String) extends Animal // constructor definition
  val aDog = new Dog("April")

  // constructor arguments are NOT fields: need to put a val before the constructor argument
  aDog.name

  // subtype polymorphism
  private val aDeclaredAnimal: Animal = new Dog("Hachi")
  aDeclaredAnimal.eat() // the most derived method wll be called at runtime

  // abstract class
  abstract class WalkingAnimal {
    val hasLegs = true
    def walk(): Unit
  }

  // "interface" = ultimate abstract class
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  private trait Philosopher {
    def ?!(thought: String): Unit // valid method name
  }

  // single-class inheritance, multi-trait "mixing"
  private class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("I'm eating you, animal")
    override def eat(): Unit = super.eat()
    override def ?!(thought: String): Unit = println(s"I was thinking $thought")
  }

  // Scala method notation and method naming
  private val aCroc = new Crocodile
  aCroc.eat(aDog)
  aCroc eat aDog // infix notation = object method argument, only available for method with ONE argument
  aCroc ?! "What if we could fly?"

  // operators in scala are actually methods
  val basicMath = 1 + 2
  val anotherBasicMath = 1.+(2) // equivalent

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur so I can pretty much eat anything")
  }

  /*
    What you tell the compiler:

    class Carnivore_Anonymous_1612 extends Carnivore {
      override def eat(animal: Animal): Unit = println("I am a dinosaur so I can pretty much eat anything")
    }

    val dinosaur = new Carnivore_Anonymous_1612
  */

  // singleton object
  private object MySingleton { // the only instance of the MySingleton type
    val mySpecialValue = 1265
    def mySpecialMethod(): Int = 125
    // presence of an apply method in a class, allows instances of that class to invoked like functions
    // important for functional programming
    def apply(x: Int): Int = x + 1
  }

  MySingleton.mySpecialMethod()
  MySingleton.apply(67)
  MySingleton(67) // equivalent to MySingleton.apply(67)

  object Animal { // companions - companion object
    // object as both class and object are created in same file with same name
    // companions can access each other's private fields/methods
    // singleton Animal and instances of Animal are different things
    val canLiveIndefinitely = false
  }

  val animalsCanLiveForever = Animal.canLiveIndefinitely // access just like static "fields"

  /*
    case classes = lightweight data structures with some boilerplate
    Compiler generates
    - sensible equals and hash code
    - sensible quick serialization
    - companion with apply
    - pattern matching
  */
  case class Person(name: String, age: Int)

  // may be constructed without new
  val bob = Person("Bob", 54) // Person.apply("Bob", 54)

  // exceptions
  try {
    // code that can throw
    val x: String = null
    x.length
  } catch { // catch(Exception e) {...}
    case e: Exception => "some faulty error message"
  } finally {
    // execute code no matter what
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  private val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head // int
  val rest = aList.tail
  private val aStringList: List[String] = List("hello", "Scala")
  val firstString = aStringList.head // string

  // Scala points
  // #1: In Scala, we usually operate with IMMUTABLE value/objects
  // Any modification to an object must return another object
  /*
    Benefits:
    1. works miracles in multithreaded/distributed env
    2. helps making sense of the code ("reasoning about")
  */
  val reversedList = aList.reverse //  returns a NEW list

  // #2: Scala is closest to the OO ideal
  // all the code - values and objects are inside an instance of some type (i.e. part of an object/class)
}
