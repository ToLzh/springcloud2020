import java.util.concurrent.atomic.AtomicInteger;

public class MyTest {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        System.out.println(atomicInteger.get());
        int current = atomicInteger.get();
        int next = current + 2;
        System.out.println(atomicInteger.compareAndSet(current,next));
        System.out.println(current);
    }
}
