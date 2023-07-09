package `1_BruteForce`

// 문제 출처
// https://school.programmers.co.kr/learn/courses/30/lessons/42840?language=kotlin

fun main() {
    val solution = 모의고사()
    solution.solution(intArrayOf(1, 2, 3, 4, 5))
    solution.solution(intArrayOf(1, 3, 2, 4, 2))
    solution.solution(intArrayOf(1, 3, 2, 4, 2, 1, 2, 3, 4, 5, 1, 3, 2, 4, 2))
}

class 모의고사 {

    data class Supo(
        val supoNumber: Int,
        var score: Int = 0,
        val pattern: IntArray,
    )

    val supoGroup = listOf(
        Supo(
            supoNumber = 1,
            pattern = intArrayOf(1, 2, 3, 4, 5),
        ),
        Supo(
            supoNumber = 2,
            pattern = intArrayOf(2, 1, 2, 3, 2, 4, 2, 5),
        ),
        Supo(
            supoNumber = 3,
            pattern = intArrayOf(3, 3, 1, 1, 2, 2, 4, 4, 5, 5),
        )
    )

    fun solution(answers: IntArray): IntArray {
        supoGroup.forEachIndexed { i, supo ->
            supoGroup[i].run {
                score = answerCount(supo.pattern, answers)
            }
        }
        val result = sortingAnswerSupoList(supoGroup)
        println(result.contentToString())
        return result
    }

    fun sortingAnswerSupoList(supoGroup: List<Supo>): IntArray {

        val ratingList = supoGroup.sortedByDescending { it.score }.toMutableList()
        ratingList.removeIf { it.score == 0 }

        if (ratingList.isEmpty())
            return intArrayOf()

        val bestScore: Int = ratingList[0].score

        val filteredHighScoreUser = ratingList.filter { it.score == bestScore }

        val result = mutableListOf<Int>()

        filteredHighScoreUser.forEach {
            result.add(it.supoNumber)
        }

        return result.toIntArray()
    }

    fun answerCount(pattern: IntArray, answers: IntArray): Int {
        var patternCurrentIndex = 0
        var answerCount = 0
        answers.forEach {
            if (pattern[patternCurrentIndex] == it)
                answerCount++

            if(patternCurrentIndex == (pattern.size - 1))
                patternCurrentIndex = 0
            else
                patternCurrentIndex++
        }
        return answerCount
    }
}