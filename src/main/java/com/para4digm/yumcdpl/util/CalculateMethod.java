package com.para4digm.yumcdpl.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculateMethod {

    static char add = '+';

    static char del = '-';

    static char mul = '*';

    static char div = '/';

    public static double calculate(String s){


        StringBuffer sbMath = new StringBuffer();
        List<String> math = new ArrayList<String>();
        List<String> flag = new ArrayList<String>();
        List<Integer> mulDiv = new ArrayList<Integer>();
        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(temp!= add && temp!= del && temp!=mul && temp!=div){
                sbMath.append(String.valueOf(temp));
            }else{
                if(sbMath.length()==0 && temp==del){
                    sbMath.append("0");
                }
                math.add(sbMath.toString());
                sbMath.delete(0, sbMath.length());
                flag.add(String.valueOf(temp));
                if(temp == mul || temp == div){
                    mulDiv.add(flag.size()-1);
                }
            }
        }
        math.add(sbMath.toString());
        while(math.size() != 1){
            boolean needReIndex = false;
            while(mulDiv.size() != 0){
                int index = mulDiv.get(0);
                if(needReIndex){
                    index = index -1;
                }
                Map<String, List<String>> map = loopProcess(index, math, flag);
                math = map.get("math");
                flag = map.get("flag");
                mulDiv = removeList(Integer.class, mulDiv, 0);
                needReIndex = true;
            }
            while(flag.size() != 0){
                Map<String, List<String>> map = loopProcess(0, math, flag);
                math = map.get("math");
                flag = map.get("flag");
            }
        }
        return Double.parseDouble(math.get(0));
    }

    private static Map<String, List<String>> loopProcess(int index, List<String> math, List<String> flag){
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        char ch = flag.get(index).charAt(0);
        String result = getResult(math.get(index).trim(), math.get(index+1).trim(), ch);
        math = removeList(String.class, math, index);
        math = removeList(String.class, math, index);
        math.add(index, result);
        flag = removeList(String.class, flag, index);
        map.put("math", math);
        map.put("flag", flag);
        return map;
    }

    private static <T> List<T> removeList(Class<T> clazz, List<T> list, int index){
        List<T> listTemp = new ArrayList<T>();
        for (int i = 0; i < list.size(); i++) {
            if(i != index){
                listTemp.add(list.get(i));
            }
        }
        return listTemp;
    }

    private static String getResult(String b, String e, char flag){
        boolean isLong = false;
        if(!b.contains(".") && !e.contains(".")){
            isLong = true;
        }
        if(isLong){
            if(flag == add){
                return String.valueOf(Long.valueOf(b)+Long.valueOf(e));
            }else if(flag == del){
                return String.valueOf(Long.valueOf(b)-Long.valueOf(e));
            }else if(flag == mul){
                return String.valueOf(Long.valueOf(b)*Long.valueOf(e));
            }else if(flag == div){
                return String.valueOf((double)Long.valueOf(b)/Long.valueOf(e));
            }else{
                throw new RuntimeException("error: "+ b + flag + e);
            }
        }else{
            if(flag == add){
                return String.valueOf(Double.valueOf(b)+Double.valueOf(e));
            }else if(flag == del){
                return String.valueOf(Double.valueOf(b)-Double.valueOf(e));
            }else if(flag == mul){
                return String.valueOf(Double.valueOf(b)*Double.valueOf(e));
            }else if(flag == div){
                return String.valueOf((double)Double.valueOf(b)/Double.valueOf(e));
            }else{
                throw new RuntimeException("error: "+ b + flag + e);
            }
        }

    }

    public static double negative(String s){

        for (int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            if(temp == add || temp == del ||  temp==mul ||  temp==div){
              int a = s.indexOf(String.valueOf(temp));
              String LoseMoney = s.substring(0,a-1);
              Double b = Double.parseDouble(LoseMoney);
              return b;
            }
        }

        return 0;
    }

//    public static void main(String[] args) {
//
//        String s = "1000 * 10 -1000 +10000";
//
//        Double b = negative(s);
//        System.out.println(b);
//
//
//    }
}
