package com.wddlhyss.gulischool.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.Map;

public class ExcelLixten extends AnalysisEventListener<DemoWrite> {
    //一行一行读取
    @Override
    public void invoke(DemoWrite demoDate, AnalysisContext analysisContext) {
        System.out.println("*********"+ demoDate);
    }
    //读取表头
    @Override
    public void invokeHeadMap(Map<Integer,String> headMap, AnalysisContext context){
        System.out.println("表头"+headMap);
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
