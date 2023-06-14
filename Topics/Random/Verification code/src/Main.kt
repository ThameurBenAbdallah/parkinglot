fun verificationCode(): String {
    // use these letters to create the code
    val letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789"
    var code = ""
    for (i in 1..10){
        code += letters.random()
    }
    return code
}