package com.copysun.functioninterface.demo;

/**
 * @author copysun
 */
public class TestFunction {

    public static void main(String[] args) {
//        showStr("haha",System.out::println);

//        showInt(10,System.out::println);

//        System.out.println(getStr("这是前缀！",()->"产生一个字符串！"));
//
//        System.out.println(getInt(10,()->20));

        System.out.println(judgeStr("111",(x)->x.contains("2")));

    }


    public static void showStr(String str, XiaoFeiFunction<String> xiaoFeiFunction){
        xiaoFeiFunction.onlyUse(str);
    }

    public static void showInt(Integer i, XiaoFeiFunction<Integer> xiaoFeiFunction){
        xiaoFeiFunction.onlyUse(i);
    }

    public static String getStr(String str,GongGiFunction<String> gongGiFunction){
        return str+gongGiFunction.produce();
    }

    public static Integer getInt(Integer integer,GongGiFunction<Integer> gongGiFunction){
        return Integer.sum(integer,gongGiFunction.produce());
    }

    public static boolean judgeStr(String str,DuanYanFunction<String> duanYanFunction){
           return  duanYanFunction.judge(str);
    }


}
