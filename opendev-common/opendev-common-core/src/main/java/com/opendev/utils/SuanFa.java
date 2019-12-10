package com.opendev.utils;

import java.util.Scanner;

public class SuanFa {

    public static void main(String[] args) {
        System.out.println("请输入要获取硬币的数量：");
        Scanner in = new Scanner(System.in);
        int coninCount = in.nextInt();
        StringBuilder sb = new StringBuilder();
        while (coninCount >= 1){
            if (coninCount % 2 == 0){
                coninCount = (coninCount - 2) / 2;
                sb.append("2");
            }else{
                coninCount = (coninCount - 1) / 2;
                sb.append("1");
            }
        }
        System.out.println(sb.reverse());
    }
}
