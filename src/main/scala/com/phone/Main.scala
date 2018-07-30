package com.phone

object Main extends App with Util {
  val filePath = "src/main/resources/calls.log"

  val calls: Seq[Call] = parseCallLog(filePath)
  val groupedByCustomers: Map[Char, Seq[Call]] = calls.groupBy(_.customerId)

  for(customerCalls <- groupedByCustomers){
    val withPromo: Seq[Call] = applyPromo(customerCalls._2)
    val totalCallCost: Double = callCosts(withPromo)
    println(s"Total cost of today's calls for customer '${customerCalls._1}' is: [£$totalCallCost]\n")
  }

}
