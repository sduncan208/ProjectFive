package com.bc;

public class AbstractedInvoiceList {

	private InvoiceListNode head;
	private int size;

	public AbstractedInvoiceList() {
		this.head = null;
		this.size = 0;
	}

	public int sortingIndex(Invoice t) {
double invoiceTotal = t.total();
		
		for (int i = 0; i < size; i ++) {
			if (getInvoice(i).total() < invoiceTotal)
				return i;
		}
		return size;

	public void orderedInput(InvoiceListNode input) {

		InvoiceListNode current;
		
		if (head == null || head.invoiceTotal >= input.invoiceTotal) {
			input.setNext(head);
			head = input;
		} else {
			current = head;
			while (current.getNext() != null && current.getNext().invoiceTotal < input.invoiceTotal)
				current = current.getNext();
				input.setNext(current.getNext());
				current.setNext(input);
		}

	}

	InvoiceListNode newNode(Invoice invoiceTotal) {
		InvoiceListNode x = new InvoiceListNode(invoiceTotal);
		return x;
	}

  
	public int getSize() {
		return this.size;
	}

	public void clear() {
		int tempSize = this.size;
		for (int i = tempSize - 1; i >= 0; i--) {
			remove(i);
		}
	}
	
	//Adding at an index
	public void add(Invoice t) {
		if (this.size == 0) {
			InvoiceListNode temp = new InvoiceListNode(t);
			temp.setNext(this.head);
			this.head = temp;
			this.size ++;
			return;
		}
		
		int index = sortingIndex(t);
		
		if(index == 0) {
			InvoiceListNode temp = new InvoiceListNode(t);
			temp.setNext(this.head);
			this.head = temp;
			this.size ++;
			return;
		}
		
		
		InvoiceListNode now = this.getInvoiceListNode(index - 1);
		InvoiceListNode newNode = new InvoiceListNode(t);
		newNode.setNext(now.getNext());
		now.setNext(newNode);
		this.size ++;
	}

	public void remove(int position) {
		if (this.head == null)
			throw new IndexOutOfBoundsException("No Invoice exists");

		if (position == 0) {
			Invoice temp = this.head.getInvoice();
			this.head = this.head.getNext();
			this.size--;
			return;
		}

		InvoiceListNode lastNode = this.getInvoiceListNode(position - 1);
		InvoiceListNode thisNode = lastNode.getNext();
		lastNode.setNext(thisNode.getNext());
		this.size--;
	}

	private InvoiceListNode getInvoiceListNode(int position) {
		if (position < 0 || position >= this.size)
			throw new IndexOutOfBoundsException("There is no Invoice at this position");

		InvoiceListNode temp = this.head;
		for (int i = 0; i < position; i++) {
			temp = temp.getNext();
		}
		return temp;
	}

	public Invoice getInvoice(int position) {
		return getInvoiceListNode(position).getInvoice();
	}
	
	public Invoice codeToObject(String code) {
		for (int i = 0; i < this.size; i ++) {
			if (getInvoice(i).getInvoiceCode().equals(code))
				return getInvoice(i);
		}
		
		return null;
	}

	public void print() {
		if (this.head == null) {
			System.out.println("There are no Invoices");
			return;
		}

		InvoiceListNode temp = this.head;
		while (temp != null) {
			System.out.println(temp.getInvoice().getInvoiceCode() + " total: " + temp.getInvoice().total());
			temp = temp.getNext();
		}
	}
}
