fun main() {
    println("Welcome to your banking system")
    println("What type of account would you like to create?")
    println("1. Debit account")
    println("2. Credit account")
    println("3. Checking account")

    var accountType = ""
    var userChoice = 0

    while (accountType == "") {
        println("Choose an option (1, 2 or 3)")
        userChoice = (1 .. 5).random()
        println("The selected option is $userChoice")

        accountType = when(userChoice) {
            1 -> "debit"
            2 -> "credit"
            3 -> "checking"
            else -> continue
        }
    }

    println("You have created a $accountType account")

    var accountBalance: Int = (0 .. 1000).random()
    println("The current balance is $accountBalance dollars.")

    val money: Int = (0 .. 1000).random()
    println("The amount you transferred is $money dollars.")

    var output = 0

    fun withdraw(amount: Int): Int {
        accountBalance -= amount
        println("You successfully withdrew $amount dollars. The current balance is $accountBalance dollars.")
        return amount
    }

    output = withdraw(money)
    println("The amount you withdrew is $output dollars.")

    fun debitWithdraw(amount: Int): Int {
        if (accountBalance == 0) {
            println("Can't withdraw, no money on this account!")
            return accountBalance
        } else if (amount > accountBalance) {
            println("Not enough money on this account! The checking balance is $accountBalance dollars.")
            return 0
        }
        return withdraw(amount)
    }

    output = debitWithdraw(money)
    println("The amount you withdrew is $output dollars.")

    fun deposit(amount: Int): Int {
        accountBalance += amount
        println("You successfully deposited $amount dollars. The current balance is $accountBalance dollars.")
        return amount
    }

    output = deposit(money)
    println("The amount you deposited is $output dollars.")

    fun creditDeposit(amount: Int): Int {
        return if (accountBalance == 0) {
            println("You don't need to deposit anything in order to pay off the account since it has already been paid off")
            accountBalance
        } else if (accountBalance + amount > 0) {
            println("Deposit failed, you tried to pay off an amount greater than the credit balance. The checking balance is $accountBalance dollars.")
            0
        } else if (amount == -accountBalance) {
            println("You have paid off this account!")
            amount
        } else {
            deposit(amount)
        }
    }

    output = creditDeposit(money)
    println("The amount you deposited is $output dollars.")
}
