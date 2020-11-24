#!/usr/bin/env scala

object HelloWorld extends App {
  print(args(0) + " ")
  print(args(1) + "\n")
}

val args = Array("Hello","World!")
HelloWorld.main(args)

val msg="This is by shell"
println(msg)
