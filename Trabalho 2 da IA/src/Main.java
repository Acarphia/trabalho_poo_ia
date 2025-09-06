import java.util.List;
import java.util.Scanner;

//Ana Clara Fagundes Curcino - 12321BSI279; Luiza Vieira Pavarina - 12411GIN047; Marcos Paulo Alves - 12311GIN022
//Implementação da IA usando o padrão Facade para gerenciar o sistema de reservas.

public class Main {
    private static final SistemaReservaFacade fachada = new SistemaReservaFacade();
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        boolean rodando = true;
        while (rodando) {
            mostrarMenu();
            String opc = sc.nextLine().trim();
            switch (opc) {
                case "1": criarReservaVooCLI(); break;
                case "2": criarReservaHotelCLI(); break;
                case "3": criarReservaCarroCLI(); break;
                case "4": criarReservaPacoteCLI(); break;
                case "5": listarTodas(); break;
                case "6": listarPorTipoCLI(); break;
                case "7": exibirValorReservaCLI(); break;
                case "8": cancelarReservaCLI(); break;
                case "9": exibirValorTotalTodas(); break;
                case "0": rodando = false; break;
                default: System.out.println("Opção inválida. Tente novamente."); break;
            }
            System.out.println(); // linha em branco
        }
        System.out.println("Saindo. Obrigado!"); 
        sc.close();
    }

    private static void mostrarMenu() {
        System.out.println("=== Sistema de Reservas (Facade) ===");
        System.out.println("1 - Criar Reserva de Voo");
        System.out.println("2 - Criar Reserva de Hotel");
        System.out.println("3 - Criar Reserva de Carro");
        System.out.println("4 - Criar Reserva de Pacote (combinação)"); 
        System.out.println("5 - Listar todas as reservas");
        System.out.println("6 - Listar reservas por tipo");
        System.out.println("7 - Exibir valor de uma reserva (pelo código)"); 
        System.out.println("8 - Cancelar reserva (pelo código)"); 
        System.out.println("9 - Exibir valor total de todas as reservas");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void criarReservaVooCLI() {
        try {
            System.out.print("Nome do cliente: ");
            String nome = sc.nextLine();
            System.out.print("Valor base do voo (ex: 200.0): ");
            double valor = Double.parseDouble(sc.nextLine());
            System.out.print("Classe (1-Economica, 2-Executiva, 3-Prime): ");
            int c = Integer.parseInt(sc.nextLine());
            ReservaVoo.Classe classe = switch (c) {
                case 1 -> ReservaVoo.Classe.ECONOMICA;
                case 2 -> ReservaVoo.Classe.EXECUTIVA;
                case 3 -> ReservaVoo.Classe.PRIME;
                default -> throw new IllegalArgumentException("Classe inválida.");
            };
            System.out.print("Taxa de bagagem (ex: 50.0): ");
            double taxa = Double.parseDouble(sc.nextLine());
            ReservaVoo rv = fachada.criarReservaVoo(nome, valor, classe, taxa);
            System.out.println("Criado: " + rv);
        } catch (Exception e) {
            System.out.println("Erro ao criar reserva de voo: " + e.getMessage());
        }
    }

    private static void criarReservaHotelCLI() {
        try {
            System.out.print("Nome do cliente: ");
            String nome = sc.nextLine();
            System.out.print("Quantidade de diárias: ");
            int diarias = Integer.parseInt(sc.nextLine());
            System.out.print("Valor da diária (ex: 150.0): ");
            double valorDiaria = Double.parseDouble(sc.nextLine());
            ReservaHotel rh = fachada.criarReservaHotel(nome, diarias, valorDiaria);
            System.out.println("Criado: " + rh);
        } catch (Exception e) {
            System.out.println("Erro ao criar reserva de hotel: " + e.getMessage());
        }
    }

    private static void criarReservaCarroCLI() {
        try {
            System.out.print("Nome do cliente: ");
            String nome = sc.nextLine();
            System.out.print("Quantidade de dias: ");
            int dias = Integer.parseInt(sc.nextLine());
            System.out.print("Tipo (1-Popular, 2-Intermediario, 3-Luxo): ");
            int t = Integer.parseInt(sc.nextLine());
            ReservaCarro.Tipo tipo = switch (t) {
                case 1 -> ReservaCarro.Tipo.POPULAR;
                case 2 -> ReservaCarro.Tipo.INTERMEDIARIO;
                case 3 -> ReservaCarro.Tipo.LUXO;
                default -> throw new IllegalArgumentException("Tipo inválido.");
            };
            System.out.print("Valor por dia (ex: 80.0): ");
            double valorDia = Double.parseDouble(sc.nextLine());
            ReservaCarro rc = fachada.criarReservaCarro(nome, dias, tipo, valorDia);
            System.out.println("Criado: " + rc);
        } catch (Exception e) {
            System.out.println("Erro ao criar reserva de carro: " + e.getMessage());
        }
    }

    private static void criarReservaPacoteCLI() {
        try {
            System.out.print("Nome do cliente: ");
            String nome = sc.nextLine();
            System.out.print("Descrição do pacote: ");
            String desc = sc.nextLine();
            ReservaPacote rp = fachada.criarReservaPacote(nome, desc);
            System.out.println("Pacote criado com código: " + rp.getCodigo());
            System.out.println("Agora adicione componentes ao pacote.");
            boolean adicionando = true;
            while (adicionando) {
                System.out.println("1 - Adicionar voo | 2 - Adicionar hotel | 3 - Adicionar carro | 0 - Terminar"); 
                System.out.print("Escolha: ");
                int op = Integer.parseInt(sc.nextLine());
                switch (op) {
                    case 1 -> {
                        System.out.print("Valor base do voo: ");
                        double v = Double.parseDouble(sc.nextLine());
                        System.out.print("Classe (1-Economica,2-Executiva,3-Prime): ");
                        int c = Integer.parseInt(sc.nextLine());
                        ReservaVoo.Classe classe = switch (c) {
                            case 1 -> ReservaVoo.Classe.ECONOMICA;
                            case 2 -> ReservaVoo.Classe.EXECUTIVA;
                            case 3 -> ReservaVoo.Classe.PRIME;
                            default -> throw new IllegalArgumentException("Classe inválida.");
                        };
                        System.out.print("Taxa de bagagem: ");
                        double taxa = Double.parseDouble(sc.nextLine());
                        ReservaVoo rv = new ReservaVoo(nome, v, classe, taxa);
                        rp.adicionarComponente(rv);
                        System.out.println("Adicionado: " + rv);
                    }
                    case 2 -> {
                        System.out.print("Quantidade de diárias: ");
                        int d = Integer.parseInt(sc.nextLine());
                        System.out.print("Valor da diária: ");
                        double vd = Double.parseDouble(sc.nextLine());
                        ReservaHotel rh = new ReservaHotel(nome, d, vd);
                        rp.adicionarComponente(rh);
                        System.out.println("Adicionado: " + rh);
                    }
                    case 3 -> {
                        System.out.print("Quantidade de dias: ");
                        int dias = Integer.parseInt(sc.nextLine());
                        System.out.print("Tipo (1-Popular,2-Intermediario,3-Luxo): ");
                        int tt = Integer.parseInt(sc.nextLine());
                        ReservaCarro.Tipo tipo = switch (tt) {
                            case 1 -> ReservaCarro.Tipo.POPULAR;
                            case 2 -> ReservaCarro.Tipo.INTERMEDIARIO;
                            case 3 -> ReservaCarro.Tipo.LUXO;
                            default -> throw new IllegalArgumentException("Tipo inválido.");
                        };
                        System.out.print("Valor por dia: ");
                        double vp = Double.parseDouble(sc.nextLine());
                        ReservaCarro rc = new ReservaCarro(nome, dias, tipo, vp);
                        rp.adicionarComponente(rc);
                        System.out.println("Adicionado: " + rc);
                    }
                    case 0 -> adicionando = false;
                    default -> System.out.println("Opção inválida."); 
                }
            }
            System.out.println("Pacote final: " + rp);
        } catch (Exception e) {
            System.out.println("Erro ao criar pacote: " + e.getMessage());
        }
    }

    private static void listarTodas() {
        List<Reserva> todas = fachada.listarTodas();
        if (todas.isEmpty()) {
            System.out.println("Nenhuma reserva cadastrada.");
            return;
        }
        todas.forEach(System.out::println);
    }

    private static void listarPorTipoCLI() {
        System.out.println("Escolha o tipo: 1-Voo 2-Hotel 3-Carro 4-Pacote");
        System.out.print("Opção: ");
        String op = sc.nextLine();
        Class<?> tipo;
        switch (op) {
            case "1": tipo = ReservaVoo.class; break;
            case "2": tipo = ReservaHotel.class; break;
            case "3": tipo = ReservaCarro.class; break;
            case "4": tipo = ReservaPacote.class; break;
            default: System.out.println("Tipo inválido."); return;
        }
        List<Reserva> lista = fachada.listarPorTipo(tipo);
        if (lista.isEmpty()) {
            System.out.println("Nenhuma reserva desse tipo encontrada.");
            return;
        }
        lista.forEach(System.out::println);
    }

    private static void exibirValorReservaCLI() {
        try {
            System.out.print("Código da reserva: ");
            int codigo = Integer.parseInt(sc.nextLine());
            double valor = fachada.calcularValorReserva(codigo);
            System.out.printf("Valor da reserva #%d = R$ %.2f\n", codigo, valor);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void cancelarReservaCLI() {
        try {
            System.out.print("Código da reserva a cancelar: ");
            int codigo = Integer.parseInt(sc.nextLine());
            boolean ok = fachada.cancelarReserva(codigo);
            if (ok) System.out.println("Reserva cancelada com sucesso.");
            else System.out.println("Reserva não encontrada.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void exibirValorTotalTodas() {
        double total = fachada.calcularValorTotalTodas();
        System.out.printf("Valor total de todas as reservas: R$ %.2f\n", total);
    }
}
