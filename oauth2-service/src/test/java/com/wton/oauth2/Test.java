package com.wton.oauth2;

public class Test {

    private int n;

    public Test(int n) {
        this.n = n;
    }

    public void foo(Runnable printFoo) throws InterruptedException {

        for (int i = 0; i < n; i++) {


            // printFoo.run() outputs "foo". Do not change or remove this line.
            printFoo.run();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {

        for (int i = 0; i < n; i++) {

            // printBar.run() outputs "bar". Do not change or remove this line.
            printBar.run();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Test test = new Test(10);
        test.foo(() -> System.out.println("foo"));
        test.bar(() -> System.out.println("bar"));
    }

}
