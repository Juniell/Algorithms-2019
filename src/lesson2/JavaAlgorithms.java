package lesson2;

import kotlin.NotImplementedError;
import kotlin.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@SuppressWarnings("unused")
public class JavaAlgorithms {
    /**
     * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
     * Простая
     * <p>
     * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
     * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
     * <p>
     * 201
     * 196
     * 190
     * 198
     * 187
     * 194
     * 193
     * 185
     * <p>
     * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
     * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
     * Вернуть пару из двух моментов.
     * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
     * Например, для приведённого выше файла результат должен быть Pair(3, 4)
     * <p>
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public Pair<Integer, Integer> optimizeBuyAndSell(String inputName) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(inputName));
        String line;
        List<Integer> list = new ArrayList<>();
        int dif = 0;                                             // переменная, которая будет считать разницу
        Pair<Integer, Integer> res = new Pair<>(null, null);     // переменная, которая будет хранить пару

        while ((line = text.readLine()) != null) {
            Integer num = Integer.parseInt(line);
            if (num > 0)                            // проверяем на положительность
                list.add(num);                      // и добавляем  список
            else
                throw new IOException();
        }

        int size = list.size();
        for (int i = 0; i < size; i++) {                          // сравниваем все элементы
            Integer numFirst = list.get(i);
            for (int j = i + 1; j < size; j++) {
                Integer numSecond = list.get(j);
                if (numFirst < numSecond && numSecond - numFirst > dif) {
                    dif = numSecond - numFirst;
                    res = new Pair<>(i + 1, j + 1); // добавляем +1 к индексам, т.к. здесь отсчёт начинается с 1, а не с 0
                }
            }
        }
        if (res.getFirst() != null && res.getSecond() != null)
            return res;
        else
            throw new NotImplementedError();
    }

    /**
     * Задача Иосифа Флафия.
     * Простая
     * <p>
     * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 5
     * <p>
     * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
     * Человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 2 3
     * 8   4
     * 7 6 х
     * <p>
     * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
     * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
     * <p>
     * 1 х 3
     * 8   4
     * 7 6 Х
     * <p>
     * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
     * <p>
     * 1 Х 3
     * х   4
     * 7 6 Х
     * <p>
     * 1 Х 3
     * Х   4
     * х 6 Х
     * <p>
     * х Х 3
     * Х   4
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   х
     * Х 6 Х
     * <p>
     * Х Х 3
     * Х   Х
     * Х х Х
     * <p>
     * Общий комментарий: решение из Википедии для этой задачи принимается,
     * но приветствуется попытка решить её самостоятельно.
     */
    static public int josephTask(int menNumber, int choiceInterval) {
        throw new NotImplementedError();
    }

