import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * ������������ �������
 */
public class Lab3 {
    private static int num, num1;

    public static void main(String args[]) {
        try {
            System.out.println("��� ���������� ������������� �������� � ������� ���������� �������� � ���������������� ������� ������� ��� ���������������� �����: \n������� ���������� ����� ��� �� ������������� ����������, ����� ������� �� �����");
            Scanner scanner = new Scanner(System.in);
            Scanner reader = new Scanner(Paths.get("in.txt"));
            FileWriter writer = new FileWriter("out.txt");
            ArrayList<Double> masX = new ArrayList<>();
            ArrayList<Double> masY = new ArrayList<>();
            num = Integer.parseInt(scanner.nextLine());
            Double x1, x2, x3, x4;
            if (num > 0) {
                System.out.println("������� " + num + " ����� (����� Enter) � ���� 'x y', ��� x � y ��������������� ����������");
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
            System.out.println("��� ���������� �������� �������, ��������� ������ ��� ������ ���������������� ������� �������: \n������� ���������� ����� ��� �� ������������� ����������, ����� ������� �� �����");
            Scanner reader1 = new Scanner(Paths.get("in2.txt"));
            ArrayList<Double> masX1 = new ArrayList<>();
            ArrayList<Double> masY1 = new ArrayList<>();
            num1 = Integer.parseInt(scanner.nextLine());
            if (num1 > 0) {
                System.out.println("������� " + num1 + " ����� (����� Enter) � ���� 'x y', ��� x � y ��������������� ����������");
                for (int i = 0; i < num1; i++) {
                    String str = scanner.nextLine();
                    Double[] dot = Lab1.getAB(str);
                    masX.add(dot[0]);
                    masY.add(dot[1]);
                }
            } else if (num1 < 0) {
                num1 = (-1) * num1;
                for (int i = 0; i < num1; i++) {
                    String str = reader1.nextLine();
                    Double[] dot = Lab1.getAB(str);
                    masX1.add(dot[0]);
                    masY1.add(dot[1]);
                }
            }

            System.out.println("������� 1 ����� ������� x1, x2, x3, x4 � ���������� ��� -1, ����� ������� �� �����");
            Scanner reader2 = new Scanner(Paths.get("in3.txt"));
            int ch = Integer.parseInt(scanner.nextLine());
            if (ch > 0) {
                System.out.println("������� x1, x2, x3, x4 (����� Enter)");
                String str = scanner.nextLine();
                x1 = Double.parseDouble(str);
                str = scanner.nextLine();
                x2 = Double.parseDouble(str);
                str = scanner.nextLine();
                x3 = Double.parseDouble(str);
                str = scanner.nextLine();
                x4 = Double.parseDouble(str);

            } else {
                String str = reader2.nextLine();
                x1 = Double.parseDouble(str);
                str = reader2.nextLine();
                x2 = Double.parseDouble(str);
                str = reader2.nextLine();
                x3 = Double.parseDouble(str);
                str = reader2.nextLine();
                x4 = Double.parseDouble(str);
            }

            //here goes 2.1 linear & quadr  for x=X1 from masX & masY
            ////linear
            System.out.println("� ������� �������� ������������ ��� " + x1 + " �������: \n");
            System.out.println("Y=" + lin(masX, masY, x1));
            System.out.println();
            ////quad
            System.out.println("� ������� ������������ ������������ ��� " + x1 + " �������: \n");
            System.out.println("Y=" + quad(masX, masY, x1));
            System.out.println();
            //2.2 Lagrange for x=X1 from masX & masY
            System.out.println("� ������� ���������� �������� ��� " + x1 + " �������: \n");
            System.out.println("Y=" + lag(masX, masY, x1));
            System.out.println();
            //2.3 Newton 1&2  for x=X2 & x=X3 from masX1 & masY1
            System.out.println("� ������� ������� ������� ��� " + x2 + " � " + x3 + " �������: \n");
            System.out.println("Y=" + newt(masX1, masY1, x2));
            System.out.println();
            System.out.println("Y=" + newt(masX1, masY1, x3));
            System.out.println();
            //2.4 Newton for != uzlov for x=X4 from masX & masY
            System.out.println("� ������� ������� ������� ��� " + x4  + " �������: \n");
            System.out.println("��� ������������� 3 ��������� �����");
            System.out.println("Y=" + newtuz(masX, masY, x4)[1]);
            System.out.println("��� ������������� 2 ��������� �����");
            System.out.println("Y=" + newtuz(masX, masY, x4)[2]);
            System.out.println("��� ������������� ���� �����");
            System.out.println("Y=" + newtuz(masX, masY, x4)[0]);
            System.out.println();

        } catch (IOException e) {
            System.out.println("�������� � ������-�������");
        }
    }

    //methods
    private static Double lin(ArrayList<Double> masX, ArrayList<Double> masY, Double x1) {
        for (int i = 1; i < num; i++) {
            if (masX.get(i - 1) < x1 && masX.get(i) >= x1) {
                //y=kx+b
                Double a, b;
                a = (masY.get(i) - masY.get(i - 1)) / (masX.get(i) - masX.get(i - 1));
                b = (masY.get(i - 1) - a * masX.get(i - 1));
                return a * x1 + b;
            }
        }
        System.out.print("X1 ����� �� ��������� �������� �����, ������� ���������� ");
        return null;
    }

    private static Double quad(ArrayList<Double> masX, ArrayList<Double> masY, Double x1) {
        for (int i = 1; i < num; i++) {
            if (masX.get(i - 1) < x1 && masX.get(i) >= x1) {
                Double a, b, c;

                if (i - 1 == 0) {
                    //trouble
                    Double znam = (masX.get(0) * masX.get(0) * masX.get(1) + masX.get(1) * masX.get(1) * masX.get(2) + masX.get(2) * masX.get(2) * masX.get(0)
                            - masX.get(0) * masX.get(0) * masX.get(2) - masX.get(1) * masX.get(1) * masX.get(0) - masX.get(2) * masX.get(2) * masX.get(1));
                    a = (masY.get(0) * masX.get(1) + masX.get(0) * masY.get(2) + masY.get(1) * masX.get(2)
                            - masY.get(2) * masX.get(1) - masY.get(1) * masX.get(0) - masY.get(0) * masX.get(2)) / (znam);
                    b = (masX.get(0) * masX.get(0) * masY.get(1) + masX.get(1) * masX.get(1) * masY.get(2) + masX.get(2) * masX.get(2) * masY.get(0)
                            - masX.get(0) * masX.get(0) * masY.get(2) - masX.get(1) * masX.get(1) * masY.get(0) - masX.get(2) * masX.get(2) * masY.get(1)) / (znam);
                    c = (masX.get(0) * masX.get(0) * masX.get(1) * masY.get(2) + masX.get(1) * masX.get(1) * masX.get(2) * masY.get(0) + masX.get(2) * masX.get(2) * masX.get(0) * masY.get(1)
                            - masX.get(0) * masX.get(0) * masX.get(2) * masY.get(1) - masX.get(1) * masX.get(1) * masX.get(0) * masY.get(2) - masX.get(2) * masX.get(2) * masX.get(1) * masY.get(0)) / (znam);
                    return a * x1 * x1 + b * x1 + c;
                } else {
                    //take x[i-2]
                    Double znam = (masX.get(i - 2) * masX.get(i - 2) * masX.get(i - 1) + masX.get(i - 1) * masX.get(i - 1) * masX.get(i) + masX.get(i) * masX.get(i) * masX.get(i - 2)
                            - masX.get(i - 2) * masX.get(i - 2) * masX.get(i) - masX.get(i - 1) * masX.get(i - 1) * masX.get(i - 2) - masX.get(i) * masX.get(i) * masX.get(i - 1));
                    a = (masY.get(i - 2) * masX.get(i - 1) + masX.get(i - 2) * masY.get(i) + masY.get(i - 1) * masX.get(i)
                            - masY.get(i) * masX.get(i - 1) - masY.get(i - 1) * masX.get(i - 2) - masY.get(i - 2) * masX.get(i)) / (znam);
                    b = (masX.get(i - 2) * masX.get(i - 2) * masY.get(i - 1) + masX.get(i - 1) * masX.get(i - 1) * masY.get(i) + masX.get(i) * masX.get(i) * masY.get(i - 2)
                            - masX.get(i - 2) * masX.get(i - 2) * masY.get(i) - masX.get(i - 1) * masX.get(i - 1) * masY.get(i - 2) - masX.get(i) * masX.get(i) * masY.get(i - 1)) / (znam);
                    c = (masX.get(i - 2) * masX.get(i - 2) * masX.get(i - 1) * masY.get(i) + masX.get(i - 1) * masX.get(i - 1) * masX.get(i) * masY.get(i - 2) + masX.get(i) * masX.get(i) * masX.get(i - 2) * masY.get(i - 1)
                            - masX.get(i - 2) * masX.get(i - 2) * masX.get(i) * masY.get(i - 1) - masX.get(i - 1) * masX.get(i - 1) * masX.get(i - 2) * masY.get(i) - masX.get(i) * masX.get(i) * masX.get(i - 1) * masY.get(i - 2)) / (znam);
                    return a * x1 * x1 + b * x1 + c;
                }
            }
        }
        System.out.print("X1 ����� �� ��������� �������� �����, ������� ���������� ");
        return null;
    }

    //��� ���������� ����� ����� �������� ��� �������������
    private static Double lag(ArrayList<Double> masX, ArrayList<Double> masY, Double x1) {
        Double su = 0.0;
        for (int i = 0; i < num; i++) {
            Double p = 1.0;
            for (int j = 0; j < num; j++) {
                if (j != i) {
                    p *= (x1 - masX.get(j)) / (masX.get(i) - masX.get(j));
                }
            }
            su += masY.get(i) * p;
        }
        return su;
    }

    private static Double newt(ArrayList<Double> masX1, ArrayList<Double> masY1, Double x2) {
        for (int i = 1; i < num1; i++) {
            if (masX1.get(i - 1) < x2 && masX1.get(i) >= x2) {
                if (i <= num1 / 2) {
                    //right
                    Double h = masX1.get(1) - masX1.get(0);
                    Double p = 1.0;
                    Double t = (x2 - masX1.get(i - 1)) / h;
                    ArrayList<Double> ret = deltaYR(masY1,i-1);
                    Double sum = ret.get(0);
                    for (int j = 1; j < ret.size(); j++) {
                        p *= (t - j +1) / j;
                        sum += p * ret.get(j);
                    }
                    return sum;
                } else {
                    //left
                    Double h = masX1.get(1) - masX1.get(0);
                    Double t = (x2-masX1.get(i))/h;
                    Double p = 1.0;
                    ArrayList<Double> ret = deltaYL(masY1,i-1);
                    Double sum = ret.get(0);
                    for (int j = 1; j <ret.size(); j++) {
                        p *= (t+j-1) /j;
                        sum += p*ret.get(j);
                    }
                    return sum;
                }
            }
        }
        return null;
    }

    private static ArrayList<Double> deltaYR(ArrayList<Double> masY1,int n) {
        ArrayList<Double> ret = new ArrayList<>();
        for (int i = n; i < num1; i++) {
            ret.add(masY1.get(i));
        }
        for (int j = 1; j < ret.size(); j++) {
            for (int i = ret.size()-1; i >= j; i--) {
                ret.set(i, ret.get(i) - ret.get(i - 1));
            }
        }
        return ret;
    }

    private static ArrayList<Double> deltaYL(ArrayList<Double> masY1,int n) {
        ArrayList<Double> ret = new ArrayList<>();
        for (int i = n+1; i >=0; i--) {
            ret.add(masY1.get(i));
        }
        for (int j = 1; j < ret.size(); j++) {
            for (int i = ret.size()-1; i >= j; i--) {
                ret.set(i, ret.get(i-1) - ret.get(i));
            }
        }
        return ret;
    }

    private static Double[] newtuz(ArrayList<Double> masX, ArrayList<Double> masY, Double x4) {
        Double[] ret = new Double[3];
        ArrayList<Double> delta = (ArrayList<Double>) masY.clone();
        for(int i=1;i<delta.size();i++){
            for(int j=delta.size()-1;j>=i;j--){
                delta.set(j,(delta.get(j)-delta.get(j-1))/(masX.get(j)-masX.get(j-i)));
            }
        }
        Double sum = delta.get(0);
        Double p = 1.0;
        for (int i=1;i<delta.size();i++){
            p*=(x4-masX.get(i-1));
            sum+=delta.get(i)*p;
        }
        ret[0]=sum;
        delta.clear();
        for(int i=num-3;i<num;i++)
            delta.add(masY.get(i));
        for(int i=1;i<delta.size();i++){
            for(int j=delta.size()-1;j>=i;j--){
                delta.set(j,(delta.get(j)-delta.get(j-1))/(masX.get(j)-masX.get(j-i)));
            }
        }
        sum = delta.get(0);
        p = 1.0;
        for (int i=1;i<delta.size();i++){
            p*=(x4-masX.get(i-1));
            sum+=delta.get(i)*p;
        }
        ret[1]=sum;
        delta.clear();
        for(int i=num-2;i<num;i++)
            delta.add(masY.get(i));
        for(int i=1;i<delta.size();i++){
            for(int j=delta.size()-1;j>=i;j--){
                delta.set(j,(delta.get(j)-delta.get(j-1))/(masX.get(j)-masX.get(j-i)));
            }
        }
        sum = delta.get(0);
        p = 1.0;
        for (int i=1;i<delta.size();i++){
            p*=(x4-masX.get(i-1));
            sum+=delta.get(i)*p;
        }
        ret[2]=sum;
        return ret;
    }
}