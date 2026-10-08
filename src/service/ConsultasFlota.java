import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public final class ConsultasFlota {

    private ConsultasFlota() {
    }

    public static List<String> idsDisponiblesBateriaSuficiente(List<Drone> drones, int bateriaMinima) {
        Objects.requireNonNull(drones, "drones no puede ser null");
        if (bateriaMinima < 0) {
            throw new IllegalArgumentException("bateriaMinima no puede ser negativa");
        }
        return drones.stream()
                .filter(Drone::disponible)
                .filter(drone -> drone.bateria() >= bateriaMinima)
                .sorted(Comparator.comparingInt(Drone::bateria).reversed())
                .map(Drone::id)
                .toList();
    }

    public static boolean existeDroneDisponibleEn(List<Drone> drones, String bloque) {
        Objects.requireNonNull(drones, "drones no puede ser null");
        Objects.requireNonNull(bloque, "bloque no puede ser null");
        return drones.stream()
                .filter(Drone::disponible)
                .anyMatch(drone -> drone.ubicacion().equals(bloque));
    }

    public static long contarBateriaCritica(List<Drone> drones, int umbralCritico) {
        Objects.requireNonNull(drones, "drones no puede ser null");
        if (umbralCritico < 0) {
            throw new IllegalArgumentException("umbralCritico no puede ser negativo");
        }
        return drones.stream()
                .filter(drone -> drone.bateria() < umbralCritico)
                .count();
    }

    public static List<String> listarIdYBateria(List<Drone> drones) {
        Objects.requireNonNull(drones, "drones no puede ser null");
        return drones.stream()
                .map(drone -> drone.id() + ": " + drone.bateria() + "%")
                .toList();
    }
}
