package src;

import java.util.Scanner;

public class SeaBattle1 {
    private static int[][] pole = new int[10][10];
    private static byte ind;
    private static byte ind1;
    public int gorVert = 0;
    private static String myHod;
    private static String s;

    public static void main(String[] args) {
        ship[] shipX = new ship[10];
        int i = 0;

        //// Определяем игровое поле
        int j;
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                pole[i][j] = 0;
            }
        }
        //// Задаём позицию размещения кораблей (вертикольно/горизонтально)
        i = 0;
        while (i < 10) {
            shipX[i] = new ship();
            shipX[i].position = (Math.random() * 2) < 1;
            i++;
            //System.out.println("положение " + (i) + " коробля: " + shipX[i - 1].position);
        }

        //// Задаём начальную позицию и расставляем корабли
        ind = 1;
        ind1 = 4;
        setShip(ind, ind1);

        //// Ставим трёхпалубные корабли
        ind = 2;
        ind1 = 3;
        setShip(ind, ind1);

        //// Ставим двупалубные корабли
        ind = 3;
        ind1 = 2;
        setShip(ind, ind1);

        //// Ставим двупалубные корабли
        ind = 4;
        ind1 = 1;
        setShip(ind, ind1);

        // Убираем маркеры границ кораблей
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                if (pole[i][j] == 8) {
                    pole[i][j] = 0;
                }
            }
        }

        //// Выводим поле (проверка работоспособности)
        for (i = 0; i < 10; i++) {
            for (j = 0; j < 10; j++) {
                System.out.print(pole[i][j] + " ");
            }
            System.out.println(" ");
        }
        boolean tester = false;
        while (!tester) {
            if (!gameOver()) hod();
        }
    }
    //// Ввод хода
    private static void hod () {
        Scanner console = new Scanner(System.in);
        String myHod = null;
        boolean exit = false;
        while (!exit){
            System.out.println("Сделайте ход, используйте буквы (ABCDEFGHIJ) и цифры (1..10): ");
            myHod = console.nextLine();
            if (myHod.length()==0) {
                System.out.println("Вы ничего не ввели!");
            }  else {
                if (myHod.length()>3) {
                    System.out.println("Неверный ввод!");
                } else {
                    if (!(((myHod.charAt(0)>64)&(myHod.charAt(0)<75))|
                            ((myHod.charAt(0)>96)&(myHod.charAt(0)<107))&
                                    ((myHod.charAt(1)>47)&(myHod.charAt(1)<58)))) {
                        System.out.println("Неверный ввод!");
                    } else exit = true;

                }
            }
        }
        int i = 0;
        int j = 0;
        boolean ok = true;
        int hodX = 0;
        int hodY = myHod.charAt(1)-'0';
        if ((myHod.length()==3)) {
            if ((myHod.charAt(1) == '1') & (myHod.charAt(2) == '0')) hodY = 10;
            else System.out.println("Неверный ввод!");
        }
        char char1 = myHod.charAt(0);

        if ((char1 == 'A') | (char1 == 'a')) hodX = 0;
        if ((char1 == 'B') | (char1 == 'b')) hodX = 1;
        if ((char1 == 'C') | (char1 == 'c')) hodX = 2;
        if ((char1 == 'D') | (char1 == 'd')) hodX = 3;
        if ((char1 == 'E') | (char1 == 'e')) hodX = 4;
        if ((char1 == 'F') | (char1 == 'f')) hodX = 5;
        if ((char1 == 'G') | (char1 == 'g')) hodX = 6;
        if ((char1 == 'H') | (char1 == 'h')) hodX = 7;
        if ((char1 == 'I') | (char1 == 'i')) hodX = 8;
        if ((char1 == 'J') | (char1 == 'j')) hodX = 9;

        if (pole[hodX][hodY-1] != 0) {
            ok = true;
            for (i = hodX - 1; i < hodX + 2; i++) {
                for (j = hodY - 2; j < hodY + 1; j++) {
                    if ((i >= 0) & (i < 10) & (j >= 0) & (j < 10)) {
                        if (!((i==hodX)&(j==hodY-1))){
                            if (pole[i][j] == 1) {
                                ok = false;
                            }
                        }
                    }
                }
            }
            if (ok) {
                pole[hodX][hodY - 1] = 0;
                System.out.println("Убит");
                gameOver();
                if (gameOver()) {System.exit(0);}

            } else {
                pole[hodX][hodY - 1] = 0;
                System.out.println("Ранение");
            }
        } else System.out.println("Мимо");

        return;
    }


    //// Метод проверки наличия целых кораблей
    private static boolean gameOver () {
        int i = 0;
        int j = 0;
        boolean ok = true;
        for (i = 0; i < 10; i++) {
            for (j = 0;j < 10; j++){
                if (pole[i][j] == 1) ok = false;
            }
        }
        if (ok) {
            System.out.println("Победа!");
        }
        return ok;
    }

    //// Метод расстановки кораблей
    public static void setShip (byte ind, byte ind1){
        int i = 0;
        int j = 0;
        int k = 0;
        byte xx;
        byte yy;
        boolean ok = true;
        ship [] setShipX = new ship[ind];
        while (i < ind) {
            setShipX[i] = new ship();
            setShipX[i].position = (Math.random() * 2) < 1;
            i++;
        }
        i = 0;
        if (setShipX[ind-1].position) {
            k = 0;
            while (k < ind) {
                xx = (byte) (Math.random() * (11 - ind1));
                yy = (byte) (Math.random() * (11 - ind1));
                ok = true;
                for (i = xx; i < xx + ind1; i++) {
                    if (pole[i][yy] != 0) ok = false;
                }
                if (ok) {
                    for (i = xx; i < xx + ind1; i++) {
                        pole[i][yy] = 1;
                    }
                    for (i = xx - 1; i <= xx + ind1; i++) {
                        for (j = yy - 1; j <= yy + 1; j++) {
                            if ((i >= 0) & (j >= 0) & (i < 10) & (j < 10)) {
                                if (pole[i][j] != 1) {
                                    pole[i][j] = 8;
                                }
                            }
                        }
                    }
                } else {
                    xx = (byte) (Math.random() * (11 - ind1));
                    yy = (byte) (Math.random() * (11 - ind1));
                    //System.out.println(xx + " " + yy);
                    k--;
                }
                k++;
            }
        } else {
            k = 0;
            while (k < ind) {
                xx = (byte) (Math.random() * (11 - ind1));
                yy = (byte) (Math.random() * (11 - ind1));
                //System.out.println(xx + " " + yy);
                ok = true;
                for (i = yy; i < yy + ind1; i++) {
                    if (pole[xx][i] != 0) ok = false;
                }
                if (ok) {
                    for (i = yy; i < yy + ind1; i++) {
                        pole[xx][i] = 1;
                    }
                    for (i = xx - 1; i <= xx + 1; i++) {
                        for (j = yy - 1; j <= yy + ind1; j++) {
                            if ((i >= 0) & (j >= 0) & (i < 10) & (j < 10)) {
                                if (pole[i][j] != 1) {
                                    pole[i][j] = 8;
                                }
                            }
                        }
                    }
                } else {
                    xx = (byte) (Math.random() * (11 - ind1));
                    yy = (byte) (Math.random() * (11 - ind1));
                    //System.out.println(xx + " " + yy);
                    k--;
                }
                k++;
            }
        }
    }
}

class ship {
    boolean position;
    byte length;
    byte startX;
    byte startY;
}


