package com.longsheng.trader;

import java.util.List;

public class ListResult<T> extends BaseResult {
	private List<T> results;

	public ListResult() {
	}
	
	public ListResult(List<T> results) {
		this.results = results;
	}
	
	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}
	
}
