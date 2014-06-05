package com.glp.message.result;

import java.util.List;

public class SearchResult<T> {

	int total;
	List<T> results;
	
	List<FacetCount> facets;
	
	public static class  FacetCount{
		String term;
		int count;
		public void setCount(int count) {
			this.count = count;
		}
		public void setTerm(String term) {
			this.term = term;
		}
		public int getCount() {
			return count;
		}
		public String getTerm() {
			return term;
		}
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public List<T> getResults() {
		return results;
	}

	public void setResults(List<T> results) {
		this.results = results;
	}

	public List<FacetCount> getFacets() {
		return facets;
	}

	public void setFacets(List<FacetCount> facets) {
		this.facets = facets;
	}
	
	
}
