/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * An interface for all the lists to ensure they have the correct methods so other 
 * class won't have errors
 */


package com.bc;

import java.util.ArrayList;

public interface ListInterface<T> {
	public ArrayList<Object> listOfObjects = new ArrayList<Object>();
	
	public int getSize();
	public T getObject(int index);
	public T codeToObject(String code);
}
