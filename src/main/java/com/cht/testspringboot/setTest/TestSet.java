package com.cht.testspringboot.setTest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 关于第一点，相等（相同）的对象必须具有相等的哈希码（或者散列码），为什么？
 *
 *  想象一下，假如两个Java对象A和B，A和B相等（eqauls结果为true），但A和B的哈希码不同，则A和B存入HashMap时的哈希码计算得到的HashMap内部数组位置索引可能不同，那么A和B很有可能允许同时存入HashMap，显然相等/相同的元素是不允许同时存入HashMap，HashMap不允许存放重复元素。
 *
 *
 *
 *  关于第二点，两个对象的hashCode相同，它们并不一定相同
 *
 *  也就是说，不同对象的hashCode可能相同；假如两个Java对象A和B，A和B不相等（eqauls结果为false），但A和B的哈希码相等，将A和B都存入HashMap时会发生哈希冲突，也就是A和B存放在HashMap内部数组的位置索引相同这时HashMap会在该位置建立一个链接表，将A和B串起来放在该位置，显然，该情况不违反HashMap的使用原则，是允许的。当然，哈希冲突越少越好，尽量采用好的哈希算法以避免哈希冲突。
 * @auther chen.haitao
 * @date 2019-03-12
 */
public class TestSet {

    public static void main(String[] args) {

        A a = new A(1, 2);

        A b = new A(3, 2);

        A c = new A(1, 3);

        System.out.println("a.equals(b): "+a.equals(b));
        System.out.println("a.equals(c): "+a.equals(c));
        System.out.println("a.hashCode(): "+a.hashCode()+"\r\nb.hashCode(): "+b.hashCode());

        Map<A,String> map = new HashMap<>();
        map.put(a,"a");
        map.put(b,"b");
        map.put(c,"c");
        System.out.println(map);
        Map<A, String> unmodifiableMap = Collections.unmodifiableMap(map);
        unmodifiableMap.put(new A(10,10),"new");
        System.out.println(unmodifiableMap);

    }

    static class A {

        private Integer a;

        private Integer b;


        public A(Integer a, Integer b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int hashCode() {
            return a;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof A) {
//                if (((A) obj).b.equals(this.b)) {
                if (((A) obj).hashCode()==this.hashCode()) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return "A{" +
                    "a=" + a +
                    ", b=" + b +
                    '}';
        }
    }
}
