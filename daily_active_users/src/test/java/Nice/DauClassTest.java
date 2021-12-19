package Nice;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Date;
import java.util.Dictionary;
import java.util.Set;

import org.junit.Test;

public class DauClassTest {

	@Test
	public void testGetDailyUsersFromTableFileNotNull() throws IOException {
		DauInterface dau = new DauClass();	
		Dictionary<Date, Set<Integer>> dateUsers = dau.getDailyUsersFromTableFile("C:\\Users\\User\\Downloads\\DAUexample.docx");
		assert(dateUsers != null);
	}

}
