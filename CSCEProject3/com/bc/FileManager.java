package com.bc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileManager {
	private String line;
	private String headerLine;
	private ArrayList<String> dataList = new ArrayList<>();

	public FileManager(String filePath) throws IOException {	
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filePath));
			//the line below will remove the first line from the dat tile and set it to headerLine. Ensure for loops are set to 0.
			this.headerLine = br.readLine();
			while ((this.line = br.readLine()) != null) {
				this.dataList.add(this.line);
			}
			
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getLine() {
		return this.line;
	}

	public String getHeaderLine() {
		return this.headerLine;
	}

	public ArrayList<String> getDataList() {
		return this.dataList;
	}
}