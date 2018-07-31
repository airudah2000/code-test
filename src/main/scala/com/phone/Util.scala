package com.phone

import java.util.{Calendar, Date}
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import scala.io.Source

trait Util {

  final val sdf = new SimpleDateFormat("HH:mm:ss")
  final val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

  case class Call(customerId: Char, numberCalled: String, callDuration: LocalTime)

  def longToTimeString(dateAsLong: Long): String = {
    val dt = new Date(0L)
    dt.setTime(dateAsLong)
    sdf.format(dt)
  }

  def sumUpTime(times: List[LocalTime]): String = {
    val calendar = Calendar.getInstance()
    calendar.set(0,0,0,0,0,0)

    for(t <- times){
      calendar.add(Calendar.HOUR,   t.getHour)
      calendar.add(Calendar.MINUTE, t.getMinute)
      calendar.add(Calendar.SECOND, t.getSecond)
    }

    val totalTime: String = longToTimeString(calendar.getTime.getTime)

    totalTime
  }

  def parseCallLog(filePath: String): Seq[Call] = {
    val calls: Seq[Call] = for {
      line <- Source
        .fromFile(filePath)
        .getLines
        .filter(_.nonEmpty)
        .toSeq
        .map(_.split(" "))
    } yield {
      Call(line.head.head, line(1), LocalTime.parse(line(2), dtf))
    }

    calls
  }

  def applyPromo(calls: Seq[Call]): Seq[Call] = {
    val sortedByPhoneNumber: Map[String, Seq[Call]] = calls.groupBy(_.numberCalled)
    val totalCost: scala.Iterable[String] = sortedByPhoneNumber
      .map(c => c._1 -> callsCost(c._2))
      .toList
      .sortBy(_._2)
      .reverse
      .toMap
      .keys

    calls.filterNot(_.numberCalled == totalCost.head)
  }

  def callsCost(calls: Seq[Call]): Double = {
    val cost = calls.map(c =>
      if(c.callDuration.getMinute >= 3) addCosts(c.callDuration, 0.03)
      else addCosts(c.callDuration, 0.05)
    ).sum

    "%.2f".format(cost).toDouble
  }

  def addCosts(time: LocalTime, costPerSec: Double): Double = {
    val hourCost = time.getHour  * 60 * 60 * costPerSec
    val minCost = time.getMinute * 60 * costPerSec
    val secCost = time.getSecond * costPerSec
    hourCost + minCost + secCost
  }

}
