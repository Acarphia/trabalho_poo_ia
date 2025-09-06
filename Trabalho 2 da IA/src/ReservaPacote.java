import java.util.ArrayList;
import java.util.List;

public class ReservaPacote extends Reserva {
    private final List<Reserva> componentes = new ArrayList<>();
    private String descricao;

    public ReservaPacote(String clienteNome, String descricao) {
        super(clienteNome, 0.0);
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("Descrição do pacote não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public void adicionarComponente(Reserva r) {
        if (r == null) throw new IllegalArgumentException("Componente nulo.");
        componentes.add(r);
    }

    @Override
    public double calcularValor() {
        double soma = 0.0;
        for (Reserva r : componentes) {
            soma += r.calcularValor();
        }
        // Desconto de pacote exemplo: 10%
        return soma * 0.9;
    }

    @Override
    public String toString() {
        return "Pacote: " + descricao + " " + super.toString() + String.format(" (componentes: %d)", componentes.size());
    }
}
