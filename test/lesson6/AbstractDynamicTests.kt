package lesson6

import kotlin.test.assertEquals

abstract class AbstractDynamicTests {
    fun longestCommonSubSequence(longestCommonSubSequence: (String, String) -> String) {
        assertEquals("", longestCommonSubSequence("", ""))
        assertEquals("", longestCommonSubSequence("мой мир", "я"))
        assertEquals("289", longestCommonSubSequence("28596", "25689"))
        assertEquals("", longestCommonSubSequence("12345", "здравствуй!"))
        assertEquals("1", longestCommonSubSequence("1", "1"))
        assertEquals("13", longestCommonSubSequence("123", "13"))
        assertEquals("здс", longestCommonSubSequence("здравствуй мир", "мы здесь"))
        assertEquals("emt ole", longestCommonSubSequence("nematode knowledge", "empty bottle"))
        assertEquals("ありがとう", longestCommonSubSequence("ありがとう", "どうもありがとう"))
        assertEquals(
            "*()0)!1",
            longestCommonSubSequence("*9?())0)0(%!143#3№4$5^@\'\":,.;#<?>", "r$56^5$3@#№Ъх1!]{\"tG&%^;:*@.,)(()0)!11")
        )
        val expectedLength = "e kerwelkkd r".length
        assertEquals(
            expectedLength, longestCommonSubSequence(
                "oiweijgw kejrhwejelkrw kjhdkfjs hrk",
                "perhkhk lerkerorwetp lkjklvvd durltr"
            ).length, "Answer must have length of $expectedLength, e.g. 'e kerwelkkd r' or 'erhlkrw kjk r'"
        )
        val expectedLength2 = """ дд саы чтых,
евшнео ваа се сви дн.
        """.trimIndent().length
        assertEquals(
            expectedLength2, longestCommonSubSequence(
                """
Мой дядя самых честных правил,
Когда не в шутку занемог,
Он уважать себя заставил
И лучше выдумать не мог.
                """.trimIndent(),
                """
Так думал молодой повеса,
Летя в пыли на почтовых,
Всевышней волею Зевеса
Наследник всех своих родных.
                """.trimIndent()
            ).length, "Answer must have length of $expectedLength2"
        )
    }

    fun longestIncreasingSubSequence(longestIncreasingSubSequence: (List<Int>) -> List<Int>) {
        assertEquals(listOf(), longestIncreasingSubSequence(listOf()))
        assertEquals(listOf(1), longestIncreasingSubSequence(listOf(1)))
        assertEquals(listOf(1, 2), longestIncreasingSubSequence(listOf(1, 2)))
        assertEquals(listOf(2), longestIncreasingSubSequence(listOf(2, 1)))
        assertEquals(
            listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10),
            longestIncreasingSubSequence(listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10))
        )
        assertEquals(listOf(2, 8, 9, 12), longestIncreasingSubSequence(listOf(2, 8, 5, 9, 12, 6)))
        assertEquals(
            listOf(23, 34, 56, 87, 91, 98, 140, 349), longestIncreasingSubSequence(
                listOf(
                    23, 76, 34, 93, 123, 21, 56, 87, 91, 12, 45, 98, 140, 12, 5, 38, 349, 65, 94,
                    45, 76, 15, 99, 100, 88, 84, 35, 88
                )
            )
        )
        assertEquals(listOf(9), longestIncreasingSubSequence(listOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0)))
        assertEquals(
            listOf(-10, -7, -6, -5, -4, -3, -2, -1, 1, 10),
            longestIncreasingSubSequence(listOf(-10, -7, -9, -6, -7, -5, -8, -4, -3, -2, -1, 1, 0, -5, 10))
        )
        assertEquals(
            listOf(0, 1, 4, 6, 43, 45, 54),
            longestIncreasingSubSequence(
                listOf(
                    87,
                    97,
                    0,
                    5,
                    -1,
                    143,
                    -2,
                    -100,
                    60,
                    90,
                    1,
                    4,
                    3,
                    6,
                    5,
                    100,
                    5555,
                    43,
                    -9,
                    -65,
                    -87,
                    67,
                    45,
                    2,
                    0,
                    1,
                    54,
                    -60,
                    -100
                )
            )
        )
    }

    fun shortestPathOnField(shortestPathOnField: (String) -> Int) {
        assertEquals(1, shortestPathOnField("input/field_in2.txt"))
        assertEquals(12, shortestPathOnField("input/field_in1.txt"))
        assertEquals(43, shortestPathOnField("input/field_in3.txt"))
        assertEquals(28, shortestPathOnField("input/field_in4.txt"))
        assertEquals(222, shortestPathOnField("input/field_in5.txt"))
        assertEquals(15, shortestPathOnField("input/field_in6.txt"))
    }

}