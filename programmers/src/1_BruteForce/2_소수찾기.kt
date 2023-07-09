package `1_BruteForce`

// 문제 출처
// https://school.programmers.co.kr/learn/courses/30/lessons/42839?language=kotlin

/*
* 소수란?
* - 약수가 두 개 뿐인 자연수
*/


fun main() {
//    val solution = 소수찾기()
    val solution = 정답참고()
    solution.solution(numbers = "17")
    solution.solution(numbers = "011")
    solution.solution(numbers = "100100")
    solution.solution(numbers = "1234567")
    solution.solution(numbers = "3126745")
    solution.solution(numbers = "16745")
    solution.solution(numbers = "000011")
    solution.solution(numbers = "110000")
    solution.solution(numbers = "0111701")
}

class 정답참고 {
    val countSet = mutableSetOf<Int>()

    val String.isPrime get(): Boolean {
        val number = this.toInt()

        if (number == 1 || number == 0)
            return false

        for (i in (2..(number / 2)))
            if (number % i == 0)
                return false

        return true
    }

    val totalCount get(): Int {
        val count = countSet.filter { it.toString().isPrime }.count()
        countSet.clear()
        return count
    }

    fun solution(numbers: String): Int {
        getCombination(numbers, "")
        val answer = totalCount
        println(answer)
        return answer
    }

    fun getCombination(numbers: String, result: String) {
        if (result.isNotEmpty())
            countSet.add(result.toInt())
        if (numbers.isEmpty()) return
        numbers.forEachIndexed { index, c ->
            getCombination(numbers.removeRange(index..index), c.plus(result))
        }
    }
}

class 소수찾기 {

    val countSet = mutableSetOf<Int>()

    val String.isPrime get(): Boolean {
        val number = this.toInt()

        if (number == 1 || number == 0)
            return false

        for (i in (2..(number / 2)))
            if (number % i == 0)
                return false

        return true
    }

    fun String.countOneIfPrime() {
        val number = this.toInt()
//        if (isPrime)
        countSet.add(number)
    }

    val String.toList
        get(): MutableList<Char> =
            this.toCharArray().toMutableList()

    val totalCount get(): Int {
        val count = countSet.size
        countSet.clear()
        return count
    }

    fun solution(numbers: String): Int {
        findPrime(numbers)
        val answer = totalCount
        println(answer)
        return answer
    }

    fun findPrime(numbers: String) {
        val cards = numbers.toList
        repeat(cards.size) {
            val newNumberSet = cards.joinToString("")
            newNumberSet.countOneIfPrime()

            findPrimeCutLast(newNumberSet)

            val first = cards.first()
            cards.removeFirst()
            cards.add(first)
        }
    }

    fun findPrimeCutLast(numbers: String) {
        var cutNumbers = numbers
        repeat(numbers.length) {
            cutNumbers.countOneIfPrime()
            cutNumbers = cutNumbers.dropLast(1)
        }
    }
}
