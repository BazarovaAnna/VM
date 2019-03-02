import org.knowm.xchart.*;

import java.util.Scanner;
import java.io.*;

/**
 * ����� ������ ����������� ��������� � �������� ���������
 */

public class Lab1 {
    static private int i1, i2;

    public static void main(String args[]) {
        try {
            FileWriter writer = new FileWriter("out.txt");
            double[] xData = new double[121];
            double[] yData = new double[121];
            int c = 0;
            for (double i = -12; i <= 12; i += 0.2) {
                xData[c] = i;
                yData[c] = f(i);
                c += 1;
            }
            XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
            chart.getStyler().setMarkerSize(1);
            chart.getStyler().setYAxisMax(6.);
            chart.getStyler().setYAxisMin(-18.);
            chart.getStyler().setXAxisTickMarkSpacingHint(2);
            chart.addSeries("function", xData, yData);
            new SwingWrapper<>(chart).displayChart();

            System.out.println("���� ���������: x^3+2.84*x^2-5.606*x-14.766");
            writer.write("���� ���������: x^3+2.84*x^2-5.606*x-14.766\n");

            //System.out.println("�������� ��������� ��������� �������� (�������):\n ��� x1: a0=-4, b0=-3 \n ��� x2: a0=-3, b0=-2 \n ��� x3: a0=2, b0=3");

            Scanner scanner = new Scanner(System.in);
            Double[] mas1;
            Double[] mas2;
            System.out.println("������� ����������� �����������");
            Double E = Double.parseDouble(scanner.nextLine());
            writer.write("���� �����������: " + E + "\n");
            System.out.println("I) ����� ����������� �������");
            System.out.println("������� ��������� ��������� �������� ��� ����� (����� ������, � ���������� ����, ��������� ������)");
            do {
                String forX1 = scanner.nextLine();
                mas1 = getAB(forX1);
            } while (!check(mas1));


            System.out.println("�������� ������������� ��������, ������ ������ ������ � ��������� " + E + "\n������� ����������� �������");
            double l = findSolution(mas1, E);
            System.out.println("��� ������: " + l);
            writer.write("��� ������: " + l + " ������� ����������� �������\n�������� �������: " + f(l) + "\n���������� ��������: " + i1);
            System.out.println("�������� �������: " + f(l));
            System.out.println();
            System.out.println("II) ����� �������");
            System.out.println("������� ��������� ��������� �������� ��� ����� (����� ������, � ���������� ����, ��������� ������)");
            do {
                String forX2 = scanner.nextLine();
                mas2 = getAB(forX2);
            } while (!check1(mas2));
            System.out.println("�������� ������������� ��������");
            double pr;//��������� ��������\
            if (f(mas2[0]) * ((3 * 2) * mas2[0] + 2 * 2.84) > 0) {
                pr = mas2[0];
            } else {
                pr = mas2[1];
            }
            System.out.println("��������� ����������� " + pr);

            System.out.println("������ ������ ������ � ��������� " + E + "\n������� �������");
            double x = findNewton(E, pr);
            writer.write("��� ������: " + x + " ������� �������\n�������� �������: " + f(x) + "\n���������� ��������: " + i2);
            System.out.println("��� ������: " + x);
            System.out.println("�������� �������: " + f(x));
            writer.close();
        } catch (IOException e) {
            System.out.println("�������� � ������-�������");
        }
    }

    private static Double f(double x) {
        return Math.pow(x, 3) + 2.84 * Math.pow(x, 2) - 5.606 * x - 14.766;
    }

    private static Double fS(double x) {
        return 3 * Math.pow(x, 2) + 2 * 2.84 * x - 5.606;
    }

    private static boolean prMenZn(double a, double b) {
        //return 3*Math.pow(x,2)+2*2.84*x-5.606;
        if (a > b) {
            double c = a;
            a = b;
            b = c;
        }
        double d = Math.pow(2 * 2.84, 2) - 4 * 3 * (-5.606);
        if (d < 0) {
            return false;
        } else {
            double x1 = (-2 * 2.84 - Math.sqrt(d)) / (2 * 3);
            double x2 = (-2 * 2.84 + Math.sqrt(d)) / (2 * 3);
            return (a < x1 && x1 < b) || (a < x2 && x2 < b);
        }
    }

