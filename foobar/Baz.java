package foobar;

public class Baz extends Foo {
    @Override
    public void method1() {
        System.out.println("baz 1");
    }

    @Override
    public String toString() {
        return "baz";
    }
}
