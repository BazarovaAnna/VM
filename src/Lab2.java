import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.*;

/**
 * Аппроксимация функции по точкам (График какой зависимости больше всего подходит к этому набору точек)
 */
public class Lab2 {
    private static int num;

    public static void main(String args[]) {
        try {
            System.out.println("Введите количество точек для аппроксимации или их отрицательное количество, чтобы считать из файла");
            Scanner scanner = new Scanner(System.in);
            Scanner reader = new Scanner(Paths.get("in.txt"));
            FileWriter writer = new FileWriter("out.txt");
            ArrayList<Double> masX = new ArrayList<>();
            ArrayList<Double> masY = new ArrayList<>();
            num = Integer.parseInt(scanner.nextLine());
            if (num > 0) {
                System.out.println("Введите " + num + " точек (через Enter) в виде 'x y', где x и y соответствующие координаты");
                for (int i = 0; i < num; i++) {
                    String str = scanner.nextLine();
                    Double[] dot = Lab1.getAB(str);
                    masX.add(dot[0]);
                    masY.add(dot[1]);
                }

            } else if (num < 0) {
                num = (-1) * num;
                for (int i = 0; i < num; i++) {
                    String str = reader.nextLine();
                    Double[] dot = Lab1.getAB(str);
                    masX.add(dot[0]);
                    masY.add(dot[1]);
                }
            }
            //1) let it be linear
            Double[] alin = getALin(masX, masY);//koeffs for linear
            System.out.println("После подсчетов по линейной функции получим:");
            System.out.println("y=a*x+b");
            double S = 0;
            for (int i = 0; i < num; i++) {
                S += Math.pow(fLin(masX.get(i), alin[0], alin[1]) - masY.get(i), 2);
                System.out.println("x=" + masX.get(i) + " y=" + masY.get(i) + " f=" + fLin(masX.get(i), alin[0], alin[1]));
            }
            double d1 = Math.sqrt(S / num);

            System.out.println("S=" + S);
            System.out.println("delta=" + d1);
            System.out.println("a=" + alin[0] + " b=" + alin[1]);
            writer.write("Линейная:\nS=" + S + "\ndelta=" + d1 + "\na=" + alin[0] + " b=" + alin[1] + "\n\n");
            //2) let it be step
            Double[] astep = getALin(getLn(masX), getLn(masY));
            Double c;
            c = astep[1];
            astep[1] = astep[0];
            astep[0] = Math.pow(Math.E, c);
            System.out.println("После подсчетов по степенной функции получим:");
            System.out.println("y=a*x^b");
            S = 0;
            for (int i = 0; i < num; i++) {
                S += Math.pow(fStep(masX.get(i), astep[0], astep[1]) - masY.get(i), 2);
                System.out.println("x=" + masX.get(i) + " y=" + masY.get(i) + " f=" + fStep(masX.get(i), astep[0], astep[1]));
            }
            double d2 = Math.sqrt(S / num);

            System.out.println("S=" + S);
            System.out.println("delta=" + d2);
            System.out.println("a=" + astep[0] + " b=" + astep[1]);
            writer.write("Степенная:\nS=" + S + "\ndelta=" + d2 + "\na=" + astep[0] + " b=" + astep[1] + "\n\n");

            //3) let it be pokaz
            Double[] apok = getALin(masX, getLn(masY));
            c = apok[1];
            apok[1] = apok[0];
            apok[0] = Math.pow(Math.E, c);
            System.out.println("После подсчетов по показательной функции получим:");
            System.out.println("y=a*e^(b*x)");
            S = 0;
            for (int i = 0; i < num; i++) {
                S += Math.pow(fPok(masX.get(i), apok[0], apok[1]) - masY.get(i), 2);
                System.out.println("x=" + masX.get(i) + " y=" + masY.get(i) + " f=" + fPok(masX.get(i), apok[0], apok[1]));
            }
            double d3 = Math.sqrt(S / num);

            System.out.println("S=" + S);
            System.out.println("delta=" + d3);
            System.out.println("a=" + apok[0] + " b=" + apok[1]);
            writer.write("Показательная:\nS=" + S + "\ndelta=" + d3 + "\na=" + apok[0] + " b=" + apok[1] + "\n\n");

            //4) let it be log
            Double[] alog = getALin(getLn(masX), masY);
            c = alog[1];
            alog[1] = alog[0];
            alog[0] = c;
            System.out.println("После подсчетов по логарифмической функции получим:");
            System.out.println("y=a+b*ln(x)");
            S = 0;
            for (int i = 0; i < num; i++) {
                S += Math.pow(fLog(masX.get(i), alog[0], alog[1]) - masY.get(i), 2);
                System.out.println("x=" + masX.get(i) + " y=" + masY.get(i) + " f=" + fLog(masX.get(i), alog[0], alog[1]));
            }
            double d4 = Math.sqrt(S / num);

            System.out.println("S=" + S);
            System.out.println("delta=" + d4);
            System.out.println("a=" + alog[0] + " b=" + alog[1]);
            writer.write("Логарифмическая:\nS=" + S + "\ndelta=" + d4 + "\na=" + alog[0] + " b=" + alog[1] + "\n\n");

            //5) then polynom
            Double[] apol = getAPol(masX, masY);

            System.out.println("После подсчетов по полиномной функции получим:");
            System.out.println("y=a+b*x+c*x^2");
            S = 0;
            for (int i = 0; i < num; i++) {
                S += Math.pow(fPol(masX.get(i), apol[0], apol[1], apol[2]) - masY.get(i), 2);
                System.out.println("x=" + masX.get(i) + " y=" + masY.get(i) + " f=" + fPol(masX.get(i), apol[0], apol[1], apol[2]));
            }
            double d5 = Math.sqrt(S / num);

            System.out.println("S=" + S);
            System.out.println("delta=" + d5);
            System.out.println("a=" + apol[0] + " b=" + apol[1] + " c=" + apol[2]);
            writer.write("Полиномная:\nS=" + S + "\ndelta=" + d5 + "\na=" + apol[0] + " b=" + apol[1] + " c=" + apol[2] + "\n\n");

            if (d1 <= d2 && d1 <= d3 && d1 <= d4 && d1 <= d5) {
                System.out.println("Дельта линейной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:");
                writer.write("Дельта линейной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:\n" + "y=" + alin[0] + "*x+" + alin[1]);
                System.out.println("y=" + alin[0] + "*x+" + alin[1]);
            }
            if (d2 <= d1 && d2 <= d3 && d2 <= d4 && d2 <= d5) {
                System.out.println("Дельта степенной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:");
                writer.write("Дельта степенной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:\n" + "y=" + astep[0] + "*x^" + astep[1]);
                System.out.println("y=" + astep[0] + "*x^" + astep[1]);
            }
            if (d3 <= d1 && d3 <= d2 && d3 <= d4 && d3 <= d5) {
                System.out.println("Дельта показательной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:");
                writer.write("Дельта показательной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:\n" + "y=" + apok[0] + "*e^(" + apok[1] + "*x)");
                System.out.println("y=" + apok[0] + "*e^(" + apok[1] + "*x)");
            }
            if (d4 <= d1 && d4 <= d2 && d4 <= d3 && d4 <= d5) {
                System.out.println("Дельта логарифмической функции - наименьшая, значит заданная функция точнее всего описывается уравнением:");
                writer.write("Дельта логарифмической функции - наименьшая, значит заданная функция точнее всего описывается уравнением:\n" + "y=" + alog[0] + "+" + alog[1] + "*ln(x)");
                System.out.println("y=" + alog[0] + "+" + alog[1] + "*ln(x)");
            }
            if (d5 <= d1 && d5 <= d2 && d5 <= d3 && d5 <= d4) {
                System.out.println("Дельта полиномной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:");
                writer.write("Дельта полиномной функции - наименьшая, значит заданная функция точнее всего описывается уравнением:\n" + "y=" + apol[0] + "+" + apol[1] + "*x+" + apol[2] + "*x^2");
                System.out.println("y=" + apol[0] + "+" + apol[1] + "*x+" + apol[2] + "*x^2");
            }
            writer.close();
            int max = (int) Math.round(masX.get(0));
            double maxy = 0;
            double maxx = 0;
            double minx = 0;
            double miny = 0;
            for (int i = 0; i < num; i++) {
                if ((int) Math.round(masX.get(i)) > max) {
                    max = (int) Math.round(masX.get(i));
                }
                if ((int) Math.round(masY.get(i)) > max) {
                    max = (int) Math.round(masY.get(i));
                }
                if (masY.get(i) > maxy) {
                    maxy = masY.get(i);
                }
                if (masY.get(i) < miny) {
                    miny = masY.get(i);
                }
                if (masX.get(i) > maxx) {
                    maxx = masX.get(i);
                }
                if (masX.get(i) < minx) {
                    minx = masX.get(i);
                }
            }
            double[] xData = new double[max * 10];
            double[] yData1 = new double[max * 10];
            double[] yData2 = new double[max * 10];
            double[] yData3 = new double[max * 10];
            double[] yData4 = new double[max * 10];
            double[] yData5 = new double[max * 10];
            int l = 0;
            for (double i = 0.1; i <= max; i += 0.1) {
                xData[l] = i;
                yData1[l] = fLin(i, alin[0], alin[1]);
                yData2[l] = fPol(i, apol[0], apol[1], apol[2]);
                yData3[l] = fStep(i, astep[0], astep[1]);
                yData4[l] = fLog(i, alog[0], alog[1]);
                yData5[l] = fPok(i, apok[0], apok[1]);
                l += 1;
            }
            //costil dlya grafikov
            boolean f = false;
            while (!f) {
                f = true;
                if (xData[xData.length - 1] == 0 && (yData1[xData.length - 1] == 0 || yData2[xData.length - 1] == 0 || yData3[xData.length - 1] == 0 || yData4[xData.length - 1] == 0 || yData5[xData.length - 1] == 0)) {
                    f = false;
                    double[] prt;
                    prt = xData;
                    int len = xData.length - 1;
                    xData = new double[len];
                    System.arraycopy(prt, 0, xData, 0, len);
                    prt = yData1;
                    yData1 = new double[len];
                    System.arraycopy(prt, 0, yData1, 0, len);
                    prt = yData2;
                    yData2 = new double[len];
                    System.arraycopy(prt, 0, yData2, 0, len);
                    prt = yData3;
                    yData3 = new double[len];
                    System.arraycopy(prt, 0, yData3, 0, len);
                    prt = yData4;
                    yData4 = new double[len];
                    System.arraycopy(prt, 0, yData4, 0, len);
                    prt = yData5;
                    yData5 = new double[len];
                    System.arraycopy(prt, 0, yData5, 0, len);
                }
            }
            List<XYChart> charts = new ArrayList<>();
            XYChart chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
            chart.getStyler().setMarkerSize(1);
            chart.getStyler().setYAxisMax(maxy + 1);
            chart.getStyler().setYAxisMin(miny);
            chart.getStyler().setXAxisMax(maxx + 1);
            chart.getStyler().setXAxisMin(minx);
            chart.getStyler().setXAxisTickMarkSpacingHint(1);
            chart.addSeries("points", masX, masY);
            chart.addSeries("quadratic function", xData, yData2);
            charts.add(chart);


            chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
            chart.getStyler().setMarkerSize(1);
            chart.getStyler().setYAxisMax(maxy + 1);
            chart.getStyler().setYAxisMin(miny);
            chart.getStyler().setXAxisMax(maxx + 1);
            chart.getStyler().setXAxisMin(minx);
            chart.getStyler().setXAxisTickMarkSpacingHint(1);
            chart.addSeries("points", masX, masY);
            chart.addSeries("linear function", xData, yData1);

            charts.add(chart);

            chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
            chart.getStyler().setMarkerSize(1);
            chart.getStyler().setYAxisMax(maxy + 1);
            chart.getStyler().setYAxisMin(miny);
            chart.getStyler().setXAxisMax(maxx + 1);
            chart.getStyler().setXAxisMin(minx);
            chart.getStyler().setXAxisTickMarkSpacingHint(1);
            chart.addSeries("points", masX, masY);
            chart.addSeries("power function", xData, yData3);

            charts.add(chart);

            chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
            chart.getStyler().setMarkerSize(1);
            chart.getStyler().setYAxisMax(maxy + 1);
            chart.getStyler().setYAxisMin(miny);
            chart.getStyler().setXAxisMax(maxx + 1);
            chart.getStyler().setXAxisMin(minx);
            chart.getStyler().setXAxisTickMarkSpacingHint(1);
            chart.addSeries("points", masX, masY);
            chart.addSeries("logarithmic function", xData, yData4);

            charts.add(chart);

            chart = new XYChartBuilder().xAxisTitle("X").yAxisTitle("Y").width(600).height(400).build();
            chart.getStyler().setMarkerSize(1);
            chart.getStyler().setYAxisMax(maxy + 1);
            chart.getStyler().setYAxisMin(miny);
            chart.getStyler().setXAxisMax(maxx + 1);
            chart.getStyler().setXAxisMin(minx);
            chart.getStyler().setXAxisTickMarkSpacingHint(1);
            chart.addSeries("points", masX, masY);
            chart.addSeries("exponential function", xData, yData5);

            charts.add(chart);
            new SwingWrapper<>(charts).displayChartMatrix();

        } catch (IOException e) {
            System.out.println("Проблемы с вводом-выводом");
        }
    }

