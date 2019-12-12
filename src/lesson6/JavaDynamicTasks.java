package lesson6;

import kotlin.NotImplementedError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static java.lang.Math.max;


@SuppressWarnings("unused")
public class JavaDynamicTasks<T> {
    /**
     * Наибольшая общая подпоследовательность.
     * Средняя
     *
     * Дано две строки, например "nematode knowledge" и "empty bottle".
     * Найти их самую длинную общую подпоследовательность -- в примере это "emt ole".
     * Подпоследовательность отличается от подстроки тем, что её символы не обязаны идти подряд
     * (но по-прежнему должны быть расположены в исходной строке в том же порядке).
     * Если общей подпоследовательности нет, вернуть пустую строку.
     * Если есть несколько самых длинных общих подпоследовательностей, вернуть любую из них.
     * При сравнении подстрок, регистр символов *имеет* значение.
     */
    // Трудоёмкость: O(N*M);
    // Ресурсоёмкость: O(N*M).
    public static String longestCommonSubSequence(String first, String second) {
        List<String> firstList = new ArrayList<>(Arrays.asList(first.split("")));
        List<String> secondList = new ArrayList<>(Arrays.asList(second.split("")));

        List<String> list = longestCommonSubSequence(firstList, secondList);

        return new StringBuilder(list.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("", "", ""))).reverse().toString();
    }

    /**
     * Наибольшая возрастающая подпоследовательность
     * Сложная
     *
     * Дан список целых чисел, например, [2 8 5 9 12 6].
     * Найти в нём самую длинную возрастающую подпоследовательность.
     * Элементы подпоследовательности не обязаны идти подряд,
     * но должны быть расположены в исходном списке в том же порядке.
     * Если самых длинных возрастающих подпоследовательностей несколько (как в примере),
     * то вернуть ту, в которой числа расположены раньше (приоритет имеют первые числа).
     * В примере ответами являются 2, 8, 9, 12 или 2, 5, 9, 12 -- выбираем первую из них.
     */
    // Трудоёмкость: O(N^2);
    // Ресурсоёмкость: O(N^2).
    public static List<Integer> longestIncreasingSubSequence(List<Integer> list) {
        List<String> listStr = new ArrayList<>(list.size());
        for (Integer myInt : list)
            listStr.add(String.valueOf(myInt));

        java.util.Collections.sort(list);
        List<String> incListStr = new ArrayList<>(list.size());

        for (Integer myInt : list)
            incListStr.add(String.valueOf(myInt));

        List<String> res = longestCommonSubSequence(listStr, incListStr);
        List<Integer> result = new ArrayList<>();

        for (int i = res.size() - 1; i >= 0; i--)
            result.add(Integer.valueOf(res.get(i)));

        return result;
    }

    private static List<String> longestCommonSubSequence(List<String> first, List<String> second) {
        int n = first.size();
        int m = second.size();
        int[][] matrix = new int[n + 1][m + 1];

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < m + 1; j++) {
                String f = first.get(i - 1);
                String s = second.get(j - 1);
                if (first.get(i - 1).equals(second.get(j - 1)))
                    matrix[i][j] = matrix[i - 1][j - 1] + 1;
                else
                    matrix[i][j] = max(matrix[i - 1][j], matrix[i][j - 1]);
            }
        }

        List<String> result = new ArrayList<>();
        while (n > 0 && m > 0) {
            String f = first.get(n - 1);
            String s = second.get(m - 1);
            if (first.get(n - 1).equals(second.get(m - 1))) {
                result.add(first.get(n - 1));
                n--;
                m--;
            } else if (matrix[n - 1][m] == matrix[n][m])
                n--;
            else
                m--;
        }

        return result;
    }

    /**
     * Самый короткий маршрут на прямоугольном поле.
     * Средняя
     *
     * В файле с именем inputName задано прямоугольное поле:
     *
     * 0 2 3 2 4 1
     * 1 5 3 4 6 2
     * 2 6 2 5 1 3
     * 1 4 3 2 6 2
     * 4 2 3 1 5 0
     *
     * Можно совершать шаги длиной в одну клетку вправо, вниз или по диагонали вправо-вниз.
     * В каждой клетке записано некоторое натуральное число или нуль.
     * Необходимо попасть из верхней левой клетки в правую нижнюю.
     * Вес маршрута вычисляется как сумма чисел со всех посещенных клеток.
     * Необходимо найти маршрут с минимальным весом и вернуть этот минимальный вес.
     *
     * Здесь ответ 2 + 3 + 4 + 1 + 2 = 12
     */
    public static int shortestPathOnField(String inputName) {
        throw new NotImplementedError();
    }

    // Задачу "Максимальное независимое множество вершин в графе без циклов"
    // смотрите в уроке 5
}
