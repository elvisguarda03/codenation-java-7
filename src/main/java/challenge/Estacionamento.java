package challenge;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Queue;

public class Estacionamento {
    private Queue<Carro> carros = new PriorityQueue<>();

    public void estacionar(Carro carro) {
        if (Objects.isNull(carro.getMotorista()) || carro.getMotorista().getIdade() < 18
                || carro.getMotorista().getPontos() > 20) {
            throw new EstacionamentoException("");
        }

        if (carros.size() < 10) {
            carros.add(carro);

            return;
        }

        if (carros.peek().getMotorista().getIdade() <= 55) {
            carros.remove();
            carros.add(carro);
        }
        else {
            Carro record = carros.stream()
                    .filter(car -> car.getMotorista().getIdade() <= 55)
                    .findFirst()
                    .orElseThrow(() -> new EstacionamentoException("Estacionamento lotado"));

            carros.remove(record);
            carros.add(carro);
        }
    }

    public int carrosEstacionados() {
        return carros.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return carros.contains(carro);
    }
}
