package it.unibo.sc

import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest : WordSpec({
    "Adding 3 and 2" should {
        "result in 5" {
            3 + 2 shouldBe 5
        }
    }
})
