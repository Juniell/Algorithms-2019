package lesson1;


import kotlin.NotImplementedError;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SuppressWarnings("unused")
public class JavaTasks {
    /**
     * Сортировка времён
     *
     * Простая
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле с именем inputName содержатся моменты времени в формате ЧЧ:ММ:СС AM/PM,
     * каждый на отдельной строке. См. статью википедии "12-часовой формат времени".
     *
     * Пример:
     *
     * 01:15:19 PM
     * 07:26:57 AM
     * 10:00:03 AM
     * 07:56:14 PM
     * 01:15:19 PM
     * 12:40:31 AM
     *
     * Отсортировать моменты времени по возрастанию и вывести их в выходной файл с именем outputName,
     * сохраняя формат ЧЧ:ММ:СС AM/PM. Одинаковые моменты времени выводить друг за другом. Пример:
     *
     * 12:40:31 AM
     * 07:26:57 AM
     * 10:00:03 AM
     * 01:15:19 PM
     * 01:15:19 PM
     * 07:56:14 PM
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */

    static public void sortTimes(String inputName, String outputName) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(inputName));
        String line;
        List<String> dates = new ArrayList<>();

        while ((line = text.readLine()) != null) {
            dates.add(line);
        }

        dates.sort(new Comparator<String>() {
            DateFormat f = new SimpleDateFormat("hh:mm:ss a", Locale.US);
            @Override
            public int compare(String o1, String o2) {
                try {
                    return f.parse(o1).compareTo(f.parse(o2));
                } catch (ParseException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        });

        try (FileWriter writer = new FileWriter(outputName, false)) {
            for (String date : dates)
                writer.write(date + "\n");
        }
    }

    /**
     * Сортировка адресов
     *
     * Средняя
     *
     * Во входном файле с именем inputName содержатся фамилии и имена жителей города с указанием улицы и номера дома,
     * где они прописаны. Пример:
     *
     * Петров Иван - Железнодорожная 3
     * Сидоров Петр - Садовая 5
     * Иванов Алексей - Железнодорожная 7
     * Сидорова Мария - Садовая 5
     * Иванов Михаил - Железнодорожная 7
     *
     * Людей в городе может быть до миллиона.
     *
     * Вывести записи в выходной файл outputName,
     * упорядоченными по названию улицы (по алфавиту) и номеру дома (по возрастанию).
     * Людей, живущих в одном доме, выводить через запятую по алфавиту (вначале по фамилии, потом по имени). Пример:
     *
     * Железнодорожная 3 - Петров Иван
     * Железнодорожная 7 - Иванов Алексей, Иванов Михаил
     * Садовая 5 - Сидоров Петр, Сидорова Мария
     *
     * В случае обнаружения неверного формата файла бросить любое исключение.
     */
    static public void sortAddresses(String inputName, String outputName) {
        throw new NotImplementedError();
    }

    /**
     * Сортировка температур
     *
     * Средняя
     * (Модифицированная задача с сайта acmp.ru)
     *
     * Во входном файле заданы температуры различных участков абстрактной планеты с точностью до десятых градуса.
     * Температуры могут изменяться в диапазоне от -273.0 до +500.0.
     * Например:
     *
     * 24.7
     * -12.6
     * 121.3
     * -98.4
     * 99.5
     * -12.6
     * 11.0
     *
     * Количество строк в файле может достигать ста миллионов.
     * Вывести строки в выходной файл, отсортировав их по возрастанию температуры.
     * Повторяющиеся строки сохранить. Например:
     *
     * -98.4
     * -12.6
     * -12.6
     * 11.0
     * 24.7
     * 99.5
     * 121.3
     */
    static public void sortTemperatures(String inputName, String outputName) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(inputName));
        String line;
        Map<Double, Integer> temp = new TreeMap<>();

        while ((line = text.readLine()) != null) {
            Double key = Double.parseDouble(line.trim());
            if (key < -273.0 || key > 500.0)
                throw new IllegalArgumentException();   // проверяем на диапазон
            if (temp.containsKey(key))                // если ключ есть в мапе, то
                temp.put(key, temp.get(key) + 1);     // добавляем его с новым значением
            else
                temp.put(key, 1);
        }
        try (FileWriter writer = new FileWriter(outputName, false)) {
            for (Map.Entry<Double, Integer> pair : temp.entrySet())
                for (int i = 1; i <= pair.getValue(); i++)
                    writer.write(pair.getKey() + "\n");
        }
    }

    /**
     * Сортировка последовательности
     *
     * Средняя
     * (Задача взята с сайта acmp.ru)
     *
     * В файле задана последовательность из n целых положительных чисел, каждое в своей строке, например:
     *
     * 1
     * 2
     * 3
     * 2
     * 3
     * 1
     * 2
     *
     * Необходимо найти число, которое встречается в этой последовательности наибольшее количество раз,
     * а если таких чисел несколько, то найти минимальное из них,
     * и после этого переместить все такие числа в конец заданной последовательности.
     * Порядок расположения остальных чисел должен остаться без изменения.
     *
     * 1
     * 3
     * 3
     * 1
     * 2
     * 2
     * 2
     */
    static public void sortSequence(String inputName, String outputName) throws IOException {
        BufferedReader text = new BufferedReader(new FileReader(inputName));
        String line;
        List<Integer> numList = new ArrayList<>();
        Map<Integer, Integer> numMap = new HashMap<>();
        //список, чтобы хранить последовательность, мапа - искать нужное число

        while ((line = text.readLine()) != null) {
            Integer num = Integer.parseInt(line.trim());
            numList.add(num);                           // Добавляем в список, чтобы сохранить последовательность
            if (numMap.containsKey(num))                // Если есть в мапе,
                numMap.put(num, numMap.get(num) + 1);   // добавляем с новым значением
            else                                        // если нет,
                numMap.put(num, 1);                     // просто добавляем
        }

        // Найдём максимальное число влохждений
        Integer max = (new TreeSet<>(numMap.values())).last();  // treeSet отсортирует по убыванию, и выдаст самое большое число вхождений

        // Найдём число / числа с максимальным вхождением
        TreeSet<Integer> keys = new TreeSet<>();                // опять сортируем
        for (Map.Entry<Integer, Integer> pair : numMap.entrySet()) {
            if (pair.getValue().equals(max))
                keys.add(pair.getKey());
        }

        // Найдём финальное нужное число. Если оно одно, то будет первым. А если их несколько, то самое маленькое из них
        // тоже будет первым
        Integer num = keys.first();

        // Вывод
        try (FileWriter writer = new FileWriter(outputName, false)) {
           for (Integer number : numList) {
               if (!number.equals(num))
                   writer.write(number + "\n");
           }
           for (int i = 1; i <= max; i++)
               writer.write(num + "\n");
        }
    }


    /**
     * Соединить два отсортированных массива в один
     *
     * Простая
     *
     * Задан отсортированный массив first и второй массив second,
     * первые first.size ячеек которого содержат null, а остальные ячейки также отсортированы.
     * Соединить оба массива в массиве second так, чтобы он оказался отсортирован. Пример:
     *
     * first = [4 9 15 20 28]
     * second = [null null null null null 1 3 9 13 18 23]
     *
     * Результат: second = [1 3 4 9 9 13 15 20 23 28]
     */
    static <T extends Comparable<T>> void mergeArrays(T[] first, T[] second) {
        throw new NotImplementedError();
    }
}
