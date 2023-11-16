package com.cst438.domain;

import java.util.List;

public class TriviaApiResponse {
    private int response_code;
    private List<BooleanQuestion> results;
    
	public int getResponse_code() {
		return response_code;
	}
	public void setResponse_code(int response_code) {
		this.response_code = response_code;
	}
	public List<BooleanQuestion> getResults() {
		return results;
	}
	public void setResults(List<BooleanQuestion> results) {
		this.results = results;
	}


}