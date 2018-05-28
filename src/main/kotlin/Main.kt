import kotlin.math.min

fun main(args: Array<String>) {
    val people = listOf(
            Person("Erik A", 5400.0+500.0+7700.0),
            Person("Jacob R", 799.0),
            Person("Erik L", 160.0),
            Person("Tobias", 280.0),
            Person("Jakob S"),
            Person("Mathias"),
            Person("Mikael", 5790.0+4800+2600),
            Person("Wallin"),
            Person("Mattis"),
            Person("Christoffer"),
            Person("Peter", 3855.0)
    )

    run(people)
    val richard = Person("Richard")
    people.forEach { richard.sendMoneyTo(101.0, it) }
    people.forEach { println(it) }
    println(richard)


}


fun run(people: List<Person>) {
    val totalAmount = people.map { it.balance }
                .reduce { acc, i -> acc + i }
    val perPerson = totalAmount / people.size
    println("Total summa: $totalAmount")
    println("====================================")
    balance(people, perPerson)

}

fun balance(people: List<Person>, perPerson: Double) =
        people.filter { it.balance < perPerson}
            .sortedBy { it.balance }
            .forEach {
                while(it.balance < perPerson)
                    with(personWithHighestExpense(people)) {
                        it.sendMoneyTo(maxTransfer(it, this, perPerson), this)
                    }
            }

private fun maxTransfer(from: Person, to: Person, perPerson: Double) = min(perPerson - from.balance, to.balance - perPerson)

private fun personWithHighestExpense(people: List<Person>) = people.sortedByDescending { it.balance }.first()