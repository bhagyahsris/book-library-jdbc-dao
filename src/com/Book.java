package com;


public class Book {

	int book_id;
	String title;
	boolean borrowed=false;
	
	public int getBook_id() {
		return book_id;
	}
	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isBorrowed() {
		return borrowed;
	}
	public void setBorrowed(boolean borrowed) {
		this.borrowed = borrowed;
	}
	public Book(int book_id, String title) {
		super();
		this.book_id = book_id;
		this.title = title;
	}
	

	
	
	
}
