class Person(private val name: String, var balance: Int = 0) {
    val incoming: MutableList<Transaction> = mutableListOf()
    val outgoing: MutableList<Transaction> = mutableListOf()

    fun addExpense(expense: Int) {
        balance += expense
    }

    fun sendMoneyTo(amount: Int, person: Person) {
        balance += amount
        outgoing.add(Transaction(amount, person))
        person.receiveMoneyFrom(amount, this)
    }

    private fun receiveMoneyFrom(amount: Int, person: Person) {
        balance -= amount
        incoming.add(Transaction(amount, person))
    }

    override fun toString() =
        "$name - $balance\n" +
                outgoing.joinToString { "\t${it.amount} -> ${it.person.name}\n" }
}

data class Transaction(val amount: Int, val person: Person)

