package com.phone

import java.time.LocalTime
import org.scalatest.FunSuite

class Tests extends FunSuite with Util {

  val filePath = "src/main/resources/calls.log"
  val calls: Seq[Call] = parseCallLog(filePath)

  test("I can sum up time") {
    val t1 = LocalTime.parse("00:00:23", dtf)
    val t2 = LocalTime.parse("00:00:27", dtf)
    val t3 = LocalTime.parse("00:00:10", dtf)

    val expectedTotalTime = "00:01:00"
    val actualTotalTime = sumUpTime(List(t1, t2, t3))

    assert(expectedTotalTime === actualTotalTime)
  }

  test("I can parse calls data from file") {
    val expectedFirstCall: Call = calls.head
    val actualFirstCall = Call('A', "555-333-212", LocalTime.parse("00:02:03", dtf))

    assert(expectedFirstCall === actualFirstCall)
  }

  test("Promotion should exclude calls to the phone numver with the highest cost for customer 'A' ") {
    val callsA: Seq[Call] = calls.filter(_.customerId == 'A')
    val mostExpensiveNumber = "555-333-212"
    val promoApplied = applyPromo(callsA)

    assert(!promoApplied.map(_.numberCalled).contains(mostExpensiveNumber))
    assert(promoApplied.size === callsA.size - 3)
  }

  test("Calculate the total call cost for customer 'B'") {
    val callsB = calls.filter(_.customerId === 'B')
    val callsBWithPromo = applyPromo(callsB)

    val expectedTotalCallCost: Double = 26.48
    val actualTotalCallCost: Double = callsCost(callsBWithPromo)

    assert(expectedTotalCallCost === actualTotalCallCost)
  }
}
