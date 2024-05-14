import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma opção: \n1. Buscar Número de Salas\n2. Enviar Número de Salas\n3. Buscar Corredores\n4. Enviar Corredores");
        int choice = scanner.nextInt();

         String IP_ADDRESS = "194.210.86.10";
         String DATABASE_NAME = "pisid2024";
         String USERNAME = "aluno";
         String PASSWORD = "aluno";
         String PORT = "3306";

         String JDBC_URL = "jdbc:mariadb://" + IP_ADDRESS + ":" + PORT + "/" + DATABASE_NAME + "?useSSL=false"; // String de conexão JDBC

        String IP_ADDRESS1 = "10.192.27.87";
        String DATABASE_NAME1 = "pisid25";
        String USERNAME1 = "Pisid25";
        String PASSWORD1 = "Pisid252024";
        String PORT1 = "3306";

        String JDBC_URL1 = "jdbc:mariadb://" + IP_ADDRESS1 + ":" + PORT1 + "/" + DATABASE_NAME1 + "?useSSL=false"; // String de conexão JDBC


        int numeroDeSalas = ConfiguracaoLabirintoMapper.fetchNumeroDeSalas(JDBC_URL, USERNAME, PASSWORD);
        System.out.println("Número de Salas: " + numeroDeSalas);

        List<Corredor> corredores = CorredorMapper.fetchCorredores(JDBC_URL, USERNAME, PASSWORD);

        for (Corredor corredor : corredores) {
            System.out.println("Sala de Origem: " + corredor.getSalaOrigem());
            System.out.println("Sala de Destino: " + corredor.getSalaDestino());
        }


        switch (choice) {
            case 1:
                int salas = ConfiguracaoLabirintoMapper.fetchNumeroDeSalas(JDBC_URL, USERNAME, PASSWORD);
                System.out.println("Número de Salas: " + salas);
                break;
            case 2:
                int salasToSend = ConfiguracaoLabirintoMapper.fetchNumeroDeSalas(JDBC_URL, USERNAME, PASSWORD);
                DatabaseTransfer.sendNumeroDeSalas(salasToSend, JDBC_URL1, USERNAME1, PASSWORD1);
                System.out.println("Número de salas enviado.");
                break;
            case 3:
                List<Corredor> corredores1 = CorredorMapper.fetchCorredores(JDBC_URL, USERNAME, PASSWORD);
                corredores1.forEach(c -> System.out.println("De " + c.getSalaOrigem() + " para " + c.getSalaDestino()));
                break;
            case 4:
                List<Corredor> corredoresToSend = CorredorMapper.fetchCorredores(JDBC_URL, USERNAME, PASSWORD);
                DatabaseTransfer.sendCorredores(corredoresToSend,  JDBC_URL1, USERNAME1, PASSWORD1);
                System.out.println("Corredores enviados.");
                break;
            default:
                System.out.println("Opção inválida.");
        }
        scanner.close();
    }
    }




