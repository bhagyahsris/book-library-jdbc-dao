package com;


import com.Book;
import com.library;

public interface libraryDAOInterface {

	public void insertIntoLib(library l);
	public void insertBookLib(int l,int b);
	public void printAvailableBooks(int id);
	public void borrowBook(String title,int id1);
	public void returnBook(String title,int id);
	
	
	
}
