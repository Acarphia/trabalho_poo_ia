import java.util.concurrent.atomic.AtomicInteger;

public abstract class Reserva {
    private static final AtomicInteger COUNTER = new AtomicInteger(1000);
    protected final int codigo;
    protected String clienteNome;
    protected double valorBase;
    protected boolean cancelada = false;

    public Reserva(String clienteNome, double valorBase) {
        if (clienteNome == null || clienteNome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do cliente não pode ser vazio.");
        }
        if (valorBase < 0) {
            throw new IllegalArgumentException("Valor não pode ser negativo.");
        }
        this.codigo = COUNTER.getAndIncrement();
        this.clienteNome = clienteNome;
        this.valorBase = valorBase;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getClienteNome() {
        return clienteNome;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public void cancelar() {
        this.cancelada = true;
    }

    public abstract double calcularValor();

    @Override
    public String toString() {
        return String.format("[#%d] %s - R$ %.2f%s", codigo, clienteNome, calcularValor(), (cancelada ? " (CANCELADA)" : ""));
    }
}
