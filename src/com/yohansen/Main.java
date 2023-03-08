package com.yohansen;


public class Main {
    public static void main(String[] args) {
        if(args.length == 0){
            System.out.println("Anda harus masukan perintah");
        }else {
            var logic = new ProcessLogic();
            logic.process(args);
        }
    }
}
