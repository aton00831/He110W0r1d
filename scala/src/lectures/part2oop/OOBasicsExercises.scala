package lectures.part2oop


/*
  Novel and a Writer

  Writer: first name, surename, year
    - method fullname

  Novel: name, year of release, author
    - method: authorAge
    - method: isWritternBy(Author)
    - copy (new year of realease) = new instance of Novel
 */

/* Counter class
  - recives an int value
  - method current count
  - method to inrement/decrement => new Counter
  - overload inc/ dec to receive an amount
 */

object OOBasicsExercises extends App {
  val author = new Writer("Charles", "Dickens", 1812)
  val imposter = new Writer("Charles", "Dickens", 1812)
  val tony = new Writer("Tony", "Wang", 1992)
  val novel = new Novel("Great Expectations", 1861, author)

  println(novel.authorAge())
  println(novel.isWrittenBy(author))
  println(novel.isWrittenBy(imposter))
  println(novel.isWrittenBy(tony))

  val counter = new Counter
  counter.inc.print
  counter.inc.inc.inc.print
  counter.inc(10).print


  class Writer(firstName:String, surename:String, val year:Integer) {
    def fullName: String = s"$firstName $surename"
  }

  class Novel(name: String, year: Int, author: Writer) {
    def authorAge(): Int = year - author.year
    def isWrittenBy(author: Writer) = author == this.author
    def copy(newYear: Int): Novel = new Novel(name, newYear, author)
  }

  class Counter(val count: Int = 0) {

    def inc = new Counter(count + 1) // immutability
    def dec = new Counter(count - 1)

    def inc(n: Int): Counter = {
      if (n <= 0) this
      else inc.inc(n-1) // this.inc.inc(n-1)
    }

    def dec(n: Int): Counter = {
      if (n <= 0) this
      else dec.dec(n-1)
    }

    def print = println(count)

  }

  /* Takeaways
    - Defining classes `class Person(name: String)`
    - Instantiating `val bob = new Person("bob")`
    - Parameters vs fields `class Person(val name: String)`
    - Defining method `def greet(): String = {...}`
    - Calling method `val bobSayHi = bob.greet`
    (Syntax allowed for parameter-less method)
    - The keyword `this`
   */

}
