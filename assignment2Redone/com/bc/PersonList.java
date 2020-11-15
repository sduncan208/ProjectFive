package com.bc;

import java.util.ArrayList;

public class PersonList {
	private ArrayList<Person> listOfPersons = new ArrayList<>();

	public PersonList(ArrayList<String> inputList) {
		for (int i = 0; i < inputList.size(); i++) {
			String personItem = inputList.get(i);
			String[] personList = personItem.split(";");

			// Puts in an empty email if there is no email in the file
			if (personList.length < 4) {
				String[] correctPersonList = new String[4];
				for (int k = 0; k < 3; k++) {
					correctPersonList[k] = personList[k];
				}
				correctPersonList[3] = "";

				personList = new String[4];
				for (int k = 0; k < 4; k++) {
					personList[k] = correctPersonList[k];
				}
			}

			// Splits multiple emails
			ArrayList<String> emails = new ArrayList<>();
			String[] tempEmailList = personList[3].split(",");
			for (String addingEmail : tempEmailList) {
				emails.add(addingEmail);
			}

			// Creates a person and adds them to the PersonList
			this.listOfPersons.add(new Person(personList[0], personList[1], personList[2], emails));

		}
	}

	public Person getPersonFromCode(String personCode) {
		for (int i = 0; i < this.listOfPersons.size(); i++) {
			if (getPerson(i).getPersonCode().equals(personCode))
				return getPerson(i);
		}
		return null;
	}

	public int getSize() {
		return listOfPersons.size();
	}

	public Person getPerson(int index) {
		return listOfPersons.get(index);
	}
}