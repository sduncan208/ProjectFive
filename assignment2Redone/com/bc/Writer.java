/* Sophia Duncan(37182093) & Joe Stollar(93721732) 
 *  
 * CSCE 156 
 */

package com.bc;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Writer<T> {
	private T listInput;

	public Writer(T _listInput) {
		this.listInput = _listInput;
	}

	// Writes to the json file
	public void writeJson(String filePath) throws JsonIOException, IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(gson.toJson(this.listInput));
		}
	}
	
	//Writes to an xml file
	public void writeXml(String filePath) throws IOException {
		XStream xstream = new XStream(new StaxDriver());
		
		try (FileWriter writer = new FileWriter(filePath)) {
			writer.write(xstream.toXML(this.listInput));
		}
	}
}