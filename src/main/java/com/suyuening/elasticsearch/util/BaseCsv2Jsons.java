package com.suyuening.elasticsearch.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;

public abstract class BaseCsv2Jsons<T> {

    public List<String> csv2JsonLines(String csvFileFullPath) {
        List<T> list = readData(csvFileFullPath);

        List<String> jsonLines = Lists.newArrayList();
        for (T bean : list) {
            jsonLines.add(JSON.toJSONString(bean));
        }

        return jsonLines;
    }

    private List<T> readData(String csvFileFullPath) {
        List<String> lines = null;
        try {
            lines = FileUtils.readLines(new File(csvFileFullPath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<T> list = Lists.newArrayList();
        T t = null;
        for (String line : lines) {
            String[] array = line.split(",");
            list.add(makeDomain(t, array));
        }
        return list;
    }

    protected abstract T makeDomain(T bean, String[] array);

}
