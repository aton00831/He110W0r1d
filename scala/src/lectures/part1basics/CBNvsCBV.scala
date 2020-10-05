package lectures.part1basics

object CBBvsCBV extends App {

  def calledByValue(x: Long): Unit = {
    println("by value: " + x) // by value: 292800379619024
    println("by value: " + x) // by value: 292800379619024
  }

  def calledByName(x: => Long): Unit = {
    println("by name: " + x) // by name: 292800423632974
    println("by name: " + x) // by name: 292800423660184
  }

  calledByValue(System.nanoTime())
  // calledByValue(292800379619024) what computer does

  calledByName(System.nanoTime())
  // function pass literally ! WITH =>
  // -> println("by name: " + System.nanoTime())
  // -> println("by name: " + System.nanoTime())

  // extremely useful in lazy streams and file (try)

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  // printFirst(infinite(), 34)
  printFirst(34, infinite())


  /* Takeaways
  - Call by value: ge
  value is computed before call
  same value used everywhere

  - Call by name: : =>
  expression is passed literally
  expression is evaluated at every use within
   */


  /* note

  ---
  Q: Why `called-by-name`?
  their name is replaced with the expression they're bound to
  for it to be evaluated on the spot, hence the "by-name" term.

  Q: Is called by named just pass first-class function as parameter ?
  1 - The concept of first-class functions doesn't necessarily solve
   the deferred evaluation problem out of the box.

   Haskell does it, but it's a design choice.
   I think Scala is more powerful because it gives you the choice to defer evaluation or not.

  2 - I didn't quite get a clear sense from your comment,
  but to be extra clear on a slight difference:
  the by-name parameters, besides deferring evaluation, are evaluated every time
  (as the time example shows) whereas lazy values are reused after evaluating.

  As you know from the advanced course examples, we see slight behavioural
  differences between lazy/ call-by-name/ call-by-need.


   */


}
