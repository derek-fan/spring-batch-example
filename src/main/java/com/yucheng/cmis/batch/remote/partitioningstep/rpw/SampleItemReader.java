package com.yucheng.cmis.batch.remote.partitioningstep.rpw;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class SampleItemReader implements ItemReader<String> {

	public volatile int count = 0;
	public int partSize = 10;
	private String currentPart;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (partSize > count) {
			count++;
			String item = currentPart + " -> " + count;
			System.out.println("******** reading item " + item);
			return item;
		} else {
			return null;
		}

	}

	public String getCurrentPart() {
		return currentPart;
	}

	public void setCurrentPart(String currentPart) {
		this.currentPart = currentPart;
	}

}
