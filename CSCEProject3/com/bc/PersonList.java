/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * The list for persons
 */

package com.bc;

import java.util.ArrayList;

public class PersonList implements ListInterface<Person> {
	private ArrayList<Person> listOfObjects = new ArrayList<>();

	public PersonList(ArrayList<String> inputList) {
		for (int i = 0; i < inputList.size(); i++) {
			String personItem = inputList.get(i);
			String[] personList = personItem.split(";");

			// Polymorphism. Accounts for inputs that don't have an email associated with it
			if (personList.length < 4) {
				this.listOfObjects.add(new Person(personList[0], personList[1], personList[2]));
			} else {
				// Splits multiple emails
				ArrayList<String> emails = new ArrayList<>();
				String[] tempEmailList = personList[3].split(",");
				for (String addingEmail : tempEmailList) {
					emails.add(addingEmail);
				}
				this.listOfObjects.add(new Person(personList[0], personList[1], personList[2], emails));
			}
		}
	}

	public Person codeToObject(String personCode) {
		for (int i = 0; i < this.listOfObjects.size(); i++) {
			if (getObject(i).getPersonCode().equals(personCode))
				return getObject(i);
		}
		return null;
	}

	public int getSize() {
		return listOfObjects.size();
	}

	public Person getObject(int index) {
		return listOfObjects.get(index);
	}
}
