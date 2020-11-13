package com.bc;

public class InvoiceListNode {

	public final double invoiceTotal = getInvoice().total();
	private InvoiceListNode next;
	private Invoice item;

	public InvoiceListNode(Invoice item) {
		this.item = item;
		this.next = null;
	}

	public Invoice getInvoice() {
		return item;
	}

	public InvoiceListNode getNext() {
		return next;
	}

	public void setNext(InvoiceListNode next) {
		this.next = next;
	}
}
