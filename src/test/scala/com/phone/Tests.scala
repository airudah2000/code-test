package com.phone

import java.sql.Time
import java.time.temporal.ChronoUnit
import java.time.{LocalDateTime, LocalTime}
import java.util.{Calendar, Date}

import org.scalatest.FunSuite

import scala.collection.immutable

class Tests extends FunSuite with Util {
  test("I can run a test") {

    val t1 = LocalDateTime.parse("00:00:23", dtf)
    val t2 = LocalDateTime.parse("00:00:27", dtf)
    val t3 = LocalDateTime.parse("00:00:05", dtf)

    def addUpTime(times: List[LocalDateTime]): Unit = {
      val calendar = Calendar.getInstance()
      calendar.set(0,0,0, 0, 0, 0)

      for(t <- times){
        calendar.add(Calendar.HOUR,  t.getHour)
        calendar.add(Calendar.MINUTE, t.getMinute)
        calendar.add(Calendar.SECOND, t.getSecond)
      }

      val summed = longToTimeString(calendar.getTime.getTime)

      println("XX")
      println(calendar.getTime.toString)
      println(summed)
      println("XX")


    }

    addUpTime(List(t1, t2, t3))

  }

  test("Parse calls data") {
    val call = Calls('A', 1234567890, new Date)
  }
}
