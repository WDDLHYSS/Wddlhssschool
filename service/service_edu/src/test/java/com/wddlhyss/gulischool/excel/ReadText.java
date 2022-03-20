package com.wddlhyss.gulischool.excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

public class ReadText {


    public static void main(String[] args) {
        /*//实现excel写操作
        String filename = "D:\\下载\\文档\\在线教育--谷粒学院\\01.xlsx";

        EasyExcel.write(filename, DemoWrite.class).sheet("学校横列吧").doWrite(get());*/

        //读操作
        String filename = "D:\\下载\\文档\\在线教育--谷粒学院\\01.xlsx";
        EasyExcel.read(filename,DemoWrite.class,new ExcelLixten()).sheet().doRead();
    }

    private  static List<DemoWrite> get(){

        List<DemoWrite> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            DemoWrite demoRead = new DemoWrite();
            demoRead.setSno(i);
            demoRead.setSname("lucy"+i);
            list.add(demoRead);
        }
        return list;
    }
}
