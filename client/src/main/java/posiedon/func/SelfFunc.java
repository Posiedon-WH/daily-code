package posiedon.func;

@FunctionalInterface
public interface SelfFunc<T, U, A, R> {
    R apply(T t, U u, A a);
}