    private static double fLin(double x, double a0, double a1) {
        //y=a0*x+a1;
        return a0 * x + a1;
    }

    private static double fPol(double x, double a0, double a1, double a2) {
        //y=a0+a1*x+a2*x^2;
        return a0 + a1 * x + a2 * Math.pow(x, 2);
    }

    private static double fStep(double x, double a0, double a1) {
        //y=a0*x^a1;
        return a0 * Math.pow(x, a1);
    }

    private static double fLog(double x, double a0, double a1) {
        //y=a0+a1*ln(x);
        return a0 + a1 * Math.log(x);
    }

    private static double fPok(double x, double a0, double a1) {
        //y=a0*e^(a1*x);
        return a0 * Math.pow(Math.E, a1 * x);
    }

    private static Double[] getALin(ArrayList<Double> masX, ArrayList<Double> masY) {
        double SX = 0;
        double SXX = 0;
        double SY = 0;
        double SXY = 0;
        for (int i = 0; i < num; i++) {
            SXX += Math.pow(masX.get(i), 2);
            SX += masX.get(i);
            SXY += masX.get(i) * masY.get(i);
            SY += masY.get(i);
        }
        Double[] ret = new Double[2];
        ret[0] = (SXY * num - SX * SY) / (SXX * num - SX * SX);
        ret[1] = (SXX * SY - SX * SXY) / (SXX * num - SX * SX);

        return ret;
    }

