import java.util.Stack;

public class main {
    public static void main(String[] args) {
        System.out.println(test("qwe2[pro4[GE]]3[zx]we"));
    }

    public static String test(String str) {
        StringBuilder sb = new StringBuilder(); //Использую билдер из-за большого количества изменений
        Stack<Integer> st = new Stack<Integer>();
        String ans = "";
        if (str.indexOf('[') - 1 != 0) {
            sb.append(str, 0, str.indexOf('[') - 1); //Проверка что до первого повторения есть символы
        }
        for (int i = 0; i < str.length(); i++) { //Проход по всей строке
            switch (str.charAt(i)) {
                case '[':
                    st.push(i); //Пушим индекс скобки в стек
                    break;
                case ']':
                    if (st.size() > 1) { //Если в стеке больше одного элемента
                        int b2 = st.pop(); //Индекс второй скобки
                        int b1 = st.pop(); //Индекс первой скобки
                        int p = i + 1;
                        while (str.charAt(p) != ']') {
                            p++;   //Находим индекс второй закрывающей скобки
                        }
                        for (int j = 0; j < str.charAt(b1 - 1) - '0'; j++) { //Проходим по основному повторению
                            if (b2 - b1 > 1) { //Проверяем что перед основным и вложенным повторением есть символы
                                sb.append(str, b1 + 1, b2 - 1);
                            }
                            for (int k = 0; k < str.charAt(b2 - 1) - '0'; k++) {
                                sb.append(str, b2 + 1, i); //Добавляем символы из вложенного повторения
                            }
                            sb.append(str, i + 1, p); //Добавляем символы после вложенного повторения
                        }
                        i = p; //Устанавливаем цикл на конец основного повторения, что бы избежать двойных выводов
                    } else {
                        int bord = st.pop();
                        for (int j = 0; j < str.charAt(bord - 1) - '0'; j++) {
                            sb.append(str, bord + 1, i); //Если нет вложенных повторений, то просто выводим основное
                        }
                    }
                    break;
            }
        }
        if (str.lastIndexOf(']') != str.length()) {
            sb.append(str, str.lastIndexOf(']') + 1, str.length()); //Проверка что после последнего повторения нет символов
        }
        ans = sb.toString();
        return ans;
    }
}

