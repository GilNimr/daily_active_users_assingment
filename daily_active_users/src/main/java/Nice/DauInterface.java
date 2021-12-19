package Nice;

import java.io.IOException;
import java.util.Date;
import java.util.Dictionary;
import java.util.Set;

public interface DauInterface {
	
	 abstract void printDailyUsersDictionary(Dictionary<Date, Set<Integer>> dateUsers);
	 
	 abstract Dictionary<Date, Set<Integer>> getDailyUsersFromTableFile(String fileName) throws IOException;

}
