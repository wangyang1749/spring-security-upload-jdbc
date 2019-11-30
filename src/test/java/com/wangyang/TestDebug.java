package com.wangyang;

public class TestDebug {
    int a=0;
    public void  A(){
        a=50;
        B();
    }
    public void  B(){
        a=10;
    }

    public static void main(String[] args) {
        new TestDebug().A();
    }
}
