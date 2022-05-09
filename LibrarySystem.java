package mvc.librarySystem;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.swing.JOptionPane;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class LibrarySystem {

	LibraryDAO obj1 = new LibraryDAO();
	int Current_userid;
	String Display; 
	
	@RequestMapping("/home")
	public String ShowBooks(HttpServletRequest req,Model model) throws ClassNotFoundException, SQLException {
		ResultSet result2=obj1.GetStudentId();
		while(result2.next()) {
	   if(result2.getString(1)==null) {
			model.addAttribute("StudentId", "184100");
		}
		else {
			int num=Integer.parseInt(result2.getString(1));
		model.addAttribute("StudentId", (num+1));
		}
	}
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "index";
	}
	@RequestMapping("/index")
	public String createAccount(HttpServletRequest req,Model model) throws ClassNotFoundException, SQLException {
		String Firstname = req.getParameter("firstname");
		String Lastname = req.getParameter("lastname");
		String Coursename = req.getParameter("coursename");
		//int Studentid = Integer.parseInt(req.getParameter("studentid"));
		String Password = req.getParameter("password");
		String Display=obj1.insertAllrows(Firstname, Lastname, Coursename, Password);
		System.out.print(Display);
			ResultSet result2=obj1.GetStudentId();
			while(result2.next()) {
			int num=Integer.parseInt(result2.getString(1));
			model.addAttribute("StudentId", (num+1));
			}
			ResultSet result=obj1.DisplayBooks();
			List<LibraryDTO> list=new ArrayList<LibraryDTO>();
			while(result.next()) {
			LibraryDTO obj2 =new LibraryDTO();
			obj2.setBookname(result.getString(1));
			obj2.setAuthor(result.getString(2));
			obj2.setDescription(result.getString(3));
			list.add(obj2);
			model.addAttribute("list", list);
			}
		return "index";
	}

	
	@RequestMapping("/index2")
	public String loginAccount(HttpServletRequest req,Model model) throws ClassNotFoundException, SQLException {
		int Userid = Integer.parseInt(req.getParameter("userid"));
		String Password = req.getParameter("pass");
	    Display=obj1.login(Userid, Password);
		System.out.println(Display);
		if(Display.equals("wp")) {
			model.addAttribute("CallModel", "clickButton");
			model.addAttribute("Display1", "Wrong Password!!!!!!!!");
			ResultSet result2=obj1.GetStudentId();
			while(result2.next()) {
			int num=Integer.parseInt(result2.getString(1));
			model.addAttribute("StudentId", (num+1));
			}
			ResultSet result=obj1.DisplayBooks();
			List<LibraryDTO> list=new ArrayList<LibraryDTO>();
			while(result.next()) {
			LibraryDTO obj2 =new LibraryDTO();
			obj2.setBookname(result.getString(1));
			obj2.setAuthor(result.getString(2));
			obj2.setDescription(result.getString(3));
			list.add(obj2);
			model.addAttribute("list", list);
			}
			return "index";
		}
		else if(Display.equals("ca")) {
			model.addAttribute("CallModel", "clickButton");
			model.addAttribute("Display2", "You don't have any Account!! Create a Account");
			ResultSet result2=obj1.GetStudentId();
			while(result2.next()) {
			int num=Integer.parseInt(result2.getString(1));
			model.addAttribute("StudentId", (num+1));
			}
			ResultSet result=obj1.DisplayBooks();
			List<LibraryDTO> list=new ArrayList<LibraryDTO>();
			while(result.next()) {
			LibraryDTO obj2 =new LibraryDTO();
			obj2.setBookname(result.getString(1));
			obj2.setAuthor(result.getString(2));
			obj2.setDescription(result.getString(3));
			list.add(obj2);
			model.addAttribute("list", list);
			}
			return "index";
		}
		else if(Display.equals("Admin Account")) {
			obj1.CalculatePrice();
			model.addAttribute("CallModel", "null");
			model.addAttribute("Display4", Display);
			ResultSet result=obj1.DisplayStudentDetails();
			List<LibraryDTO> list=new ArrayList<LibraryDTO>();
			while(result.next()) {
			LibraryDTO obj4 =new LibraryDTO();
			obj4.setStudentid(result.getInt(1));
			obj4.setBookname(result.getString(2));
			obj4.setAuthor(result.getString(3));
			obj4.setPrice(result.getInt(4));
			obj4.setStatus(result.getString(5));
			String Check= result.getString(6);
			if(Check.equals("Return Request")) {
				obj4.setRequest("<button type=\"submit\" class=\"btn btn-dark\"><input type=\"hidden\" name=\"bookstatus\"  value=\"Return Request\">Return Request</input></button>");
			}
			if(Check.equals("Renew Request")) {
				obj4.setRequest("<button type=\"submit\" class=\"btn btn-dark\"><input type=\"hidden\" name=\"bookstatus\"  value=\"Renew Request\">Renew Request</input></button>");
			}
			list.add(obj4);
			model.addAttribute("list", list);
			}
			return "admin";
		}
		else {
			Current_userid=Userid;
			obj1.CalculatePrice();
		model.addAttribute("CallModel", "null");
		model.addAttribute("Display3", Display);
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		String Check=obj1.BookStatus(result.getString(1),result.getString(2),Current_userid);
		int Check2=obj1.NoOfBooks(result.getString(1),result.getString(2));
		if(Check.equals("Issued") || Check.equals("Renewed")) {
			obj2.setStatus("Return Book");
			obj2.setBookalert("clickButton");
			obj2.setRenew("<button class=\"btn btn-lg btn-light \" formaction=\"issueBook2\" type=\"submit\">Renew Book</button>");
		}
		else if(Check2<=0) {
			obj2.setButtonstatus("disabled");
			obj2.setStatus("Not Available ");
		}
		else if(Check2>0){
			obj2.setStatus("Issue Book");
		}
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "login";
		}
	}
	
	
	
	@RequestMapping("/index3")
	public String forgetPassword(HttpServletRequest req,Model model) throws ClassNotFoundException, SQLException {
		int Studentid = Integer.parseInt(req.getParameter("studentidf"));
		String Password = req.getParameter("passwordf");
		obj1.forgetPassword(Studentid, Password);
		model.addAttribute("CallModel", "clickButton");
		ResultSet result2=obj1.GetStudentId();
		while(result2.next()) {
		int num=Integer.parseInt(result2.getString(1));
		model.addAttribute("StudentId", (num+1));
		}
		return "index";
	}
	
	
	@RequestMapping("/logout")
	public String Logout(Model model) throws ClassNotFoundException, SQLException  {
		ResultSet result2=obj1.GetStudentId();
		while(result2.next()) {
		int num=Integer.parseInt(result2.getString(1));
		model.addAttribute("StudentId", (num+1));
		}
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		list.add(obj2);
		model.addAttribute("list", list);
		
		}
		Current_userid=0;
		return "index";
	}
	
	@RequestMapping("/homeAdmin")
	public String HomeAdmin(Model model) throws ClassNotFoundException, SQLException  {
		model.addAttribute("Display4", Display);
		ResultSet result=obj1.DisplayStudentDetails();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj4 =new LibraryDTO();
		obj4.setStudentid(result.getInt(1));
		obj4.setBookname(result.getString(2));
		obj4.setAuthor(result.getString(3));
		obj4.setPrice(result.getInt(4));
		obj4.setStatus(result.getString(5));
		String Check= result.getString(6);
		if(Check.equals("Return Request")) {
			obj4.setRequest("<button type=\"submit\" class=\"btn btn-dark\"><input type=\"hidden\" name=\"bookstatus\"  value=\"Return Request\">Return Request</input></button>");
		}
		if(Check.equals("Renew Request")) {
			obj4.setRequest("<button type=\"submit\" class=\"btn btn-dark\"><input type=\"hidden\" name=\"bookstatus\"  value=\"Renew Request\">Renew Request</input></button>");
		}
		list.add(obj4);
		model.addAttribute("list", list);
		}
		return "admin";
	}
	
	@RequestMapping("/actionBook")
	public String ActionBook(HttpServletRequest req,Model model) throws ClassNotFoundException, SQLException  {
		int StudentId = Integer.parseInt(req.getParameter("studentId"));
		String Bookname = req.getParameter("bookName");
		String Author = req.getParameter("author");
		String BookStatus = req.getParameter("bookstatus");
		//System.out.println(StudentId+" "+Bookname+" "+Author+" "+BookStatus);
		if(BookStatus.equals("Return Request")) {
			int availBook=obj1.NoOfBooks(Bookname,Author);
			obj1.ReturnRequest(StudentId,Bookname,Author,availBook);
		}
		else if(BookStatus.equals("Renew Request")){
			LocalDate todayDate =  LocalDate.now();
			LocalDate returnDate =  LocalDate.now().plusDays(7);
			int Price=0;
			obj1.RenewRequest(StudentId,Bookname,Author,todayDate,returnDate);
		}
		model.addAttribute("Display4", Display);
		ResultSet result=obj1.DisplayStudentDetails();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj4 =new LibraryDTO();
		obj4.setStudentid(result.getInt(1));
		obj4.setBookname(result.getString(2));
		obj4.setAuthor(result.getString(3));
		obj4.setPrice(result.getInt(4));
		obj4.setStatus(result.getString(5));
		String Check= result.getString(6);
		if(Check.equals("Return Request")) {
			obj4.setRequest("<button type=\"submit\" class=\"btn btn-dark\"><input type=\"hidden\" name=\"bookstatus\"  value=\"Return Request\">Return Request</input></button>");
		}
		if(Check.equals("Renew Request")) {
			obj4.setRequest("<button type=\"submit\" class=\"btn btn-dark\"><input type=\"hidden\" name=\"bookstatus\"  value=\"Renew Request\">Renew Request</input></button>");
		}
		list.add(obj4);
		model.addAttribute("list", list);
		}
		return "admin";
	}
	
	@RequestMapping("/booklist")
	public String Books(Model model) throws ClassNotFoundException, SQLException  {
		model.addAttribute("Display4", Display);
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		obj2.setNoOfBooks(result.getInt(4));
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "books";
	}
	
	@RequestMapping("/homeLogin")
	public String HomeLogin(Model model) throws SQLException, ClassNotFoundException  {
		model.addAttribute("Display3", Display);
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		String Check=obj1.BookStatus(result.getString(1),result.getString(2),Current_userid);
		int Check2=obj1.NoOfBooks(result.getString(1),result.getString(2));
		if(Check.equals("Issued") || Check.equals("Renewed")) {
			obj2.setStatus("Return Book");
			obj2.setBookalert("clickButton");
			obj2.setRenew("<button class=\"btn btn-lg btn-light \" formaction=\"issueBook2\" type=\"submit\">Renew Book</button>");
		}
		else if(Check2<=0) {
			obj2.setButtonstatus("disabled");
			obj2.setStatus("Not Available ");
		}
		else if(Check2>0){
			obj2.setStatus("Issue Book");
		}
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "login";
	}
	@RequestMapping("/issueBook")
	public String IssueBook(Model model) throws ClassNotFoundException, SQLException  {
		model.addAttribute("Display3", Display);
		ResultSet result=obj1.DisplayIssueBooks(Current_userid);
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
			LibraryDTO obj3 =new LibraryDTO();
			obj3.setBookname(result.getString(1));
			obj3.setAuthor(result.getString(2));
			obj3.setTodayDate(result.getDate(3));
			obj3.setReturnDate(result.getDate(4));
			obj3.setPrice(result.getInt(5));
			obj3.setStatus(result.getString(6));
			list.add(obj3);
			model.addAttribute("list", list);
		}
		return "issueBook";
	}
	@RequestMapping("/issueBook1")
	public String IssueBook1(HttpServletRequest req,Model model) throws SQLException, ClassNotFoundException  {
		String Bookname = req.getParameter("bookName");
		String Author = req.getParameter("author");
		LocalDate todayDate =  LocalDate.now();
		LocalDate returnDate =  LocalDate.now().plusDays(7);
		int Price=0;
		String Status="Issued";
		String BookStatus = req.getParameter("bookstatus");
		//System.out.println(Bookname+" "+Author+" "+Current_userid+" "+todayDate+" "+returnDate+" "+Status+" "+BookStatus);
		if(BookStatus.equals("Issue Book")) {
			int availBook=obj1.NoOfBooks(Bookname,Author);
		obj1.issueBook(Current_userid,Bookname,Author,todayDate,returnDate,Price,Status,availBook);
		}
		else if(BookStatus.equals("Return Book")){
			//System.out.println("Return Request has been send ");
			obj1.ReturnBook(Current_userid,Bookname,Author);
			model.addAttribute("CallModel", "#returnModel");
		}
		else {
			System.out.print("Not Available");
		}
		
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		String Check=obj1.BookStatus(result.getString(1),result.getString(2),Current_userid);
		int Check2=obj1.NoOfBooks(result.getString(1),result.getString(2));
		if(Check.equals("Issued") || Check.equals("Renewed")) {
			obj2.setStatus("Return Book");
			obj2.setBookalert("clickButton");
			obj2.setRenew("<button class=\"btn btn-lg btn-light \" formaction=\"issueBook2\" type=\"submit\">Renew Book</button>");
		}
		else if(Check2<=0) {
			obj2.setButtonstatus("disabled");
			obj2.setStatus("Not Available ");
		}
		else if(Check2>0){
			obj2.setStatus("Issue Book");
		}
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "login";
	}
	
	
	@RequestMapping("/issueBook2")
	public String IssueBook2(HttpServletRequest req,Model model) throws SQLException, ClassNotFoundException  {
		String Bookname = req.getParameter("bookName");
		String Author = req.getParameter("author");
		LocalDate todayDate =  LocalDate.now();
		LocalDate returnDate =  LocalDate.now().plusDays(7);
	    String Feedback=obj1.RenewBook(Current_userid,Bookname,Author,todayDate,returnDate);
	    if(Feedback.equals("Renewed")) {
	    	System.out.println("Renewed");
	    	model.addAttribute("CallModel", "#renewSuccessModel");
	    }
	    else {
	    	System.out.println("Renew Request has been send ");
	    	model.addAttribute("CallModel", "#renewModel");
	    }
		
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		String Check=obj1.BookStatus(result.getString(1),result.getString(2),Current_userid);
		int Check2=obj1.NoOfBooks(result.getString(1),result.getString(2));
		if(Check.equals("Issued") || Check.equals("Renewed")) {
			obj2.setStatus("Return Book");
			obj2.setBookalert("clickButton");
			obj2.setRenew("<button class=\"btn btn-lg btn-light \" formaction=\"issueBook2\" type=\"submit\">Renew Book</button>");
		}
		else if(Check2<=0) {
			obj2.setButtonstatus("disabled");
			obj2.setStatus("Not Available ");
		}
		else if(Check2>0){
			obj2.setStatus("Issue Book");
		}
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "login";
	}
	
	
	
	
	
	
	@RequestMapping("/addbook")
	public String AddBooks(HttpServletRequest req,Model model) throws ClassNotFoundException, SQLException  {
		
		String Bookname = req.getParameter("bookName");
		String Author = req.getParameter("author");
		String Description = req.getParameter("description");
		int NoOfBooks = Integer.parseInt(req.getParameter("NoOfBooks"));
		obj1.addbooks(Bookname, Author ,Description,NoOfBooks);
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		obj2.setNoOfBooks(result.getInt(4));
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "books";
	}
	
	@RequestMapping("/deleteBook")
	public String DeleteBook(HttpServletRequest req,Model model) throws ClassNotFoundException, SQLException  {
		String Bookname = req.getParameter("bookName");
		String Author = req.getParameter("author");
		obj1.DeleteBook(Bookname,Author);
		ResultSet result=obj1.DisplayBooks();
		List<LibraryDTO> list=new ArrayList<LibraryDTO>();
		while(result.next()) {
		LibraryDTO obj2 =new LibraryDTO();
		obj2.setBookname(result.getString(1));
		obj2.setAuthor(result.getString(2));
		obj2.setDescription(result.getString(3));
		obj2.setNoOfBooks(result.getInt(4));
		list.add(obj2);
		model.addAttribute("list", list);
		}
		return "books";
	}
	
	
	
	
}
