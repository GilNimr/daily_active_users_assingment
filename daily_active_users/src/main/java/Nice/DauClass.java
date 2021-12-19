package Nice;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.poi.xwpf.usermodel.IBodyElement;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;

public class DauClass implements DauInterface {

	@Override
	public void printDailyUsersDictionary(Dictionary<Date, Set<Integer>> dateUsers) {
		//Print the Dictionary.
		if(dateUsers == null || dateUsers.size() <= 0) {
			System.out.println("Empty DAU tabe");
		}
		Enumeration<Date> keys =  dateUsers.keys();
		Date date = keys.nextElement();
		while (date != null)
		{

			Set<Integer> set = dateUsers.get(date);
			System.out.println("Daily Active Users of the Day: " + date.toString());
			for (int i : set) {
				System.out.println(i);
			}
			if(keys.hasMoreElements()) {
				date = keys.nextElement();
			}
			else {
				break;
			}

		}
	}

	@Override
	public Dictionary<Date, Set<Integer>> getDailyUsersFromTableFile(String fileName) throws IOException{
		Dictionary<Date, Set<Integer>> dateUsers = new Hashtable<Date, Set<Integer>>();

		try (XWPFDocument doc = new XWPFDocument(
				Files.newInputStream(Paths.get(fileName)))) {

			Iterator<IBodyElement> docElementsIterator = doc.getBodyElementsIterator();

			//Iterate through the list and check for table element type
			while (docElementsIterator.hasNext()) {
				IBodyElement docElement = docElementsIterator.next();
				if ("TABLE".equalsIgnoreCase(docElement.getElementType().name())) {
					//Get List of table and iterate it
					List<XWPFTable> xwpfTableList = docElement.getBody().getTables();

					//add if list is not empty

					//if there is more than 1 table.
					for (XWPFTable xwpfTable : xwpfTableList) {

						//i = 1, because first row is a header.
						for (int i = 1; i < xwpfTable.getRows().size(); i++) {

							//Assuming the second column is a date
							String strDate = xwpfTable.getRow(i).getCell(1).getText(); 
							Date loginTime = new Date();
							try {

								loginTime =new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(strDate);
								//Deprecated, just for assingment.
								loginTime.setHours(0);
								loginTime.setMinutes(0);

								//System.out.println("loginTime: " + loginTime.toString());

							} catch (ParseException e) {
								e.printStackTrace();
								continue;
							} 

							//Assuming the second column is a integer
							String strUserId = xwpfTable.getRow(i).getCell(0).getText();
							//System.out.println("strUserId: " + strUserId);

							int userId;
							try {
								userId = Integer.parseInt(strUserId);
							} catch (NumberFormatException e) {
								e.printStackTrace();
								continue;
							}

							Set<Integer> set =dateUsers.get(loginTime);
							if( set == null) {
								set = new HashSet<Integer>();
							}
							set.add(userId);
							dateUsers.put(loginTime,set);

						}
					}
				}
			}

		}
		return dateUsers;
	}

}
