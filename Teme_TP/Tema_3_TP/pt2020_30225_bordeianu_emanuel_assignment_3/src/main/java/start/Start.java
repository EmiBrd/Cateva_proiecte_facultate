package start;


import citire_fisier.Read;

public class Start {
    public static void main(String[] args) {

       // Read.parseInputFile("commands.txt");

        /***
         * conditia ca args.length sa fie mai mare decat 0
         * este necesara pentru rulare
         */
        System.out.println("Hello world!");
        if (args.length > 0)
            Read.parseInputFile(args[0]);


    }

}


