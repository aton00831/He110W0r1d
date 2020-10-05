package lectures.part1basics

object StringOp extends App {
  val str: String = "Hello, I am learning Scala!"

  // All Java function available in Scala!
  println(str.charAt(2)) // 'l'

  println(str.substring(7, 11)) // (inclusive, exclusive) "I am"

  println(str.split(' ').toList) // List(Hello,, I, am, learning, Scala!)

  println(str.startsWith("Hello")) // True

  println(str.replace(' ', '-')) // Hello,-I-am-learning-Scala!

  println(str.toLowerCase()) // hello, i am learning scala!

  println(str.length) // 27
  println(str.length()) // 27

  // Scala
  val aNumberString = "45"
  val aNumber = aNumberString.toInt


  println('a' +: aNumberString :+ 'z') // prepending, appending
  // 1. prepend a to aNumberString
  // 2. append z to aNumberString
  /* https://alvinalexander.com/scala/how-to-append-prepend-items-vector-seq-in-scala/
  to append one item, use :+   e.g. oldSeq :+ e
  to append multiple items, use :++ e.g. oldSeq :++ newSeq
  to prepend one item, use +: e.g. e +: oldSeq
  to prepend multiple items, use ++: e.g. newSeq ++: oldSeq
   */
  println(str.reverse) // !alacS gninrael ma I ,olleH

  println(str.take(2)) // He

  // Scala-specific: String interpolator
  // 1. S-interpolators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I am $age years old"
  val anotherFreeting = s"Hello, my name is $name and I will be trurning ${age + 1} years old"

  // 2. F-interpolators (printf format)
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minutes."
  // %2.2f: 2 charaters total, minimum, 2 decimal precision

  val x = 1.1f
  // val str = f"$x%3d" // Type error

  // 3. raw-interpolators
  println(raw"This is a \n newline") // This is a \n newline
  val escaped = "This is a \n newline"
  println(raw"$escaped")
//  This is a
//  newline


}
