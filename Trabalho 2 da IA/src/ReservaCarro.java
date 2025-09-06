public class ReservaCarro extends Reserva {
    public enum Tipo { POPULAR, INTERMEDIARIO, LUXO }

    private int dias;
    private Tipo tipo;

    public ReservaCarro(String clienteNome, int dias, Tipo tipo, double valorPorDia) {
        super(clienteNome, dias * valorPorDia);
        if (dias <= 0) throw new IllegalArgumentException("Quantidade de dias deve ser positiva.");
        if (tipo == null) throw new IllegalArgumentException("Tipo do carro não pode ser nulo.");
        this.dias = dias;
        this.tipo = tipo;
    }

    @Override
    public double calcularValor() {
        double fator = 1.0;
        switch (tipo) {
            case POPULAR: fator = 1.0; break;
            case INTERMEDIARIO: fator = 1.5; break;
            case LUXO: fator = 2.5; break;
        }
        return dias * fator * (valorBase / Math.max(dias, 1)); // valorBase foi inicializado como dias*valorPorDia
    }

    @Override
    public String toString() {
        return "Carro " + super.toString() + String.format(" (%d dias, %s)", dias, tipo);
    }
}
