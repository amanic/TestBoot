package com.cht.testspringboot._8_new;

/**
 * @auther chen.haitao
 * @date 2019-03-29
 */
public abstract class MyExecutor {//这个可以当成BaseController


    void doExecute(MyInterfaceExecutor executor){
        executor.execute();
        System.out.println("");
    }


    @FunctionalInterface
    interface MyInterfaceExecutor {

        void execute();

    }

class ExecutorImpl extends MyExecutor{//这个可以当成继承于BaseController的具体业务controller

        //dojob和dojob1是一样的，使用了lambda表达式。


        public void doJob(){
            doExecute(()->{
                //这里放service业务
            });
        }

        public void doJob1(){
            doExecute(new MyInterfaceExecutor() {
                @Override
                public void execute() {
                    //这里放service的业务
                }
            });
        }
}

}
