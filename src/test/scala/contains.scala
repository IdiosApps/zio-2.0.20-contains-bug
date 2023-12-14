import zio.test._

object ExampleSpec extends ZIOSpecDefault {

  val numbers = List(1,2)
  val allNumbers = List(1,2,3)

  override def spec = suite("TestingApplicationsExamplesSpec")(

    test("contains should work with =>") {
      assertTrue(numbers.forall(num => allNumbers.contains(num)))
    },

    test("contains should work with _") {
      assertTrue(numbers.forall(allNumbers.contains(_)))
    },

    test("contains should work without placeholder or arrow") {
      assertTrue(numbers.forall(allNumbers.contains))
    },
  )
}