    static Double[] getAB(String forX) {
        String[] ss = forX.split(" ");
        Double[] ret = new Double[ss.length];
        for (int i = 0; i < ss.length; i++) {
            ret[i] = Double.parseDouble(ss[i]);
        }
        return ret;
    }

    private static boolean check(Double[] mas) {
        if (mas.length != 2) {
            System.out.println("���������� ������� ��� �����, �� ������������� �������� �����");
            return false;
        }
        if (mas[0].equals(mas[1])) {
            System.out.println("������� ��������, ����� �� ������ ���� ����� ���� �����");
            return false;
        }
        if (f(mas[0]) * f(mas[1]) > 0) {
            System.out.println("������� �� ������ ������� ������ ��������� ��������, ��������� �� �����");
            System.out.println("�������� � ������ �����: " + f(mas[0]));
            System.out.println("�������� �� ������ �����: " + f(mas[1]));
            return false;
        }
        if (prMenZn(mas[0], mas[1])) {
            System.out.println("� ����� ��������� ����� ���������� ��������� ������, ���������� �����");
            return false;
        }
        return true;
    }

    private static boolean pr2MenZn(double a, double b) {
        //return 3*2*x+2*2.84;
        if (a > b) {
            double c = a;
            a = b;
            b = c;
        }
        double x = -2 * 2.84 / (3 * 2);
        return !(a < x) || !(x < b);
    }

    private static boolean check1(Double[] mas) {
        if (mas.length != 2) {
            System.out.println("���������� ������� ��� �����, �� ������������� �������� �����");
            return false;
        }
        if (mas[0].equals(mas[1])) {
            System.out.println("������� ��������, ����� �� ������ ���� ����� ���� �����");
            return false;
        }
        if (f(mas[0]) * f(mas[1]) > 0) {
            System.out.println("������� �� ������ ������� ������ ��������� ��������, ��������� �� �����");
            System.out.println("�������� � ������ �����: " + f(mas[0]));
            System.out.println("�������� �� ������ �����: " + f(mas[1]));
            return false;
        }
        if (prMenZn(mas[0], mas[1])) {
            System.out.println("� ����� ��������� f'(x) ������ ��������� ����");
            return false;
        }
        if (!pr2MenZn(mas[0], mas[1])) {
            System.out.println("� ����� ��������� f''(x) ������ ��������� ����");
            return false;
        }
        return true;
    }

    private static double findSolution(Double[] mas, double e) {
        double a, b, c;
        if (f(mas[0]) > 0) {
            a = mas[1];
            b = mas[0];
        } else {
            a = mas[0];
            b = mas[1];
        }
        int i = 0;
        do {
            i = i + 1;
            //System.out.print(i+") a="+a+" b="+b);
            c = (a + b) / 2;
            //System.out.print(" x="+c+" f(a)="+f(a)+" f(b)="+f(b)+" f(x)="+f(c)+" |a-b|="+Math.abs(b-a));
            //System.out.println();
            if (f(c) > 0) {
                b = c;
            } else if (f(c) < 0) {
                a = c;
            } else {
                return c;
            }
        } while (Math.abs(b - a) > e || Math.abs(f(c)) > e);
        System.out.println("���������� �������� " + i);
        i1 = i;
        return (a + b) / 2;
    }

    private static double findNewton(double e, double pr) {
        double x0 = pr;
        double x;
        double c;
        int i = 0;
        do {
            i = i + 1;
            x = x0 - (f(x0) / fS(x0));
            //System.out.println(i+") x"+i+"="+x0+" f(x)="+f(x0)+" f'(x)="+fS(x0)+" x"+i+"+1="+x+"|x"+i+"-x"+i+"+1|="+Math.abs(x-x0));
            c = x0;
            x0 = x;
        } while (Math.abs(x - c) > e || Math.abs(f(x)) > e);
        System.out.println("���������� �������� " + i);
        i2 = i;
        return x0;
    }
}