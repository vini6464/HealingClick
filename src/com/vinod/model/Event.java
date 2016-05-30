package com.vinod.model;

public class Event {

	long id;
	String start;
	String end;
	String title;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Event(long id, String start, String end, String title) {
		super();
		this.id = id;
		this.start = start;
		this.end = end;
		this.title = title;
	}
	public Event() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Event [id=" + id + ", start=" + start + ", end=" + end
				+ ", title=" + title + "]";
	}
	
	
}
