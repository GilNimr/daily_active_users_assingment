package Nice;



import java.io.IOException;
import java.util.Set;
import java.util.Date;
import java.util.Dictionary;


public class Main {

	public static void main(String[] args)  throws IOException {

		String fileName = "C:\\Users\\User\\Downloads\\DAUexample.docx";
		//String fileName = "C:\\Users\\User\\Downloads\\DAUshouldfail.docx";
		
		DauInterface dau = new DauClass();
		
		Dictionary<Date, Set<Integer>> dateUsers = dau.getDailyUsersFromTableFile(fileName);
		dau.printDailyUsersDictionary(dateUsers);
	}

}
