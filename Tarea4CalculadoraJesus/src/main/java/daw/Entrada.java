package daw;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Entrada {

    public static int scN() {

        Scanner sc = new Scanner(System.in);

        int num;
        do {

            try {

                num = sc.nextInt();
                return num;

            } catch (InputMismatchException ime) {

                System.out.println("Valor invalido, por favor introduce un numero entero");
                sc.nextLine();
            }

        } while (true);
    }

    public static int IntroducirOP(int numOp) {

        int num;

        System.out.printf("Intoduce el operando %d \n", numOp);
        num = scN();
        return num;

    }
}
