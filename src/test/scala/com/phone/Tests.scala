package com.phone

import java.sql.Time
import java.time.temporal.ChronoUnit
import java.time.{LocalDateTime, LocalTime}
import java.util.Date

import org.scalatest.FunSuite

class Tests extends FunSuite with Util {
  test("I can run a test") {


    val t1 = Time.valueOf("00:00:23")
    val t2 = Time.valueOf("00:00:27")
    val t3 = Time.valueOf("00:00:05")

    val sumOfTimes: Long = t1.getTime() + t2.getTime() + t3.getTime()

    println(new Date(sumOfTimes).getTime)

  }

  test("Parse calls data") {
    val call = Calls('A', 1234567890, new Date)
  }
}
