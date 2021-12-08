package com.company;

public class Board {
    private int w = 8;
    private int k = 8;
    private boolean[][] plansza;
    private boolean[][] plansza_nowa;
    private int krok = 1;

    public Board() {
        plansza = new boolean[w][k];
        plansza_nowa = new boolean[w][k];
    }

    public Board(int w, int k) {
        this.w = w;
        this.k = k;
        plansza = new boolean[w][k];
        plansza_nowa = new boolean[w][k];
    }
    public void init2() {
        for(int i = 0; i < w; ++i) {
            for (int j = 0; j < k; ++j) {
                plansza[i][j] = false;
            }
        }
        plansza[0][1] = true;
        plansza[1][2] = true;
        plansza[2][0] = true;
        plansza[2][1] = true;
        plansza[2][2] = true;
    }

    public int liczbaSasiadow(int x, int y) {
        int liczba_sasiadow = 0;
        for(int i = x - 1; i <= x + 1; ++i) {
            if(i < 0 || i > w - 1) continue;
            for(int j = y - 1; j <= y + 1; ++j) {
                if (j < 0 || j > k - 1 || (i == x && j == y)) continue;
                if (plansza[i][j]) liczba_sasiadow++;
            }
        }
        return liczba_sasiadow;
    }

    public boolean zmiana(int x, int y) {
        int liczba_sasiadow = liczbaSasiadow(x, y);
        if(plansza[x][y]) {
            if(liczba_sasiadow <= 1 || liczba_sasiadow > 3){
                System.out.println("Umiera " + x + " " + y + " liczba sąsiadów " + liczba_sasiadow);
                return false;
            }
            if(liczba_sasiadow == 3 || liczba_sasiadow == 2) {
                System.out.println("Żyje dalej " + x + " " + y + " liczba sąsiadów " + liczba_sasiadow);
                return true;
            }
        } else {
            if (liczba_sasiadow == 3) {
                System.out.println("Ożywa " + x + " " + y + " liczba sąsiadów " + liczba_sasiadow);
                return true;
            }
        }
        return false;
    }

    public void step(){
        System.out.println();
        System.out.println("Krok" + krok + ":");
            for(int i = 0; i < w; ++i)
            for(int j = 0; j < k; ++j)
                plansza_nowa[i][j] = zmiana(i, j);
        System.out.println();
        krok++;
    }

    public void draw2(){
        for(int i = 0; i < w; ++i) {
            for (int j = 0; j < k; ++j) {
                if (plansza[i][j] == true) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
    }

    public void draw(){
        for(int i = 0; i < w; ++i) {
            for (int j = 0; j < k; ++j) {
                if (plansza_nowa[i][j] == true) System.out.print("#");
                else System.out.print(".");
            }
            System.out.println();
        }
        for(int i = 0; i < w; ++i) {
            for (int j = 0; j < k; ++j) {
                plansza[i][j] = plansza_nowa[i][j];
            }
        }
    }

    public int spr(){
        int test = 0;
        for(int i = 0; i < k; ++i){
            for(int j = 0; j < w; ++j){
                if(plansza[i][j] == false) test++;
            }
        }
        return test;
    }

    public void doit(){
        init2();
        draw2();
        int x = 0;
        while(x!=23){
            step();
            draw();
            x++;
        }
    }
}
