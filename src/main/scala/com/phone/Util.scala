package com.phone

import java.util.{Date, Locale, TimeZone}
import java.text.SimpleDateFormat
import java.sql.Time
import java.time.format.DateTimeFormatter

trait Util {

  final val sdf = new SimpleDateFormat("HH:mm:ss")
  final val dtf: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss")

  case class Calls(customerId: Char, numberCalled: Int, callDuration: Date)

  def timeStringToDate(timeString: String) = {
    val parsedTime: Time = Time.valueOf(timeString)
    val dt = new Date(0L)
    dt.setTime(parsedTime.getTime)
    val timezz: String =  sdf.format(dt)

    sdf.parse(timezz)
  }

  def longToTimeString(dateAsLong: Long) = {
    val dt = new Date(0L)
    dt.setTime(dateAsLong)
    sdf.format(dt)
  }
}
