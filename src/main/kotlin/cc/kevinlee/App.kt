package cc.kevinlee

import java.math.BigDecimal

import cc.kevinlee.MyNum.*

/**
 * @author Kevin Lee
 * @since 2016-02-21
 */
fun main(args: Array<String>) {
  println("Hello world")

  val myName = "Kevin"

  println("Hello " + myName + ". " + myName.length)
  println("Hello $myName. $myName.length")
  println("Hello $myName. ${myName.length}")

  val a = 10
  val b = 5
  println("max($a, $b): ${max(a, b)}")
  println("max2($a, $b): ${max2(a, b)}")

  println(toStringIfString("a"))
  println(toStringIfString(1))

  println(findLength("abc"))
  println(findLength(123))

  val abc = "abc"
  println("abc.length: ${abc.length}")

  val abc2: String? = null
  println("abc2?.length: ${abc2?.length}")

  for (i in 1..10) {
    print("i = $i, ")
  }

  println("\n=========================")
  val numbers = arrayOf(1, 2, 3, 4, 5)

  for (i in numbers) {
    print("i: $i, ")
  }
  println("\n=========================")

  val nums1 = arrayListOf<Int>()
  for (i in numbers) {
    if (i > 2)
      nums1.add(i)
  }

  println("""
-----------------------------------
val nums1 = arrayListOf<Int>()
for (i in numbers) {
  if (i > 2)
    nums1.add(i)
}
result: $nums1
-----------------------------------

""")

  val filteredNumbers = numbers.filter { i -> i > 2 }
  println("numbers.filter { i -> i > 2 } => $filteredNumbers")

  val mappedNumbers = numbers.map { i -> i * 2 }
  println("numbers.map { i -> i * 2 } => $mappedNumbers")

  val product = Product(1L, "A", BigDecimal("100"))

  println(product.name)
  println(product.price)
  println(product)

  val product2 = Product("B", BigDecimal("100"))
  println(product2)
  val product3 = Product(3L, "C")
  println(product3)

  val product4 = Product("D")
  println(product4)


  C(1).test()
  D(1).test()
  E(1).test()

  println(C(1))


  println(NewProduct(1L, "NewProductA", BigDecimal("20")))

  println("""
  Product(1L, "A", BigDecimal("10")) == Product(1L, "A", BigDecimal("10"))
  ${ Product(1L, "A", BigDecimal("10")) == Product(1L, "A", BigDecimal("10")) }
  """)

  println("""
  NewProduct(1L, "NewProductA", BigDecimal("20")) == NewProduct(1L, "NewProductA", BigDecimal("20"))
  ${ NewProduct(1L, "NewProductA", BigDecimal("20")) == NewProduct(1L, "NewProductA", BigDecimal("20")) }
  """)


  val newProcutA = NewProduct(1L, "NewProductA", BigDecimal("20"))
  println("""val newProcutA = NewProduct(1L, "NewProductA", BigDecimal("20"))""")
  println("newProcutA: $newProcutA")
  val newProductB = newProcutA.copy(2L, "NewProductB")
  println("""
val newProductB = newProcutA.copy(2L, "NewProductB")""")
  println("newProductB: $newProductB}")

  val (id, name, price) = newProcutA
  println("""
  val (id, name, price) = newProcutA
  ($id, $name, $price)""")

  tellMeWhatItIs(10)
  tellMeWhatItIs(5)
  tellMeWhatItIs(100)
  tellMeWhatItIs("aaa")



  println("""
MyNum
================================""")
  println(Zero)
  println(Num(Zero))
  println(Num(Num(Zero)))
  println(Num(Num(Num(Zero))))
  println("--------------------------------")

  val something = Something(1, "Kevin")
  println("something.name = ${something.name}")
  something.name = "Tom"
  println("something.name = ${something.name}")

  println("something.id = ${something.id}")
  something.id = 2
  println("something.id = ${something.id}")

  println("something.email = ${something.email}")
  something.email = "test@email.com"
  println("something.email = ${something.email}")

  println("something.xyz = ${something.xyz}")


  println("\n=================================")
  val somethingElse = SomethingElse(10L, "John")
  println("somethingElse.xyz: ${somethingElse.xyz}")
  somethingElse.xyz = 123
  println("somethingElse.xyz: ${somethingElse.xyz}")

  println("\n=================================")
  val products =
      listOf(
          NewProduct(1L, "A", BigDecimal("10")),
          NewProduct(2L, "B", BigDecimal("100")),
          NewProduct(3L, "C", BigDecimal("55"))
          )

  val result = arrayListOf<NewProduct>()
  for (product in products) {
    if (product.price > BigDecimal("50")) {
      result.add(product)
    }
  }
  println("Products more expensive than $50: \n${result.map { it.toString() + "\n"} } ")

  println("\n=================================")
  println("Products more expensive than $50:")
  val result2 = products.filter { it.price > BigDecimal("50") }
                        .map { it.toString() }
                        .forEach { println(it) }
}

class Something(var id: Long, n: String) {

  //  var id: Long = num

  var name: String = n
    get() = field
    set(value): Unit {
      if (value.length > 10)
        field = value.substring(0, 10)
      else
        field = value
    }

  var email: String = ""

  val xyz: Int = 999
    get() = field * 1000

}
class SomethingElse(var id: Long, n: String) {

  //  var id: Long = num

  var name: String = n
    get() = field
    set(value): Unit {
      if (value.length > 10)
        field = value.substring(0, 10)
      else
        field = value
    }

  var email: String = ""

  // xyz가 null일경우 기본값인 1을 사용하는 코드
  var xyz: Int? = null
    get() = (field?: 1) * 1000

}


sealed class MyNum {

  abstract val value: Int

  override fun toString() = value.toString()

  object Zero : MyNum() {

    override val value = 0
  }

  class Num(private val next: MyNum) : MyNum() {

    override val value = 1 + next.value
  }
}


fun aboutProduct(product: Any): Unit = when (product) {
//  NewProduct(id, name, price) -> println("$id, $name, $price")
  is NewProduct -> println("${product.id}, ${product.name}, ${product.price}")
  else -> println("Not Product")
}

fun tellMeWhatItIs(n: Any): Unit = when (n) {
  10 -> println("It is 10.")
  in 1..9 -> println("between 1 and 9")
  is String -> println("String: $n")
  else -> println("whatever")
}

class Product(id: Long?, var name: String, var price: BigDecimal = BigDecimal("0")) {

  constructor(name: String, price: BigDecimal = BigDecimal("0")) : this(null, name, price)

  private val _id = id

  override fun toString(): String {
    return "Product(id: $_id, name: $name, price $price)"
  }
}

data class NewProduct(val id: Long?, var name: String, var price: BigDecimal = BigDecimal("0"))

open class A(num: Int) {
  open fun test(): Unit = println("A.test()")
}

interface B {
  fun test(): Unit = println("B.test()")
}

class C(num: Int) : A(num), B {
  override fun test(): Unit = println("C.test()")
}

class D(num: Int) : A(num), B {
  override fun test() {
    super<A>.test()
  }
}

class E(num: Int) : A(num), B {
  override fun test() {
    super<B>.test()
  }
}


fun toStringIfString(value: Any): String? {
  if (value is String)
    return value

  return null
}

fun findLength(value: Any): Int? {
  if (value is String)
    return value.length

  return -1
}

fun max(a: Int, b: Int): Int {
  if (a > b)
    return a
  else
    return b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b
