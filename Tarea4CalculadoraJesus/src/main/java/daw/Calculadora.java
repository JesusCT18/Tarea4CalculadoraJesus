package daw;

public class Calculadora {

    public static void main(String[] args) {

        int resultado;
        int opcion;

        do {

            opcion = Menu.ElejirOpcion();

            resultado = switch (opcion) {
                case (1) -> {
                    System.out.println("Suma");
                    yield Operaciones.suma();
                }
                case (2) -> {
                    System.out.println("Resta");
                    yield Operaciones.resta();
                }
                case (3) -> {
                    System.out.println("Multiplicacion");
                    yield Operaciones.multiplicacion();
                }
                case (4) -> {
                    System.out.println("Division");
                    yield Operaciones.division();
                }
                case (5) -> {
                    System.out.println("Adios");
                    yield 0;
                }
                default -> {
                    System.out.println("El valor introducido no es valido,"
                            + "\nPor favor introduzca un numero entre 1 y 5");
                    yield 0;
                }
            };
            if (opcion > 0 && opcion < 5) {
                System.out.printf("El resultado es %d \n \n", resultado);
            }

        } while (opcion != 5);

    }
}
