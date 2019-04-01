package com.cht.testspringboot._8_new.streamsTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @auther chen.haitao
 * @date 2019-04-01
 */
public class Main {

    public static void main(String[] args) {

        List<Entity> entities = new ArrayList<>();


        for (int i = 0; i < 10; i++) {
            Entity entity = new Entity(i, i + "", "");
            entities.add(entity);
        }


        entities.stream().filter((a) -> a.getName().equals("")).forEach((b) -> {
            System.out.println();
        });

        //map：对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素
        Stream<String> stringStream = entities.stream().map(a -> {
            return a.getName();
        });

        //count()是对列表的聚合，统计列表的数量。
        long count = entities.stream().count();
        System.out.println(count);

        //对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素
        entities.stream().filter(a -> {
            return a.getName().equals("");
        });

        //distinct是对stream里面的元素进行去重，有点类似于SQL里面的distinct去重
        //但是这里有点扩展，如果比较的是对象，就要做特殊处理：https://juejin.im/entry/5af2dd225188253dc612b128
        entities.add(new Entity(10, "9", ""));
        entities.stream().distinct();

        //sorted来对列表来进行排序，limit用来取前n个结果
        entities.stream().sorted((o1, o2) -> {
            return o2.getId() - o1.getId();
        }).limit(5).forEach(a -> System.out.println(a.getId()));


        //这里是转换成map
        Map<Integer, Entity> entityMap = entities.stream().collect(Collectors.toMap(Entity::getId, Function.identity()));


        //TOMAP操作
        List<String> items = Arrays.asList("Apple", "Apple", "orange", "Apple", "orange", "banana", "papaya");
        Map<String, Long> result = items
                .stream()
                .collect(Collectors
                        .groupingBy(Function.identity(), Collectors.counting()));


        List<ListDTO> beans = Arrays.asList(
                new ListDTO(1, "孙博"), new ListDTO(1, "二代"), new ListDTO(1, "孙博"),
                new ListDTO(2, "戴硕"), new ListDTO(2, "戴硕"), new ListDTO(2, "赛克"),
                new ListDTO(3, "二代"), new ListDTO(3, "路痴"), new ListDTO(3, "路痴"),
                new ListDTO(4, "赛克"), new ListDTO(4, "二代"), new ListDTO(4, "路痴")
        );


        Map<Integer,List<ListDTO>> listMap = beans.stream().collect(
                Collectors.groupingBy(ListDTO::getId));

        System.out.println(listMap);


    }

    static class ListDTO {
        public Integer id;
        public String name;

        public ListDTO() {
        }

        public ListDTO(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "ListDTO{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
