package frc.simulacion.elmejorgrupo.tpcinco.util;

public class CustomPair<K, V> {
    private final K key;
    private final V value;

    public CustomPair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public static void main(String[] args) {
        CustomPair<Integer, String> customPair = new CustomPair<>(1, "One");
        //System.out.println("Key: " + customPair.getKey() + ", Value: " + customPair.getValue());
    }
}