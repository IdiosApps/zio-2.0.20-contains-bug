package com.example

import zio._
import zio.logging.backend.SLF4J

import com.example.api.healthcheck.InMemoryHealthCheckService
import com.example.domain.InMemoryItemRepository
import com.example.api.healthcheck.HealthCheckService
import com.example.domain.ItemRepository

object Layers {

  val logger: ULayer[Unit] = ZLayer.make[Unit](
    Runtime.removeDefaultLoggers,
    SLF4J.slf4j
  )

  val itemRepository: ULayer[ItemRepository] = InMemoryItemRepository.live

  val healthCheckService: ULayer[HealthCheckService] = InMemoryHealthCheckService.live
}
