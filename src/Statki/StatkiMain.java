package Statki;

import java.util.Random;
import java.util.Scanner;

public class StatkiMain extends StatkiGra{
    public static void main(String[] args) {

        StatkiMain st = new StatkiMain();
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        String[][] mapaOceanu = new String[10][10];
        String [][] mapaKomputera = new String [10][10];

        st.pokazMape(mapaOceanu);
        try {
            st.rozmiesczenieStatkowGracza(mapaOceanu,sc);
        } catch (Exception e) {
            e.printStackTrace();
        }

        st.rozmiesczenieStatkówKomputera(mapaKomputera,mapaOceanu,rand);
        try {
            st.bitwa(mapaKomputera,mapaOceanu,sc,rand);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
