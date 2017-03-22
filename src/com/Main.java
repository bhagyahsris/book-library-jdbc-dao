package com;

import com.JDBCBookLibraryDAO;


public class Main {

	public static void main(String[] args) {
		
		int i,j;
		Book example = new Book(01,"The Da Vinci Code");
		Book example1 = new Book(02,"Le Petit Prince");
		Book example2 = new Book(03,"A Tale of Two Cities");
		Book example3 = new Book(04,"The Lord of the Rings");
		Book example4 = new Book(05,"The Da Vinci Code");
		example1.setBorrowed(true);
		
		library firstLibrary = new library(01,"fc road"); 
		library secondLibrary = new library(02,"228 Liberty St."); 
		
		firstLibrary.addBook(example);
		firstLibrary.addBook(example1);
		secondLibrary.addBook(example4);
		secondLibrary.addBook(example2);
		secondLibrary.addBook(example3);
		
		JDBCBookLibraryDAO jdbc=new JDBCBookLibraryDAO();
		jdbc.getConnection();
		jdbc.insertIntoBook(example);
		jdbc.insertIntoBook(example1);
		jdbc.insertIntoBook(example2);
		jdbc.insertIntoBook(example3);
		jdbc.insertIntoBook(example4);
		
		jdbc.insertIntoLib(firstLibrary);
		jdbc.insertIntoLib(secondLibrary);
		
		for( i=0;i<firstLibrary.getBooks().size();i++)
		{
			jdbc.insertBookLib(firstLibrary.getL_id(), firstLibrary.getBooks().get(i).getBook_id());
		}
		if(i==firstLibrary.getBooks().size())
		{
			System.out.println("transaction completed");
			jdbc.commited();
		}
		else
		{
			System.out.println("transaction not completed");
			jdbc.rollbacked();
		}
		for( j=0;j<secondLibrary.getBooks().size();j++)
		{
			jdbc.insertBookLib(secondLibrary.getL_id(), secondLibrary.getBooks().get(j).getBook_id());
		}
		if(j==secondLibrary.getBooks().size())
		{
			System.out.println("transaction completed");
			jdbc.commited();
		}
		else
		{
			System.out.println("transaction not completed");
			jdbc.rollbacked();
		}
		System.out.println("Books available in the first library:");
		jdbc.printAvailableBooks(firstLibrary.getL_id());
		System.out.println();
		jdbc.borrowBook("The Da Vinci Code",firstLibrary.getL_id());
		
		System.out.println();
		jdbc.printAvailableBooks(firstLibrary.getL_id());
		
		System.out.println();
		jdbc.returnBook("Le Petit Prince",firstLibrary.getL_id());
		
		System.out.println();
		System.out.println("Books available in the first library:");
		jdbc.printAvailableBooks(firstLibrary.getL_id());
		
		System.out.println();
		System.out.println("Books available in the second library:");
		jdbc.printAvailableBooks(secondLibrary.getL_id());
		
		System.out.println();
		jdbc.borrowBook("The Da Vinci Code",secondLibrary.getL_id());
		
		
		System.out.println();
		jdbc.borrowBook("Le Petit Prince",secondLibrary.getL_id());
		System.out.println();
		jdbc.returnBook("The Da Vinci Code",secondLibrary.getL_id());
		System.out.println();
		System.out.println("Books available in the second library:");
		jdbc.printAvailableBooks(secondLibrary.getL_id());
		
		
		System.out.println("Library addresses:");
		firstLibrary.printAddress(firstLibrary);
		secondLibrary.printAddress(secondLibrary);
		System.out.println();
		System.out.println("Library hours:");
		library.printOpeningHours();
		
		System.out.println();
		
		jdbc.closeConnection();
	}

}
