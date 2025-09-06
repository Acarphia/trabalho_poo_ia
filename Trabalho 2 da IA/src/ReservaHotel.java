public class ReservaHotel extends Reserva {
    private int diarias;
    private double valorDiaria;

    public ReservaHotel(String clienteNome, int diarias, double valorDiaria) {
        super(clienteNome, diarias * valorDiaria);
        if (diarias <= 0) throw new IllegalArgumentException("Quantidade de diárias deve ser positiva.");
        if (valorDiaria < 0) throw new IllegalArgumentException("Valor da diária não pode ser negativo.");
        this.diarias = diarias;
        this.valorDiaria = valorDiaria;
    }

    @Override
    public double calcularValor() {
        return diarias * valorDiaria;
    }

    @Override
    public String toString() {
        return "Hotel " + super.toString() + String.format(" (%d diárias x R$ %.2f)", diarias, valorDiaria);
    }
}
