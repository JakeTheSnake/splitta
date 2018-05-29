class Person(val name: String, var expenses: Double = 0.0) {
    val balance: Double
        get() = expenses - incoming.sumByDouble { it.amount } + outgoing.sumByDouble { it.amount }

    val incoming: MutableList<Transaction> = mutableListOf()
    val outgoing: MutableList<Transaction> = mutableListOf()

    fun sendMoneyTo(amount: Double, person: Person) =
            with(Transaction(amount, this, person)) {
                outgoing.add(this)
                person.incoming.add(this)
            }

    override fun toString() =
        "$name - Expenses(${expenses.format()} - Balance(${balance.format()}) kr\n" +
                outgoing.joinToString("") { "\tSkicka ${it.amount.format()} kr till ${it.to.name}\n" } +
                incoming.joinToString("") { "\tTa emot ${it.amount.format()} kr från ${it.from.name}\n" }

    private fun Double.format(digits: Int = 0): String = java.lang.String.format("%.${digits}f", this)
}

data class Transaction(val amount: Double, val from: Person, val to: Person) {
    override fun toString() = "Från ${from.name} till ${to.name}: $amount"
}

