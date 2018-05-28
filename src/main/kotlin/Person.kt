class Person(val name: String, var balance: Double = 0.0) {
    val incoming: MutableList<Transaction> = mutableListOf()
    val outgoing: MutableList<Transaction> = mutableListOf()

    fun sendMoneyTo(amount: Double, person: Person) {
        balance += amount
        outgoing.add(Transaction(amount, person))
        person.receiveMoneyFrom(amount, this)
    }

    private fun receiveMoneyFrom(amount: Double, person: Person) {
        balance -= amount
        incoming.add(Transaction(amount, person))
    }

    override fun toString() =
        "$name - ${balance.format()} kr\n" +
                outgoing.joinToString("") { "\tSkicka ${it.amount.format()} kr till ${it.person.name}\n" } +
                incoming.joinToString("") { "\tTa emot ${it.amount.format()} kr från ${it.person.name}\n" }

    private fun Double.format(digits: Int = 0): String = java.lang.String.format("%.${digits}f", this)
}

data class Transaction(val amount: Double, val person: Person)

