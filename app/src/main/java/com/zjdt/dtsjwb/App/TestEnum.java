package com.zjdt.dtsjwb.App;

public enum TestEnum implements Ceo{//枚举就是一个 多final对象

    Z("momoda") {
        @Override
        public void cacui() {

        }

        @Override
        public void doIt(int position) {

        }

        @Override
        public double eval(double x, double y) {
            return 0;
        }
    },T("xixiha"){
        @Override
        public void cacui() {

        }

        @Override
        public void doIt(int position) {

        }

        @Override
        public double eval(double x, double y) {
            return 0;
        }
    };
    private final String arr;

    TestEnum(String arr) {
        this.arr = arr;
    }
  /*  public interface Ceo{
        void cacui();
        void doIt(int position);
    }

    不能继承或是实现自己的子类
*/
    public abstract double eval(double x, double y);

}
