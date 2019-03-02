import java.util.Scanner;

public class Lab4 {
    private static String str;
    private static double e = Math.pow(10, -6);

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите номер функции, которую хотите проинтегрировать:\n" +
                "1) f(x)=(sin(x)+1)/2\n" +
                "2) f(x)=ln(|x|)\n" +
                "3) f(x)=e^x\n" +
                "4) f(x)=x^4-x^3+15x^2-38x+0.6");
        str = scanner.nextLine();

        switch (str) {
            case "1":
            case "2":
            case "3":
            case "4":
                break;
            default:
                System.out.println("Вы ввели что-то не то :(");
        }

        System.out.println("Введите номер метода для вычисления интеграла или '0' чтобы вывести все методы для сравнения:\n" +
                "1) Метод прямоугольников(левый)\n" +
                "2) Метод прямоугольников(правый)\n" +
                "3) Метод прямоугольников(средний)\n" +
                "4) Метод трапеций\n" +
                "5) Метод Симпсона\n");
        String meth = scanner.nextLine();
        System.out.println("Введите интервал для расчета");
        String ent = scanner.nextLine();
        Double[] dot = Lab1.getAB(ent);
        Double a = dot[0];
        Double b = dot[1];
        int n;
        double I0, I1;
        switch (meth) {
            case "1":
                n = 4;
                I0 = rectL(a, b, n);
                n = n * 2;
                I1 = rectL(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = rectL(a, b, n);
                }
                System.out.println("Методом левого прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);
                break;
            case "2":
                n = 4;
                I0 = rectR(a, b, n);
                n = n * 2;
                I1 = rectR(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = rectR(a, b, n);
                }
                System.out.println("Методом левого прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);
                break;
            case "3":
                n = 4;
                I0 = rectM(a, b, n);
                n = n * 2;
                I1 = rectM(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = rectM(a, b, n);
                }
                System.out.println("Методом левого прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);
                break;
            case "4":
                n = 4;
                I0 = trap(a, b, n);
                n = n * 2;
                I1 = trap(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = trap(a, b, n);
                }
                System.out.println("Методом левого прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);
                break;
            case "5":
                n = 4;
                I0 = simp(a, b, n);
                n = n * 2;
                I1 = simp(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = simp(a, b, n);
                }
                System.out.println("Методом левого прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);
                break;
            case "0":
                n = 4;
                I0 = rectL(a, b, n);
                n = n * 2;
                I1 = rectL(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = rectL(a, b, n);
                }
                System.out.println("Методом левого прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);

                n = 4;
                I0 = rectR(a, b, n);
                n = n * 2;
                I1 = rectR(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = rectR(a, b, n);
                }
                System.out.println("Методом правого прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);

                n = 4;
                I0 = rectM(a, b, n);
                n = n * 2;
                I1 = rectM(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = rectM(a, b, n);
                }
                System.out.println("Методом среднего прямоугольника для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);

                n = 4;
                I0 = trap(a, b, n);
                n = n * 2;
                I1 = trap(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = trap(a, b, n);
                }
                System.out.println("Методом трапеции для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);

                n = 4;
                I0 = simp(a, b, n);
                n = n * 2;
                I1 = simp(a, b, n);
                while (Math.abs(I1 - I0) > e) {
                    n = n * 2;
                    I0=I1;
                    I1 = simp(a, b, n);
                }
                System.out.println("Методом Симпсона для выбранной функции получим:\n" +
                        "n=" + n + "\n" +
                        "Результат: " + I1);
                break;

            default:
                System.out.println("Вы ввели что-то не то :(");

        }
    }

    private static double simp(Double a, Double b, int n) {
        double sum = 0;
        for (int i = 0; i < n/2; i++) sum += (b-a)/3/n*(call(a+(b-a)/n*(2*i))+4*call(a+(b-a)/n*(2*i+1))+call(a+(b-a)/n*(2*i+2)));
        return sum;
    }

    private static double trap(Double a, Double b, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) sum += (b-a)/n*(call(a+(b-a)/n*i)+call(a+(b-a)/n*(i+1)))/2;
        return sum;
    }

    private static double rectM(Double a, Double b, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) sum += ((b - a) / n) * call(a + ((b - a) / n) * (2*i+1)/2);
        return sum;
    }

    private static double rectR(Double a, Double b, int n) {
        double sum = 0;
        for (int i = 1; i <= n; i++) sum += ((b - a) / n) * call(a + ((b - a) / n) * i);
        return sum;
    }

    private static double rectL(Double a, Double b, int n) {
        double sum = 0;
        for (int i = 0; i < n; i++) sum += ((b - a) / n) * call(a + ((b - a) / n) * i);
        return sum;
    }

    private static Double call(double x) {
        switch (str) {
            case "1":
                return f1(x);
            case "2":
                return f2(x);
            case "3":
                return f3(x);
            case "4":
                return f4(x);
            default:
                System.out.println("Выбрана неверная функция");
                return null;
        }
    }

    private static Double f1(double x) {
        return (Math.sin(x) + 1) / 2;
    }

    private static Double f2(double x) {
        return Math.log(Math.abs(x));
    }

    private static Double f3(double x) {
        return Math.pow(Math.E, x);
    }

    private static Double f4(double x) {
        return Math.pow(x, 4) - Math.pow(x, 3) + 15 * Math.pow(x, 2) - 38 * x + 0.6;
    }


}