    /**
     * Наибольшая общая подстрока.
     * Средняя
     * <p>
     * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
     * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
     * Если общих подстрок нет, вернуть пустую строку.
     * При сравнении подстрок, регистр символов *имеет* значение.
     * Если имеется несколько самых длинных общих подстрок одной длины,
     * вернуть ту из них, которая встречается раньше в строке first.
     */
    static public String longestCommonSubstring(String first, String second) {
        int lenF = first.length();
        int lenS = second.length();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < lenF; i++) {                     // Проходимся по всем символам первой строки
            for (int j = 0; j < lenS; j++) {                 // и всем символам второй строки.
                StringBuilder str = new StringBuilder();
                int count = 0;                               // счётчик
                // Если было найдено совпадение, то пока символы будут совпадать, сравниваем следующие символы
                // и при соответствии заносим их в строку
                while (i + count < lenF && j + count < lenS && first.charAt(i + count) == second.charAt(j + count)) {
                    str.append(first.charAt(i + count));
                    count++;
                }
                if (str.length() > result.length())          // если длина найденной строки больше той, что нашли раньше
                    result = str;                            // запоминаем её
            }
        }
        return result.toString();
    }

    /**
     * Число простых чисел в интервале
     * Простая
     * <p>
     * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
     * Если limit <= 1, вернуть результат 0.
     * <p>
     * Справка: простым считается число, которое делится нацело только на 1 и на себя.
     * Единица простым числом не считается.
     */
    static public int calcPrimesNumber(int limit) {
        if (limit <= 1)
            return 0;
        int count = 0;
        // найдём все не простые числа и вычтем их количество из всех
        for (int i = 2; i <= limit; i++) {
            if (i % 2 == 0)     // если чётное - не простое
                count++;
            if (i % 2 == 1)     // если нечётное, то смотрим, делится ли хотя бы на одно число
                for (int j = 3; j <= Math.sqrt((double) i); j = j + 2)
                    if (i % j == 0) {
                        count++;
                        break;
                    }
        }
        return limit - count;
    }

    /**
     * Балда
     * Сложная
     * <p>
     * В файле с именем inputName задана матрица из букв в следующем формате
     * (отдельные буквы в ряду разделены пробелами):
     * <p>
     * И Т Ы Н
     * К Р А Н
     * А К В А
     * <p>
     * В аргументе words содержится множество слов для поиска, например,
     * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
     * <p>
     * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
     * и вернуть множество найденных слов. В данном случае:
     * ТРАВА, КРАН, АКВА, НАРТЫ
     * <p>
     * И т Ы Н     И т ы Н
     * К р а Н     К р а н
     * А К в а     А К В А
     * <p>
     * Все слова и буквы -- русские или английские, прописные.
     * В файле буквы разделены пробелами, строки -- переносами строк.
     * Остальные символы ни в файле, ни в словах не допускаются.
     */
    static public Set<String> baldaSearcher(String inputName, Set<String> words) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(inputName));
        String line;
        List<String> lines = new ArrayList<>();
        Set<String> result = new HashSet<>();

        while ((line = text.readLine()) != null)
            lines.add(line.toLowerCase().replaceAll(" ", ""));          // Убираем пробелы

        for (String word : words) {                                     // Проходимся по всем словам
            int y = -1;
            char[] chars = word.toLowerCase().toCharArray();
            List <Pair<Integer, Integer>> list = new ArrayList<>();
            char firstCh = chars[0];
            List<Character> wordCh = new ArrayList<>();

            for (int i = word.length() - 1; i > 0; i--)      // Преобразуем слово в массив строчных символов
                wordCh.add(chars[i]);                        // и ревёрсаем слово (т.е. РАКЕТА -> АТЕКА), убирая при этом первый символ, его найдём отдельно.
            Integer num = wordCh.size() - 1;                 // Находим индекс последнего в списке символа (второй символ изначального слова)

            for (String str : lines) {                       // Ищем первый символ (точнее все символы, которые подходят
                y++;
                for (int i = str.indexOf(firstCh); i >= 0; i = str.indexOf(firstCh, i + 1))
                    list.add(new Pair<>(i, y));
            }

            for (Pair<Integer, Integer> coord: list) {
                Set<Pair<Integer, Integer>> coords = new HashSet<>();
                coords.add(coord);
                if (findChar(wordCh, num, coords, coord.getFirst(), coord.getSecond(), lines)) {
                    result.add(word);
                    break;
                }
            }
        }
        return result;
    }

    // Рекурсивная функция поиска символа
    private static boolean findChar(List<Character> word, Integer num, Set<Pair<Integer, Integer>> coords,Integer x, Integer y, List<String> lines) {
        int length = lines.get(0).length() - 1;                 // максимальный индекс для х
        int height = lines.size() - 1;                          // максимальный индекс для y
        Character findCh = word.get(num);                       // искомый символ

        Pair<Integer, Integer> topInd = null;                   // координаты верхнего символа
        Pair<Integer, Integer> rightInd = null;                 // .......... правого ........
        Pair<Integer, Integer> bottomInd = null;                // .......... нижнего ........
        Pair<Integer, Integer> leftInd = null;                  // .......... левого .........

        List <Pair<Integer, Integer>> list = new ArrayList<>();         //список подходящих точек ждя дальнейшего поиска

        if (y != 0 && findCh.equals(lines.get(y - 1).charAt(x)))        // Находим верхний эдемент, если это возможно,
            list.add(new Pair<>(x, y - 1));                             // и добавляем его в список.
        if (x != length && findCh.equals(lines.get(y).charAt(x + 1)))   // Аналогично с правым элементом.
            list.add(new Pair<>(x + 1, y));
        if (y != height && findCh.equals(lines.get(y + 1).charAt(x)))   // Аналогично с нижним элементом.
            list.add(new Pair<>(x , y + 1));
        if (x != 0 && findCh.equals(lines.get(y).charAt(x - 1)))        // Аналогично с левым элементом.
            list.add(new Pair<>(x - 1, y));

        num--;
        if (list.isEmpty())                         // Если не найден ни один нужный символ,
            return false;                           // значит слово не найдено
        for (Pair<Integer, Integer> ch : list)
            if (!coords.contains(ch)) {             // Если символ ранее не использовался в этом слова, то
                if (num == -1)                      // а) если символ был последним в слове,
                    return true;                    // значит, всё слово найдено.
                coords.add(ch);                     // б) продолжаем искать, добавив символ в список использованных.
                if (findChar(word, num, coords, ch.getFirst(), ch.getSecond(), lines))
                    return true;
            }
        return false;
    }
}