package com.example.api

import com.example.api.healthcheck.DbStatus
import com.example.domain.{ Item, ItemId }
import zio.json._

final case class CreateItemRequest(name: String, price: BigDecimal)
final case class UpdateItemRequest(name: String, price: BigDecimal)
final case class PartialUpdateItemRequest(name: Option[String], price: Option[BigDecimal])
final case class EmptyResponse()

trait JsonSupport {
  implicit val itemIdDecoder: JsonDecoder[ItemId] = JsonDecoder[Long].map(ItemId)
  implicit val itemIdEncoder: JsonEncoder[ItemId] = JsonEncoder[Long].contramap(_.value)

  implicit val itemDecoder: JsonDecoder[Item] = DeriveJsonDecoder.gen[Item]
  implicit val itemEncoder: JsonEncoder[Item] = DeriveJsonEncoder.gen[Item]

  implicit val dbStatusDecoder: JsonDecoder[DbStatus] = DeriveJsonDecoder.gen[DbStatus]
  implicit val dbStatusEncoder: JsonEncoder[DbStatus] = DeriveJsonEncoder.gen[DbStatus]

  implicit val createItemRequestDecoder: JsonDecoder[CreateItemRequest] = DeriveJsonDecoder.gen[CreateItemRequest]
  implicit val createItemRequestEncoder: JsonEncoder[CreateItemRequest] = DeriveJsonEncoder.gen[CreateItemRequest]

  implicit val updateItemRequestDecoder: JsonDecoder[UpdateItemRequest] = DeriveJsonDecoder.gen[UpdateItemRequest]

  implicit val partialUpdateItemRequestDecoder: JsonDecoder[PartialUpdateItemRequest] =
    DeriveJsonDecoder.gen[PartialUpdateItemRequest]

  implicit val emptyResponseEncoder: JsonEncoder[EmptyResponse] = DeriveJsonEncoder.gen[EmptyResponse]
}

object JsonSupport extends JsonSupport
