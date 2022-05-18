package mvc.librarySystem;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LibraryDAO {

	private Connection con;
	private String FName;
	private String LName;
	private String CName;
	private int StdId ;
	private String Password;
	private int UserId ;
	private String Bookname;
	private String Author;
	private String Description;
	Connect c=new Connect();
	
	public String insertAllrows(String FName,String LName,String CName,String Password) throws SQLException, ClassNotFoundException {
		this.FName=FName;
		this.LName=LName;
		this.CName=CName;
		//this.StdId=StdId;
		this.Password=Password;
		String Insert;
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into library_system(firstname,lastname,coursename,password)  values (?,?,?,?)");
		ps.setString(1, FName);
		ps.setString(2, LName);
		ps.setString(3, CName);
		//ps.setInt(4, StdId);
		ps.setString(4, Password);
		ps.executeUpdate();
		Insert="Created Account Successfully";
		con.close();
		return Insert;
	}
	
	
	
	public String login(int userid, String password2) throws ClassNotFoundException, SQLException {
		
		this.UserId=userid;
		this.Password=password2;
		int Count=0;
		int Count1=0;
		String Login = null;
		String Fullname = null;
		Connection con=c.getConnection();
		Statement stat = con.createStatement();
	    ResultSet res = stat.executeQuery("select * from library_system");
	    
	    for(;res.next();) {
	    	if(UserId == res.getInt(4)) {
	    		Count1=1;
	    		if(Password.equals(res.getString(5))) {
	    			this.FName=res.getString(1);
	    			this.LName=res.getString(2);
	    			Fullname =FName+" "+LName;
				Count=1;
				break;
	    		}
			}
	    }
	    if(Count==1) {
	    	Login=Fullname;
	    }
	    else if(Count==0 && Count1==1) {
	    	Login="wp";
	    }
	    else if(Count==0 && Count1==0) {
	    	Login="ca";
	    }
	    con.close();
		return Login;
	}
	
	
	
	public int  forgetPassword(int studentid, String password2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		this.StdId=studentid;
		this.Password=password2;
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("update library_system  set password=? where UserId=?");
		ps.setString(1, Password);
		ps.setInt(2, StdId);
		int affectedRows =ps.executeUpdate();
		con.close();
		return affectedRows;
	}



	public void addbooks(String bookname, String author, String description, int noOfBooks) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		this.Bookname=bookname;
		this.Author=author;
		this.Description=description;
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into books  values (?,?,?,?)");
		ps.setString(1, Bookname);
		ps.setString(2, Author);
		ps.setString(3, Description);
		ps.setInt(4, noOfBooks);
	    ps.executeUpdate();
	    con.close();
	}



	public ResultSet DisplayBooks() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con=c.getConnection();
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM books");
		return result;
	}



	public void DeleteBook(String bookname2, String author2) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		this.Bookname=bookname2;
		this.Author=author2;
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("Delete from books  where bookname=? and author=?");
		ps.setString(1, Bookname);
		ps.setString(2, Author);
	    ps.executeUpdate();
	    con.close();
	}



	public void issueBook(int current_userid, String bookname2, String author2, LocalDate todayDate,
			LocalDate returnDate, int price, String status, int availBook) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		java.sql.Date date = java.sql.Date.valueOf(todayDate);
		java.sql.Date date1 = java.sql.Date.valueOf(returnDate);
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("insert into issue_books(userId,bookname,author,issue_date,return_date,price,status)  values (?,?,?,?,?,?,?)");
		ps.setInt(1, current_userid);
		ps.setString(2, bookname2);
		ps.setString(3, author2);
		ps.setDate(4, date);
		ps.setDate(5, date1);
		ps.setInt(6, price);
		ps.setString(7, status);
	    ps.executeUpdate();
	    int availBook2=availBook-1;
	    PreparedStatement ps2 = con.prepareStatement("update books set noOfBooks=? where bookname=? and author=?");
	    ps2.setInt(1, availBook2);
	    ps2.setString(2, bookname2);
		ps2.setString(3, author2);
		ps2.executeUpdate();
	    con.close();
	}



	public ResultSet DisplayIssueBooks(int current_userid) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		this.UserId=current_userid;
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT bookname,author,issue_date,return_date,price,status FROM issue_books where userId=?");
		ps.setInt(1, UserId);
		ResultSet result = ps.executeQuery();
		return result;
	}



	public ResultSet DisplayStudentDetails() throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con=c.getConnection();
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery("SELECT userId,bookname,author,price,status,action FROM issue_books");
		return result;
	}



	public ResultSet GetStudentId() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/library_system", "root", "csir@123");
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery("SELECT max(userId) FROM library_system");
		return result;
	}



	public void CalculatePrice() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con=c.getConnection();
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery("SELECT return_date,price,userId,bookname,author FROM issue_books");
		while(result.next()) {
			Date ReturnDate=result.getDate(1);
			int Price=result.getInt(2);
			int UserId=result.getInt(3);
			String Bookname=result.getString(4);
			String Author=result.getString(5);
			LocalDate todayDate =  LocalDate.now();
			LocalDate date=ReturnDate.toLocalDate();
		    int check = (int) date.until(todayDate, ChronoUnit.DAYS);
			//System.out.println(check);
			if(check>0) {
				Price=check;
				PreparedStatement ps = con.prepareStatement("update issue_books  set price=? where userId=? and bookname=? and return_date=? and author=?");
				ps.setInt(1, Price);
				ps.setInt(2, UserId);
				ps.setString(3, Bookname);
				ps.setDate(4, ReturnDate);
				ps.setString(5, Author);
				ps.executeUpdate();
				
			}
			else {
				System.out.print(check);
			}
			}
	}



	public String BookStatus(String Bookname, String Author, int current_userid) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		Connection con=c.getConnection();;
		Statement stmt = con.createStatement();
		PreparedStatement ps = con.prepareStatement("SELECT status FROM issue_books where bookname=? and author=? and userId=?");
		ps.setString(1, Bookname);
		ps.setString(2, Author);
		ps.setInt(3, current_userid);
		ResultSet result = ps.executeQuery();
		String Status = "Nottaken";
		while(result.next()) {
			Status=result.getString(1);
		}
		return Status;
	}



	public void ReturnBook(int current_userid, String Bookname, String Author) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String Returnbook= "Return Request";
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("update issue_books  set action=? where userId=? and bookname=?  and author=?");
		ps.setString(1, Returnbook);
		ps.setInt(2, UserId);
		ps.setString(3, Bookname);
		ps.setString(4, Author);
		ps.executeUpdate();
	}



	public String RenewBook(int current_userid, String Bookname, String Author, LocalDate todayDate,
			LocalDate returnDate) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		String Renewbook= "Renew Request";
		String Feedback;
		Connection con=c.getConnection();
		PreparedStatement ps2 = con.prepareStatement("SELECT price FROM issue_books where bookname=? and author=? and userId=?");
		ps2.setString(1, Bookname);
		ps2.setString(2, Author);
		ps2.setInt(3, current_userid);
		ResultSet result = ps2.executeQuery();
		int Price=0;
		while(result.next()) {
			Price=result.getInt(1);
		}
		if(Price>0) {
		PreparedStatement ps = con.prepareStatement("update issue_books  set action=? where userId=? and bookname=?  and author=?");
		ps.setString(1, Renewbook);
		ps.setInt(2, current_userid);
		ps.setString(3, Bookname);
		ps.setString(4, Author);
		ps.executeUpdate();
		Feedback="have Price more than zero";
		}
		else {
			java.sql.Date date = java.sql.Date.valueOf(todayDate);
			java.sql.Date date1 = java.sql.Date.valueOf(returnDate);
			String Status="Renewed";
			Connection con2=c.getConnection();
			PreparedStatement ps = con2.prepareStatement("update issue_books  set issue_date=?,return_date=?,status=?  where userId=? and bookname=?  and author=?");
			ps.setDate(1, date);
			ps.setDate(2, date1);
			ps.setString(3, Status);
			ps.setInt(4, current_userid);
			ps.setString(5, Bookname);
			ps.setString(6, Author);
			ps.executeUpdate();
			Feedback="Renewed";
		}
		return Feedback;
	}



	public void ReturnRequest(int studentId, String Bookname, String Author, int availBook) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("delete from issue_books where userId=? and bookname=?  and author=?");
		ps.setInt(1, studentId);
		ps.setString(2, Bookname);
		ps.setString(3, Author);
		ps.executeUpdate();
		int availBook2=availBook+1;
		    PreparedStatement ps2 = con.prepareStatement("update books set noOfBooks=? where bookname=? and author=?");
		    ps2.setInt(1, availBook2);
		    ps2.setString(2, Bookname);
			ps2.setString(3, Author);
			ps2.executeUpdate();
	}



	public void RenewRequest(int studentId, String Bookname, String Author, LocalDate todayDate,
			LocalDate returnDate) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		java.sql.Date date = java.sql.Date.valueOf(todayDate);
		java.sql.Date date1 = java.sql.Date.valueOf(returnDate);
		String Action="NoRequest";
		String Status="Renewed";
		int Price=0;
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("update issue_books  set issue_date=?,return_date=?,action=?,status=?,price=? where userId=? and bookname=?  and author=?");
		ps.setDate(1, date);
		ps.setDate(2, date1);
		ps.setString(3, Action);
		ps.setString(4, Status);
		ps.setInt(5, Price);
		ps.setInt(6, studentId);
		ps.setString(7, Bookname);
		ps.setString(8, Author);
		ps.executeUpdate();
	}



	public int NoOfBooks(String Bookname, String Author) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		int count=0;
		Connection con=c.getConnection();
		PreparedStatement ps = con.prepareStatement("SELECT noOfBooks FROM books where bookname=? and author=?");
		ps.setString(1, Bookname);
		ps.setString(2, Author);
		ResultSet result = ps.executeQuery();
		while(result.next()) {
			count=result.getInt(1);
		}
		return count;
	}
	
	
}







