/**
 * 
 */
package mvc.librarySystem;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author Lenovo
 *
 */
class LibraryJunitTest {

	@Test
	@DisplayName("Testing Login method ")
	void testlogin() throws ClassNotFoundException, SQLException {
		LibraryDAO obj1 = new LibraryDAO();
		assertEquals("Jyoti Chhabra", obj1.login(184100, "123"), "Failed to get UserName");
	}

	
	@Test
	@DisplayName("Testing forget method ")
	void forgetPassword() throws ClassNotFoundException, SQLException {
		LibraryDAO obj1 = new LibraryDAO();
		assertEquals(1, obj1.forgetPassword(184104, "rani34"), "Failed to get UserName");
	}
}
