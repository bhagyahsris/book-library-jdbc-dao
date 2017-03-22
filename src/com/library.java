package com;


import com.Book;

import java.util.ArrayList;

public class library {

	int l_id;
	ArrayList <Book> books;
	String address;
	static String open_time="09:00:00";
	static String closing_time="17:00:00";
	public int getL_id() {
		return l_id;
	}
	public void setL_id(int l_id) {
		this.l_id = l_id;
	}
	public ArrayList<Book> getBooks() {
		return books;
	}
	public void setBooks(ArrayList<Book> books) {
		this.books = books;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static String getOpen_time() {
		return open_time;
	}
	public static void setOpen_time(String open_time) {
		library.open_time = open_time;
	}
	public static String getClosing_time() {
		return closing_time;
	}
	public static void setClosing_time(String closing_time) {
		library.closing_time = closing_time;
	}
	public library(int l_id, String address) {
		super();
		this.l_id = l_id;
		this.address = address;
		books=new ArrayList<Book>();
	}
	public void addBook(Book b) {
		books.add(b);
		
	}
	public void printAddress(library l) {
		System.out.println(l.getAddress());
		
	}
	public static void printOpeningHours()
	{
		System.out.println(open_time +"to"+ closing_time);
	}
	
	
}
