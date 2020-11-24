package lectures.part3fp

object MapFlatmapFilterFor extends App {

  val list = List(1,2,3)
  println(list)

  println(list.head)
  println(list.tail)

  // https://www.scala-lang.org/api/current/scala/collection/immutable/List.html

  // map
  println(list.map(_ + 1))
  println(list.map(_ + "is a number"))

  // filter
  println(list.filter(_ %2 == 0))

  // flatMap
  val toPair =  (x: Int) => List(x, x+1)
  println(list.flatMap(toPair))

  // Exercise, print all combination between two list
  val numbers = List(1,2,3,4,5,6,7,8,9,10,11,12,13)
  val chars = List('A', 'B', 'C', 'D')

  // TRAD: USE TWO LOOP
  // In FP:
  val combineChar = (x: Int) => chars.map(_ + x.toString) // "" + x + _
  println(numbers.flatMap(combineChar))
  // numbers.flatMap(n => chars.map(c => "" + c + n)

  // one more list:
  val colors = List("black", "white")
  println(numbers.flatMap(n => chars.flatMap(c => colors.map( color => "" + c + n + "-" + color))))
  // ^ hard to read

  // for-each
  list.foreach(println)

  // for-comprehensions (solve readability)
  val forCombinations = for {
    n <- numbers if  n % 2 == 0 // guard, like add a filter  numbers.filter(..).flatMap
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map ( x =>
    x * 2
  )

  /* Exercise
    1. MyList supports for comprehensions?
      for comprehensions are rewritten by the compiler into
      chains of maps, flat map and filter

      map(f: A => B) => MyList[B]
      filter(p: A => Boolean) => MyList[A]
      fiatMap(f: A => MyList[B]) => MyList[B]

    2. A small collection of at more ONE element - Maybe[+T]
      - map, flatMap, filter

      useful in FP to denote optional value
      actual option type in scala later

      denote the possible absense of value
  */
}