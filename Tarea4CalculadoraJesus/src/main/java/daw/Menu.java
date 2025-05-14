package daw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {

    public static int ElejirOpcion() {

        Scanner sc = new Scanner(System.in);

        int num = 0;

        String strOpciones = """
                             
                          Que operacion deseas realizar:
                          1 Suma
                          2 Resta
                          3 Multiplicacion
                          4 Division
                          5 Salir
                          """;
        do {

            System.out.println(strOpciones);
            try {

                num = sc.nextInt();

            } catch (InputMismatchException ime) {

                System.out.println("Valor invalido, introduce un numero entero");
                sc.nextLine();
            }
        } while (!(num > 0 && num < 6));

        return num;
    }

}
