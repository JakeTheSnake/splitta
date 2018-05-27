import kotlin.math.min

fun main(args: Array<String>) {
    val people = listOf(
            Person("Erik A", 5400+500+7700),
            Person("Jacob R", 799),
            Person("Jakob S")
    )

    run(people)
    people.forEach { println(it) }
}

fun run(people: List<Person>) {
    val totalAmount = people.map { it.balance }
                .reduce { acc, i -> acc + i }
    val perPerson = totalAmount / people.size
    println("Total summa: $totalAmount")
    println("Per person: $perPerson")
    balance(people, perPerson)
}

fun balance(people: List<Person>, perPerson: Int) = people.sortedBy { it.balance }
            .filter { it.balance < perPerson }
            .forEach { while(it.balance < perPerson)
                with(personWithHighestExpense(people)) { it.sendMoneyTo(maxTransfer(it, this, perPerson), this) } }

fun maxTransfer(from: Person, to: Person, perPerson: Int) = min(perPerson - from.balance, to.balance - perPerson)

private fun personWithHighestExpense(people: List<Person>) = people.sortedByDescending { it.balance }.first()