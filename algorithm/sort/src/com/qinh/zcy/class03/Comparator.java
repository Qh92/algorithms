package com.qinh.zcy.class03;

/**
 * 比较器
 *
 * @author Qh
 * @version 1.0
 * @date 2022-01-10 23:30
 */
public class Comparator {


    public static class Student {
        String name;
        Integer id;
        Integer age;

        public Student(String name, Integer id, Integer age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }


    public static class IdAscendingComparator implements java.util.Comparator<Student> {

        /**
         * 返回负数的时候，第一个参数排在前面
         * 返回正数的时候，第二个参数排在前面
         * 返回0的时候，谁在前面无所谓
         * @param o1
         * @param o2
         * @return
         */
        @Override
        public int compare(Student o1, Student o2) {
            /*if (o1.id < o2.id) {
                return -1;
            }
            if (o2.id < o1.id) {
                return 1;
            }
            return 0;*/

            return o1.id - o2.id;
        }
    }
}
