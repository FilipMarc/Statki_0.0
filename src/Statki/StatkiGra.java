package Statki;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class StatkiGra {
    private int x,y;
    private String s1, s2;

    public void pokazMape(String[][] mapaOceanu){
        String zewnetrzneLiczby = "---0123456789---";
        System.out.println(zewnetrzneLiczby);

        for (int i = 0; i <mapaOceanu.length ; i++) {
            System.out.print(i +" |");
            for (int j = 0; j <mapaOceanu[i].length ; j++) {
                if (mapaOceanu[i][j] == null) {
                    mapaOceanu[i][j] = " ";
                    System.out.print(mapaOceanu[i][j]);
                }else {
                    System.out.print(mapaOceanu[i][j]);
                }
            }
            System.out.println("| " + i);
        }
        System.out.println(zewnetrzneLiczby);
    }

    public void rozmiesczenieStatkowGracza(String[][] mapaGracza, Scanner sc) throws Exception{
        System.out.println("Rozmieść statki.");
        boolean czyNieJestPoprawne = true;
        int i = 1;
        do {
            try {
                while (i<=5){
                    System.out.print("Wprowadź współrzędne x " +i+" statku: ");
                    s1 = sc.nextLine();
                    x = Integer.parseInt(s1);
                    while (x<0 || x>9){
                        System.out.println("Przekroczyłeś zakres x, powinien być w zakresie od 0-9");
                        System.out.print("Wprowadź współrzędne x" + i+ " statku ponownie: ");
                        x = sc.nextInt();
                        sc.nextLine();
                    }
                    System.out.print("Wprowadż współrzędne y " + i+" statku: ");
                    s2= sc.nextLine();
                    y = Integer.parseInt(s2);
                    while (y<0 || y>9){
                        System.out.println("Przekroczyłeś zakres y, powinien być w zakresie od 0-9");
                        System.out.print("Wprowadż współrzędne y"+i+" statku ponownie: ");
                        y = sc.nextInt();
                    }
                    if (mapaGracza[x][y] =="@"){
                        System.err.println("Nie możesz umieszczać 2 statków w tym samym miejscu");
                        continue;
                    }
                    mapaGracza[x][y] ="@";
                    i++;
                }
                czyNieJestPoprawne = false;
            } catch (InputMismatchException | NumberFormatException e) {
                System.out.println("Niepoprawne dane wyjściowe, proszę wprowadzić liczbę z zakresu od 0-9");
            }
        }while (czyNieJestPoprawne);
        pokazMape(mapaGracza);
    }

    public String[][] rozmiesczenieStatkówKomputera(String[][] mapaKomputera,String[][] mapaGracza, Random random){
        System.out.println("Komputer rozmieszcza statki.");
        int i = 1;
        while (i<=5){
            x = random.nextInt(10);
            y = random.nextInt(10);
            if (mapaKomputera[x][y] == "@" || mapaGracza[x][y] =="@") {
                continue;
            }
            mapaKomputera[x][y] = "@";
            System.out.println(i + ". Statki rozmiesczone");
            i++;
        }
        System.out.println("-------------------------------------------");
        return mapaKomputera;
    }

    public void bitwa(String[][] mapaKomputera, String[][] mapaGracza, Scanner sc, Random random) throws Exception {
        int statkiGracza = 5, statkiKomputera = 5;
        int i = 1;
        try{
            while (statkiGracza >0 && statkiKomputera>0){
                System.out.println("Twoje kolej");
                System.out.print("Wprowadz wspolrzedne x: ");
                s1 = sc.nextLine();
                x = Integer.parseInt(s1);
                while (x < 0 || x > 9){
                    System.out.println("Przekroczyłeś zakres x, powinien być w zakresie od 0-9");
                    System.out.print("Wprowadź współrzędne x: " + i+ " statku ponownie: ");
                    x = sc.nextInt();
                }
                System.out.print("Wprowadz wpolrzedne  y: ");
                s2 = sc.nextLine();
                y = Integer.parseInt(s2);
                while (y<0 || y>9){
                    System.out.println("Przekroczyłeś zakres y, powinien być w zakresie od 0-9");
                    System.out.print("Wprowadż współrzędne y: "+i+" statku ponownie: ");
                    y = sc.nextInt();
                }
                if (mapaKomputera[x][y] =="@") {
                    System.out.println("Strafiłeś w statek komputera !");
                    mapaGracza[x][y] ="!";
                    statkiKomputera--;
                }else if (mapaGracza[x][y]=="@"){
                    System.out.println("Niestety trafiłeś w swój statek");
                    mapaGracza[x][y] ="x";
                    statkiGracza--;
                }else if (mapaGracza[x][y] == " "){
                    System.out.println("Pudło !");
                    mapaGracza[x][y] ="-";
                }else {
                    System.out.println("Ta pozycja jest zajęta!");
                    continue;
                }
                pokazMape(mapaGracza);
                System.out.println("Twoje statki: " + statkiGracza + " | " + ", Statki komputera: " + statkiKomputera);
                System.out.println("--------------------------------------------");

                System.out.println("Runda komputera");
                x = random.nextInt(10);
                y = random.nextInt(10);
                while (mapaGracza[x][y] == "x" || mapaGracza[x][y] == "!"|| mapaGracza[x][y] == "-") {
                    x = random.nextInt(10);
                    y = random.nextInt(10);
                }
                if ( mapaGracza[x][y] == "@") {
                    System.out.println("Komputer zatopił twój statek");
                    mapaGracza[x][y] = "x";
                    statkiGracza--;
                }else if (mapaGracza[x][y] == "@") {
                    System.out.println("Komputer zatopił swój własny statek");
                    mapaGracza[x][y] = "!";
                    statkiGracza--;
                }else if (mapaGracza[x][y] == " ") {
                    System.out.println("Komputer chybił");
                    mapaGracza[x][y] = "-";
                }else {
                    continue;
                }
                pokazMape(mapaGracza);
                System.out.println("Twoje statki:  " + statkiGracza + " | " + "Statki komputera: " + statkiKomputera);
                System.out.println("----------------------------------------------------");
            }
        }catch (InputMismatchException | NumberFormatException | ArrayIndexOutOfBoundsException e){
            System.out.println("NIe poprawne dane wyjściowe, proszę wprowadzić liczbę z przedziału od 0 - 9");
        }finally {
            bitwa(mapaKomputera, mapaGracza,sc,random);
        }
        if (statkiGracza > 0) {
            System.out.println("Wygrywasz :)");
        }else {
            System.out.println("Niestety komputer wygrał :( ");
        }

    }

}
