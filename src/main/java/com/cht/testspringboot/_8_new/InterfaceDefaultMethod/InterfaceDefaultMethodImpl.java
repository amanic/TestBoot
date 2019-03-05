package com.cht.testspringboot._8_new.InterfaceDefaultMethod;

/**由于这两个接口有相同的方法，因此就会产生冲突，不知道以哪个接口中的run方法为准，编译会出错：inherits unrelated defaults for run.....
 * 重写即可
 * @auther chen.haitao
 * @date 2019-03-05
 */
public class InterfaceDefaultMethodImpl implements InterfaceDefaultMethod,AnotherInterfaceDefaultMethod{


    @Override
    public void test1() {
        InterfaceDefaultMethod.super.test1();
    }

    @Override
    public void test2() {

    }


}
