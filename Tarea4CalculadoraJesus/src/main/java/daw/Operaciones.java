package daw;

public class Operaciones {

    public static int suma() {

        return Entrada.IntroducirOP(1) + Entrada.IntroducirOP(2);

    }

    public static int resta() {

        return Entrada.IntroducirOP(1) - Entrada.IntroducirOP(2);

    }

    public static int multiplicacion() {

        return Entrada.IntroducirOP(1) * Entrada.IntroducirOP(2);

    }

    public static int division() {

        int num;

        do {

            try {

                num = Entrada.IntroducirOP(1) / Entrada.IntroducirOP(2);
                return num;
            } catch (ArithmeticException ae) {

                System.out.println("Dividir entre cero romperia el espacio-tiempo, no lo hagas");
            }

        } while (true);
    }
}
