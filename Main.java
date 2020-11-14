package patelhetul;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

public class Main {

	public static void main(String[] args) throws AddressException, MessagingException {
		// TODO Auto-generated method stub
		String[][] participants = {  {"Mark", "mark@hotmail.com"},
									{"John", "john@gmail.com"},
									{"Claire", "claire@gmail.com"},

		};
		
		// convert to a list of lists to shuffle
		List<List<String>> list = Arrays.stream(participants)
                .map(Arrays::asList)
                .collect(Collectors.toList());
		
		//List<String> strList = Arrays.asList(participants);
		
		// shuffle the list
		Collections.shuffle(list);
		
		//participants = list.toArray(new String[list.size()]);
		
		// turn list of lists back to 2d Array
		participants = list.stream()
			    .map(l -> l.stream().toArray(String[]::new))
			    .toArray(String[][]::new);
		
		for(int i = 0; i < participants.length; i++) {
			System.out.println(participants[i][0] + " " + participants[i][1]);
		}
		
		RoleAssigner secret = new RoleAssigner();
		
		for(int i = 0; i < participants.length; i++) {
			secret.addPerson(participants[i][0], participants[i][1]);
		}
		
		secret.traverseSend();
		
	}

}
