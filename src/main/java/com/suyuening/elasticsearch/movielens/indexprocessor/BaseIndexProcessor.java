package com.suyuening.elasticsearch.movielens.indexprocessor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.suyuening.elasticsearch.processor.ESBulkProcessor;

public abstract class BaseIndexProcessor<T> {

	public void readAndIndexData(String index, String type, String csvFileFullPath) {

		System.out.println(String.format("[index=%s, type=%s] process start!", index, type));

		List<String> jsonLines = Lists.newArrayList();
		long processCount = 1;
		try (BufferedReader reader = new BufferedReader(new FileReader(csvFileFullPath))) {
			String line = null;
			T bean = null;
			while ((line = reader.readLine()) != null) {
				jsonLines.add(JSON.toJSONString(makeDomain(bean, line)));
				if (jsonLines.size() == 150000) {
					process(index, type, jsonLines, processCount);
				}
				processCount++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		if (jsonLines.size() > 0) {
			process(index, type, jsonLines, processCount);
		}

		System.out.println(String.format("[index=%s, type=%s] process end!", index, type));

	}

	private void process(String index, String type, List<String> jsonLines, long processCount) {
		ESBulkProcessor.process(index, type, jsonLines);
		System.out.println(String.format("[index=%s, type=%s] %d processed!", index, type, processCount));
		jsonLines.clear();
	}

	protected abstract T makeDomain(T bean, String line);

}
