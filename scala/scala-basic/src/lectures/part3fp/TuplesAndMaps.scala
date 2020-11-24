package lectures.part3fp

object TuplesAndMaps extends App {

  // tuples = finite ordered "lists"
  val aTuple: Tuple2[Int, String] = new Tuple2(2, "hello Scala")
  // new is optional because the companion objects
  // Tuples can group at most 22 elements of different types
  // why 22? because they're used in conjuction with function type

  println(aTuple._1)
  println(aTuple._2)

  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello Scala", 2)
  // TODO: WHY Tuple

  // Maps: associate things to another thing
  // Keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(
    ("Jim", 555),
    "Daniel" -> 789 // syntactic sugar
  ).withDefaultValue(-1) // to prevent exception when access empty key
  // a -> b is sugar for tuple (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains{"Jim"})
  println(phonebook("Jim"))

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing
  println(newPhonebook)

  // functionals on maps
  // map, flatMap, filter
  println(phonebook.map(
    pair => pair._1.toLowerCase -> pair._2
  ) )
  
  // filterKeys (deprecate)
  println(phonebook.filterKeys(
    x => x.startsWith("J")
  )) // TODO: MapView(<not computed>)

  // mapValues (deprecate)
  println(phonebook.mapValues(
    number => "0245-" + number
  )) // TODO: MapView(<not computed>)

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)

  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0)))
  // HashMap(J -> List(James, Jim), A -> List(Angela), M -> List(Mary), B -> List(Bob), D -> List(Daniel))
  // very used in spark, data scientist

  /* Exercise
    1. What would happens if I had two original entries "Jim" -> 555 and "JIM" -> 9000

      !!! careful with mapping keys

    2. Overly simplified social network based on maps
      Person = String
      - add a person to the network
      - remote
      - friend (mutual)
      - unfriend

      - number of friends of a person
      - person with most friends
      - how many people have NO friends
      - if there is a social connection between two people (direct or not)

   */
  val phonebookExercise = Map(
    ("Jim", 555),
    ("JIM", 9000)
  ).withDefaultValue(-1)
  println(phonebookExercise)
  println(phonebookExercise.map(
    pair => pair._1.toLowerCase -> pair._2
  ) ) // losing Jim ...

  def add(network: Map[String, Set[String]], person: String)
    : Map[String, Set[String]] =
    network + (person -> Set())

  // can simply guard against these with tries, but here we practice map
  def friend(network: Map[String, Set[String]], a: String, b: String)
  : Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA + b)) + (b -> (friendsB + a))
  }

  def unfriend(network: Map[String, Set[String]], a: String, b: String)
  : Map[String, Set[String]] = {
    val friendsA = network(a)
    val friendsB = network(b)

    network + (a -> (friendsA - b)) + (b -> (friendsB - a))
  }

  def remove(network: Map[String, Set[String]], person: String)
  : Map[String, Set[String]] = {
    // prevent dangling friendship
    def removeAux(friends: Set[String]q)
  }


}







