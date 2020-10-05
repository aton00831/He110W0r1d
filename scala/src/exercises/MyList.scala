package exercises
import scala.annotation.tailrec

abstract class MyList {
  /* Singly-linked-list with holding integer
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */
  val head: Int
  val tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList // immutable list
  // def toString: String
}

object Empty extends MyList {
  // val head: Int = ??? // return nothing, throw not implemented error
  val head: Int = throw new NoSuchElementException // Throw expression are expressions which return nothing
  val tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element, Empty) // immutable list
}

class Cons(h: Int, t: MyList) extends MyList {
  val head: Int = h // return nothing, throw not implemented error
  val tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element, this) // immutable list
}

object MyListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)

}



/*
Questions:
1. there is more than one way to do it
2. most developers will have coded it using a Node inner class
3. using a Node class is Not A Good Thing
   as far as idiomatic scala or functional programming are concerned

Responses
1.
IN FUNCTIONAL PROGRAMMING,
mutations (e.g. reassignment to vars or "setters") are Not a Good Thing.
We on;y work with values and expressions.focus on `immutable data structure`

classic node implementation is not "bad",
but more like "dangerous", because it invites you to the thinking pattern of mutations.
MyList `is` a list (Node is named MyList), as you can access any element from that node

Q: linked list usually add to the end of the list
A:
In standard libraries, linked lists are usually doubly-linked,
which makes them suitable for both stack/queue.
If they're singly-linked, then adding an element to the end is O(n), whereas adding to the front is O(1).
We tend to add to the beginning.

Q: Why named cons? Consumer?
A:
Linked lists have been around in this head/tail style
in functional programming for about
as long as the Node version in imperative programming.
Cons is a historical shorthand for "constructor".
*/

/**
  * Q:
  * why MutableMyList is not good idiomatic scala code?
  *
  * A:
  * The one thing I will point out is that  "this list is mutable",
  * which is something we generally try to avoid.
  *
  * In functional programming, mutable data structures and variables
  * make the code error-prone and extremely hard to read and understand,
  * as the problem of __"who changed my variable" is so much harder to figure out__.
  *
  * I appreciate the use of tailrec so you don't blow up your stack.
  * Try to eliminate vars and to make the methods return a new list with any change to the list.
 **/
class MutableMyList() {
  class Node(val x: Int, var next: Node) {
    override def toString: String = String.valueOf(x)
  }

  var head: Node = null;
  def tail : Node = head.next;

  @tailrec
  final def getlast(current: Node = this.head) : Node = {
    if ( current == null ) null
    else if(current.next == null ) current
    else getlast(current.next)
  }
  def isEmpty : Boolean = {
    if (this.head == null) return true else false
  }

  override def toString: String = {
    if (isEmpty) "[EMPTY]"
    else "[ " + nodesAsString() + "]"
  }

  @tailrec
  final def nodesAsString(current: Node = head, accum: String = "") : String = {
    if(current == null) accum;
    else nodesAsString(current.next, accum + current.toString + " ")
  }

  def add(j : Int) : Unit = {
    val newNode : Node = new Node(j, null)
    val lastNode : Node = getlast()
    if(lastNode==null) head = newNode
    else lastNode.next = newNode
  }
}

/* English class

TIMTOWTDI:  /tɪmˈtəʊdi/
Acronym of there's more than one way to do it:
a motto associated with the Perl programming language.
there is more than one way to skin a cat

AFAIK:
written abbreviation for as far as I know:
used when you believe that something is true,
but you are not completely certain

 */