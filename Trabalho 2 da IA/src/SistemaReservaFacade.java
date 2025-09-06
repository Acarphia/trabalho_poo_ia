import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Facade que fornece uma interface simples para criar e gerenciar reservas.
 */
public class SistemaReservaFacade {
    private final List<Reserva> reservas = new ArrayList<>();

    // --- Criação simplificada de reservas
    public ReservaVoo criarReservaVoo(String clienteNome, double valorBase, ReservaVoo.Classe classe, double taxaBagagem) {
        ReservaVoo rv = new ReservaVoo(clienteNome, valorBase, classe, taxaBagagem);
        reservas.add(rv);
        return rv;
    }

    public ReservaHotel criarReservaHotel(String clienteNome, int diarias, double valorDiaria) {
        ReservaHotel rh = new ReservaHotel(clienteNome, diarias, valorDiaria);
        reservas.add(rh);
        return rh;
    }

    public ReservaCarro criarReservaCarro(String clienteNome, int dias, ReservaCarro.Tipo tipo, double valorPorDia) {
        ReservaCarro rc = new ReservaCarro(clienteNome, dias, tipo, valorPorDia);
        reservas.add(rc);
        return rc;
    }

    public ReservaPacote criarReservaPacote(String clienteNome, String descricao) {
        ReservaPacote rp = new ReservaPacote(clienteNome, descricao);
        reservas.add(rp);
        return rp;
    }

    // --- Operações de consulta
    public List<Reserva> listarTodas() {
        return new ArrayList<>(reservas);
    }

    public List<Reserva> listarPorTipo(Class<?> tipo) {
        return reservas.stream().filter(r -> tipo.isInstance(r)).collect(Collectors.toList());
    }

    public Reserva buscarPorCodigo(int codigo) {
        return reservas.stream().filter(r -> r.getCodigo() == codigo).findFirst().orElse(null);
    }

    public double calcularValorReserva(int codigo) {
        Reserva r = buscarPorCodigo(codigo);
        if (r == null) throw new IllegalArgumentException("Reserva não encontrada: " + codigo);
        return r.calcularValor();
    }

    public boolean cancelarReserva(int codigo) {
        Reserva r = buscarPorCodigo(codigo);
        if (r == null) return false;
        r.cancelar();
        return true;
    }

    public double calcularValorTotalTodas() {
        return reservas.stream().mapToDouble(Reserva::calcularValor).sum();
    }
}
