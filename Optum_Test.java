import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Optum_Test {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("a");
        strings.add("b");
        strings.add("c");
        strings.add("c");
        strings.add("a");

        AtomicInteger i = new AtomicInteger(0);
        Map<Object, Object> map1 = strings.stream().collect(Collectors.toMap(x -> i.incrementAndGet(), Function.identity()));
        System.out.println(map1);

        Map<String, Long> map = strings.stream().collect(Collectors.groupingBy(x -> x, Collectors.counting()));
        PriorityQueue<Map.Entry<String, Long>> pq = new PriorityQueue<>((a, b) -> (int) (a.getValue()-b.getValue()));
        for(Map.Entry<String, Long> entry: map.entrySet()) {
            pq.add(entry);
        }

    }
}
