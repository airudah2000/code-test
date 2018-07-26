package com.phone

import java.util.Date
import java.text.SimpleDateFormat
import java.sql.Time

trait Util {

  final val sdf = new SimpleDateFormat("HH:mm:ss")

  case class Calls(customerId: Char, numberCalled: Int, callDuration: Date)
//  def dateShell = {
//    val dt = new Date(0L)
//  }

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
