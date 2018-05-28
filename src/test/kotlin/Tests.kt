import org.junit.Assert.assertEquals
import org.junit.Test

class Tests {

    private var name: Int = 1

    @Test
    fun a_person_can_send_money_to_another() {
        val person1 = person(500)
        val person2 = person()

        person2.sendMoneyTo(250.0, person1)

        assertEquals(person1.balance, 250)
        assertEquals(person2.balance, 250)
        person1.incoming.first().apply {
            assertEquals(amount, 250)
            assertEquals(person, person2)
        }
        person2.outgoing.first().apply {
            assertEquals(amount, 250)
            assertEquals(person, person1)
        }
    }

    @Test
    fun balance1() {
        val p1 = person(6000)
        val p2 = person()
        val p3 = person()

        val people = listOf(p1, p2, p3)
        run(people)
        people.forEach { println(it) }

        assertEquals(p1.balance, 2000)
        assertEquals(p2.balance, 2000)
        assertEquals(p3.balance, 2000)
        assertEquals(p1.incoming.size, 2)
        assertEquals(p2.outgoing.size, 1)
        assertEquals(p3.outgoing.size, 1)


    }

    @Test
    fun balance2() {
        val p1 = person(6000)
        val p2 = person(6000)
        val p3 = person()

        val people = listOf(p1, p2, p3)
        run(people)
        people.forEach { println(it) }

        people.forEach { assertEquals(it.balance, 4000) }
        assertEquals(p1.incoming.size, 1)
        assertEquals(p2.incoming.size, 1)
        assertEquals(p3.outgoing.size, 2)
    }

    @Test
    fun balance3() {
        val p1 = person(7000)
        val p2 = person(2000)
        val p3 = person()

        val people = listOf(p1, p2, p3)
        run(people)
        people.forEach { println(it) }

        people.forEach { assertEquals(it.balance, 3000) }
        assertEquals(p1.incoming.size, 2)
        assertEquals(p2.outgoing.first().amount, 1000)
        assertEquals(p3.outgoing.first().amount, 3000)
    }

    @Test
    fun balance4() {
        val p1 = person(6000)
        val p2 = person(5000)
        val p3 = person(1000)
        val p4 = person()

        val people = listOf(p1, p2, p3, p4)
        run(people)
        people.forEach { println(it) }

        people.forEach { assertEquals(it.balance, 3000) }
    }

    @Test
    fun balance5() {
        val p1 = person(3500)
        val p2 = person(3300)
        val p3 = person(2800)
        val p4 = person(2700)

        val people = listOf(p1, p2, p3, p4)
        run(people)
        people.forEach { println(it) }

        people.forEach { assertEquals(it.balance, 3075) }
    }

    private fun person(amount: Int = 0) = Person("${name++}", 0.0 + amount)
}