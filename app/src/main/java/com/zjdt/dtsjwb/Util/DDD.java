package com.zjdt.dtsjwb.Util;

public abstract class DDD {

    int x;
    int y;

    public DDD(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract void ou();
    public void m(){
        DDD ddd=new DDD(1,2) {
            @Override
            void ou() {

            }
        };
    }
    class Ha extends DDD{

        public Ha(int x, int y) {
            super(x, y);
        }//abstruct 最好指定构造器 super

        @Override
        void ou() {

        }
    }
}
