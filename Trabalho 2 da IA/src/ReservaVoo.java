public class ReservaVoo extends Reserva {
    public enum Classe { ECONOMICA, EXECUTIVA, PRIME }

    private Classe classe;
    private double taxaBagagem; // exemplo adicional

    public ReservaVoo(String clienteNome, double valorBase, Classe classe, double taxaBagagem) {
        super(clienteNome, valorBase);
        if (classe == null) throw new IllegalArgumentException("Classe do voo não pode ser nula.");
        if (taxaBagagem < 0) throw new IllegalArgumentException("Taxa de bagagem não pode ser negativa.");
        this.classe = classe;
        this.taxaBagagem = taxaBagagem;
    }

    @Override
    public double calcularValor() {
        double multiplicador = 1.0;
        switch (classe) {
            case ECONOMICA: multiplicador = 1.0; break;
            case EXECUTIVA: multiplicador = 2.0; break;
            case PRIME: multiplicador = 3.0; break;
        }
        return valorBase * multiplicador + taxaBagagem;
    }

    @Override
    public String toString() {
        return "Voo " + super.toString();
    }
}