    private static Double[] getAPol(ArrayList<Double> masX, ArrayList<Double> masY) {
        double a = 0;//SX
        double b = 0;//SXX
        double c = 0;//SXXX
        double d = 0;//SXXXX
        double f = 0;//SY
        double g = 0;//SXY
        double e = 0;//SXXY
        Double[] ret = new Double[3];
        for (int i = 0; i < num; i++) {
            b += Math.pow(masX.get(i), 2);
            a += masX.get(i);
            g += masX.get(i) * masY.get(i);
            f += masY.get(i);
            c += Math.pow(masX.get(i), 3);
            d += Math.pow(masX.get(i), 4);
            e += Math.pow(masX.get(i), 2) * masY.get(i);
        }
        ret[0] = (a * c * e + b * g * c + f * b * d - f * c * c - b * b * e - a * g * d) / (num * b * d + a * c * b * 2 - b * b * b - a * a * d - num * c * c);
        ret[1] = (num * g * d + f * c * b + b * a * e - b * g * b - f * a * d - num * c * e) / (num * b * d + a * c * b * 2 - b * b * b - a * a * d - num * c * c);
        ret[2] = (num * b * e + a * g * b + f * a * c - f * b * b - a * a * e - num * g * c) / (num * b * d + a * c * b * 2 - b * b * b - a * a * d - num * c * c);
        return ret;
    }

    private static ArrayList<Double> getLn(ArrayList<Double> masA) {
        ArrayList<Double> ret = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            ret.add(Math.log(masA.get(i)));
        }
        return ret;
    }
}
