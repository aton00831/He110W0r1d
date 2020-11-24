package lectures.part3fp

import scala.util.Random

object Sequences extends App {
  /* Seq
```
trait Seq[+A] {
  def head: A
  def tail: Seq[A]
}
```
A (very) general interface for data structure that
- have a well defined order
- can be indexed

Supports various operations
- apply, iterator, length, reverse for indexing and iterating
- concatenation, appending, prepending
- a lot of others: grouping, sorting, zipping, searching, slicing
   */
  val aSequence = Seq(1,2,3,4)
  println(aSequence) // List(1,2,3,4)
  // Seq companion object actually has an apply factory method
  // that can construct subclasses of sequence
  println(aSequence.reverse) // 4, 3, 2, 1
  println(aSequence.apply(2)) // 3
  println(aSequence ++ Seq(5,7, 6))
  println(aSequence.sorted)
  // The sorted method works if the type is by default order ?

  // Ranges
  val aRange: Seq[Int] = 1 until 10 // right exclusive
  // val aRange: Seq[Int] = 1 to 10 // right  inclusive
  aRange.foreach(println)
  // a replace of loop (instead of using recursive function
  (1 to 10).foreach(x => println("Hello " + (x*x).toString))
  println()

  /* List signature:
```
sealed abstract class List[+A]
case object Nil extends List[Nothing]
case class ::[A](val hd: A, val tl: List[A]) extends List[A]
```
- Nil is our Empty
- :: is our Con

A LinearSeq immutable linked list
- head, tail, isEmpty methods are fast: O(1)
- most operations are O(n): length, reverse

Sealed and has two subtypes:
- object Nil (empty)
- class ::
   */

  // lists
  val aList = List(1,2,3)
  val prepended0 = 42 :: aList // syntactic sugar for  ::.apply(42, List)
  println(prepended0) // List(42, 1, 2, 3)
  val prepended = 42 +: aList
  println(prepended) // List(42, 1, 2, 3)
  val append = aList :+ 89
  println(append) // List(1, 2, 3, 89)
  // remember, colon always on the side of list

  val apple5 = List.fill(5)("apple") // fill is a curry function take number and then value
  println(apple5)

  println(aList.mkString("-")) // Python "-".join(aList)

/* Array signature:
```
final class Array[T]
  extends java.io.Serializable
  with java.lang.Cloneable
```
The equivalent of simple Java arrays
- can be manually constructed with `predefined lengths`
- can be mutated (updated in place)
- are interoperable with Java's T[] arrays // ? T[] means Java native arrays
- indexing is fast

WHERE's the Seq ?!*
 */
  val numbers = Array(1,2,3,4)
  val threeElements = Array.ofDim[Int](3)
  println(threeElements) // [I@5315b42e
  threeElements.foreach(println) // default value: 0, false, null ..

  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  // update method is special in Scala
  // It's quite rarely used in practice and
  // only used for mutable collections
  // syntax sugar that is very similar to apply
  println(numbers.mkString(" "))

  // arrays and seq
  // remind: numbers(NOT Keyword) is an array which is a direct mapping
  // over Java's native arrays so it has nothing to do with sequences
  // Despite this, the conversion can be applied
  val numberSeq: Seq[Int] = numbers // implicit conversion
  println(numberSeq) // ArraySeq(1, 2, 0, 4)
  // TODO: Course is WrappedArray([1,2,3,4])

  /* Vector
```
final class Vector[+A]
```
The default implementation for immutable sequences
- effectively constant indexed read and write: O(log_32(n))
- fast element addition: append/ prepend
- implemented as a fixed-branched trie (branch factor 32)
- good performance for large sizes

 */
  val noElements = Vector.empty
  val vnumbers = noElements :+ 1 :+ 2 :+ 3 // Vector (1,2,3)
  println(vnumbers)
  val vmodified = vnumbers updated (0, 7) // Vector(7,2,3)
  println(vmodified)

  val vector: Vector[Int] = Vector(1,2,3)
  println(vector)

  // vectors vs lists (Benchmark code
  val maxRuns = 1000
  val maxCapacity = 1000000
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      // operation
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }

    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tail
  // updating an element in the middle takes long
  println(getWriteTime(numbersList)) // 4708395.82

  // depth of the tree is small
  // needs to replace an entire 32-element chunk
  println(getWriteTime(numbersVector)) // 3879.469

  // so, vector is the default implementation of seq


}
