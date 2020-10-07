package exercises

import scala.annotation.tailrec


/* Exercise2 Generic MyList with following functionality
1. Generic trait MyPredicate[-T] with a little method test(T) => Boolean
2. Generic trait MyTransformer[-A, B] with a method transform(A) => B
3. MyList:
  - map(transformer) => MyList
  - filter(predicate) => MyList
  - flatMap(transformer from A to MyList[B]) => MyList[B]

class EvenPredicate extends MyPredicate[Int]
class StringToIntTransformer extends MyTransformer[String, Int]

[1,2,3].map(n * 2) = [2,4,6] (Pseudo Code)
[1,2,3,4].filter(n % 2) = [2,4]
[1,2,3].flatMap(n => n, n+1]) => [1,2,2,3,3,4]
 */



object MyListAdvanced extends App {

  abstract class MyList[+A] {
    def head: A
    def tail: MyList[A]
    def isEmpty: Boolean
    def add[B >: A <: AnyRef](element: B): MyList[B]
    def printElements: String
    override def toString: String = "[" + printElements + "]"
    /*
    def map[B](transformer: MyTransformer[A, B]): MyList[B]
    def flatMap[B](transformer: MyTransformer[A,MyList[B]]): MyList[B]
    def filter(predicate: MyPredicate[A]): MyList[A]
    */

    // Higher-Order Functions
    // either receive functions as parameters
    // or return other functions as a result
    // critical concept to FP.
    def map[B](transformer: A => B): MyList[B]
    def flatMap[B](transformer: A => MyList[B]): MyList[B]
    def filter(predicate: A => Boolean): MyList[A]

    def ++[B >: A](list: MyList[B]): MyList[B]
  }

  case object Empty extends MyList[Nothing] {
    def head: Nothing = throw new NoSuchElementException
    def tail: MyList[Nothing] = throw new NoSuchElementException
    def isEmpty: Boolean = true
    def add[B >: Nothing <: AnyRef](element: B): MyList[B] = new Cons(element, this)
    def printElements: String = ""

    def map[B](transformer: Nothing => B): MyList[B] = Empty
    def flatMap[B](transformer: Nothing => MyList[B]): MyList[B] = Empty
    def filter(predicate: Nothing => Boolean): MyList[Nothing] = Empty

    def ++[B >: Nothing](list: MyList[B]): MyList[B] = list
  }

  case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
    def head: A = h
    def tail: MyList[A] = t
    def isEmpty: Boolean = false
    def add[B >: A <: AnyRef](element: B): MyList[B] = new Cons(element, this)
    def printElements: String =
      if (t.isEmpty) "" + h
      else h + " " + t.printElements

    def filter(predicate: A=>Boolean): MyList[A] =
      if (predicate(h)) new Cons(h, t.filter(predicate))
      else t.filter(predicate)

    def map[B](transformer: A => B): MyList[B] =
      new Cons(transformer(h), t.map(transformer))

    /*
      [1, 2] ++ [3, 4, 5]
      = new Cons(1, [2] ++ [3, 4, 5])
      = new Cons(1, new Cons(2, Empty ++ [3,4,5]))
      = new Cons(1, new Cons(2, [3,4,5]))
     */
    // def ++ [B >: A <: AnyRef](list: MyList[B]): MyList[B] =
    //               ^^^^^^^^^^ Cause Error ?
    def ++ [B >: A ](list: MyList[B]): MyList[B] =
      new Cons(h, t ++ list)

    /*
      [1,2].flatMap(n => [n, n+1])
      = [1,2] ++ [2].flatMa(n => [n, n+1])
      = [1,2] ++ [2,3] ++ Empty
     */
    def flatMap[B](transformer: A => MyList[B]): MyList[B] =
      transformer(h) ++ t.flatMap(transformer)

  }

  /* Turn into function type
  trait MyPredicate[-T] { // T => Boolean
    def test(elem: T): Boolean
  }

  // if no -A, map[B](transformer, .. shows error
  // Covariant type A occurs in invariant position in type MyTransformer[A, B] of value transformer
  trait MyTransformer[-A, B] { // A => B
    def transform(elem:A) : B
  }
  */

    // TEST ---
  class Food
  class Bagel extends Food {
    override def toString: String = "I am Bagel"
  }
  class Donut extends Food {
    override def toString: String = "I am Donut"
  }

  val bagel = new Bagel
  val donut = new Donut

  val listOfFoods: MyList[Food] =
    new Cons[Food](bagel, new Cons[Food](donut, Empty))

  println(listOfFoods)


  val listOfIntegers: MyList[Int] =
    new Cons(1, new Cons(2, new Cons(3, Empty)))

  val cloneListOfIntegers: MyList[Int] =
    new Cons(1, new Cons(2, new Cons(3, Empty)))

  val anotherListOfIntegers: MyList[Int] =
    new Cons(4, new Cons(5, Empty))

  val listOfStrings: MyList[String] =
    new Cons("Today", new Cons("is", new Cons("sunny", Empty)))


  println(listOfIntegers)
  println(listOfStrings)
  /*
  println(listOfIntegers.map(new Function1[Int, Int] {
    override def apply(elem: Int): Int = elem * 2
  }))
  */
  // println(listOfIntegers.map(elem => elem * 2))
  println(listOfIntegers.map(_ * 2))

  /*
  println(listOfIntegers.filter(new Function1[Int, Boolean]{
    override def apply(elem: Int): Boolean = elem % 2 == 1
  }))
  */

  // println(listOfIntegers.filter(elem => elem % 2 == 1))
  println(listOfIntegers.filter(_ % 2 == 1))

  // println(listOfIntegers ++ anotherListOfIntegers)

  /*
  println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
    override def apply(elem: Int): MyList[Int] =
      new Cons(elem, new Cons(elem+1, Empty))
  }))
  */
  println(listOfIntegers.flatMap(elem => new Cons(elem, new Cons(elem+1, Empty))))
  // underscore not work here

  // After using `case`
  println(cloneListOfIntegers == listOfIntegers)
}