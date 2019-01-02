package com.shail.musicfinder.musicFinder.objects;

public class Results {
	
	private QueryLastfm query;
	private String totalResults;
	private String startIndex;
	private String itemsPerPage;
	private Tracks trackMatches;
	public QueryLastfm getQuery() {
		return query;
	}
	public void setQuery(QueryLastfm query) {
		this.query = query;
	}
	public String getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
	public String getStartIndex() {
		return startIndex;
	}
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	public String getItemsPerPage() {
		return itemsPerPage;
	}
	public void setItemsPerPage(String itemsPerPage) {
		this.itemsPerPage = itemsPerPage;
	}
	public Tracks getTrackMatches() {
		return trackMatches;
	}
	public void setTrackMatches(Tracks trackMatches) {
		this.trackMatches = trackMatches;
	}
	

}
