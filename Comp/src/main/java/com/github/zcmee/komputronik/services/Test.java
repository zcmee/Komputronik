package com.github.zcmee.komputronik.services;

class Makumba{

    {
        System.out.println("HERERE I GO {} ");
    }

    static {
        System.out.println("HERERE I GO static{} ");
    }

    public Makumba(){
        System.out.println("Konstruktor makumba");
    }


}


public class Test {


    public static void main(String[] args) {
        Makumba makumba = new Makumba();
        Makumba makumba2 = new Makumba();
    }


}
