package com.bc;

public class AbstractedInvoiceList {

	private InvoiceListNode head;
	private int size;

	public AbstractedInvoiceList() {
		this.head = null;
		this.size = 0;
	}

	public void sortTotals() {

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

	public void addToEnd(Invoice t) {
		if (this.size == 0) {
			InvoiceListNode temp = new InvoiceListNode(t);
			temp.setNext(this.head);
			this.head = temp;
			this.size++;
			return;
		}

		InvoiceListNode temp = this.head;
		while (temp.getNext() != null) {
			temp = temp.getNext();
		}

		InvoiceListNode endNode = new InvoiceListNode(t);
		temp.setNext(endNode);
		this.size++;
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
