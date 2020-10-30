/* Sophia Duncan(37182093) & Joe Stollar(93721732)
 * 
 * Writes the report of all invoics to the .txt file
 */

package com.bc;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class SummaryWriter {
	private InvoiceList invoices;
	private String path;

	public SummaryWriter(InvoiceList _invoices, String _path) throws FileNotFoundException {
		this.invoices = _invoices;
		this.path = _path;
	}

	public void printing() {
		// Writes to file
		System.out.println("Executive Summary Report:");
		System.out.println(String.format("%-10s", "Code") + String.format("%-30s", "Owner")
				+ String.format("%-30s", "Customer Account") + String.format("%-12s", "Subtotal")
				+ String.format("%-12s", "Discounts") + String.format("%-12s", "Fees") + String.format("%-12s", "Taxes")
				+ String.format("%-10s", "Total"));
		System.out.println(("-").repeat(128));

		// Writes and calculates the summary
		double subtotal = 0;
		double discounts = 0;
		double fees = 0;
		double taxes = 0;
		double total = 0;
		for (int i = 0; i < this.invoices.getSize(); i++) {
			Invoice temp = this.invoices.getObject(i);
			//Calculations calculator = new Calculations(temp);
			subtotal += temp.subtotal();
			discounts += temp.discounts();
			fees += temp.fees();
			taxes += temp.taxes();
			total += temp.total();
			System.out.println(String.format("%-10s", temp.getInvoiceCode())
					+ String.format("%-30s", temp.getPersonAssociated().getName())
					+ String.format("%-30s", temp.getCustomerAssociated().getName())
					+ String.format("$%9.2f", temp.subtotal()) + "  "
					+ String.format("$%9.2f", temp.discounts()) + "  "
					+ String.format("$%9.2f", temp.fees()) + "  " + String.format("$%9.2f", temp.taxes())
					+ "  " + String.format("$%7.2f", temp.total()));
		}
		System.out.println(("-").repeat(128));
		System.out.println(String.format("%-70s", "Totals:") + String.format("$%9.2f", subtotal) + "  "
				+ String.format("$%9.2f", discounts) + "  " + String.format("$%9.2f", fees) + "  "
				+ String.format("$%9.2f", taxes) + "  " + String.format("$%7.2f", total));

		// Writes each individual invoice report
		for (int i = 0; i < this.invoices.getSize(); i++) {
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Invoice Details:");
			System.out.println(("-").repeat(128));
			System.out.println("Invoice " + this.invoices.getObject(i).getInvoiceCode());
			System.out.println(("-").repeat(30));

			System.out.println("Owner:");
			System.out.println(
					String.format("        %-40s", this.invoices.getObject(i).getPersonAssociated().getName()));
			if (this.invoices.getObject(i).getPersonAssociated().emailsToString() != null)
				System.out.println(String.format("        %-40s",
						this.invoices.getObject(i).getPersonAssociated().emailsToString()));
			System.out.println(String.format("        %-40s",
					this.invoices.getObject(i).getPersonAssociated().getAddress().getStreet()));
			System.out.println(String.format("        %-40s",
					this.invoices.getObject(i).getPersonAssociated().getAddress().getCity() + ", "
							+ this.invoices.getObject(i).getPersonAssociated().getAddress().getState() + " "
							+ this.invoices.getObject(i).getPersonAssociated().getAddress().getCountry() + " "
							+ this.invoices.getObject(i).getPersonAssociated().getAddress().getZip()));

			System.out.println("Customer:");
			System.out.println(
					String.format("        %-40s", this.invoices.getObject(i).getCustomerAssociated().getName()));
			System.out.println(String.format("        %-40s",
					this.invoices.getObject(i).getCustomerAssociated().getAddress().getStreet()));
			System.out.println(String.format("        %-40s",
					this.invoices.getObject(i).getCustomerAssociated().getAddress().getCity() + ", "
							+ this.invoices.getObject(i).getCustomerAssociated().getAddress().getState() + " "
							+ this.invoices.getObject(i).getCustomerAssociated().getAddress().getCountry() + " "
							+ this.invoices.getObject(i).getCustomerAssociated().getAddress().getZip()));

			System.out.println("Products:");
			System.out.println("  " + String.format("%-12s", "Code") + String.format("%-70s", "Description")
					+ String.format("%-12s", "Subtotal") + String.format("%-12s", "Discounts")
					+ String.format("%-12s", "Taxes") + String.format("%-10s", "Total"));
			System.out.println("  " + ("-").repeat(124));

			// Writes out each product
			//Calculations calculator = new Calculations(this.invoices.getObject(i));
			Invoice tempTwo = this.invoices.getObject(i);
			for (int j = 0; j < this.invoices.getObject(i).getProductsAssociated().size(); j++) {
				InvoiceProduct temp = this.invoices.getObject(i).getProductsAssociated().get(j);
				String descriptionOne = "";
				String descriptionTwo = "";

				if (temp.getProduct().getProductType().equals("R")) {
					Rental tempRental = (Rental) temp.getProduct();
					descriptionOne += tempRental.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " days @ $" + String.format("%.2f", tempRental.getDailyCost()) + "/day)";
					descriptionTwo += "(+ $" + String.format("%.2f", tempRental.getCleaningFee()) + " cleaning fee, -$"
							+ String.format("%.2f", tempRental.getDeposit()) + " deposit refund)";
				}
				if (temp.getProduct().getProductType().equals("F")) {
					Repair tempRepair = (Repair) temp.getProduct();
					descriptionOne += tempRepair.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " hours of labour @ $" + String.format("%.2f", tempRepair.getHourlyLaborCost())
							+ "/hour)";
					descriptionTwo += "(+ $" + String.format("%.2f", tempRepair.getPartsCost()) + " for parts)";
				}
				if (temp.getProduct().getProductType().equals("C")) {
					Concession tempConcession = (Concession) temp.getProduct();
					descriptionOne += tempConcession.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " units @ $" + String.format("%.2f", tempConcession.getUnitCost()) + "/unit)";
				}
				if (temp.getProduct().getProductType().equals("T")) {
					Towing tempTowing = (Towing) temp.getProduct();
					descriptionOne += tempTowing.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " miles @ $" + String.format("%.2f", tempTowing.getCostPerMile()) + "/mile)";
				}

				System.out.println("  " + String.format("%-12s", temp.getProduct().getProductCode())
						+ String.format("%-70s", descriptionOne)
						+ String.format("$%9.2f", tempTwo.individualSubtotal(j)) + "  "
						+ String.format("$%9.2f", tempTwo.individualDiscount(j)) + "  "
						+ String.format("$%9.2f", tempTwo.individualTaxes(j)) + "  "
						+ String.format("$%7.2f", tempTwo.individualTotal(j)));
				System.out.println("  " + String.format("%-12s", " ") + String.format("%-70s", descriptionTwo));
			}
			System.out.println(("-").repeat(126));
			System.out.println(String.format("%-84s", "Item Totals:") + String.format("$%9.2f", tempTwo.subtotal())
					+ "  " + String.format("$%9.2f", tempTwo.discounts()) + "  "
					+ String.format("$%9.2f", tempTwo.taxes()) + "  " + String.format("$%7.2f", tempTwo.total()));

			if (this.invoices.getObject(i).getCustomerAssociated().getCustomerType().equals("B"))
				System.out.println(
						String.format("%-120s", "Business Account Fees:") + String.format("$%7.2f", tempTwo.fees()));
			if (tempTwo.loyalCustomer() != 0)
				System.out.println(String.format("%-120s", "Loyal Customer Discount (5% off):")
						+ String.format("$%7.2f", tempTwo.loyalCustomer()));

			System.out.println(String.format("%-120s", "Grand Total:") + String.format("$%7.2f", tempTwo.total()));
			System.out.println((" ".repeat(30) + "Thank you for doing business with us!"));
		}
	}

	public void writing() throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File(this.path));

		// Writes to file
		writer.println("Executive Summary Report:");
		writer.println(String.format("%-10s", "Code") + String.format("%-30s", "Owner")
				+ String.format("%-30s", "Customer Account") + String.format("%-12s", "Subtotal")
				+ String.format("%-12s", "Discounts") + String.format("%-12s", "Fees") + String.format("%-12s", "Taxes")
				+ String.format("%-10s", "Total"));
		writer.println(("-").repeat(128));

		// Writes and calculates the summary
		double subtotal = 0;
		double discounts = 0;
		double fees = 0;
		double taxes = 0;
		double total = 0;
		for (int i = 0; i < this.invoices.getSize(); i++) {
			Invoice temp = this.invoices.getObject(i);
			//Calculations calculator = new Calculations(temp);
			subtotal += temp.subtotal();
			discounts += temp.discounts();
			fees += temp.fees();
			taxes += temp.taxes();
			total += temp.total();
			writer.println(String.format("%-10s", temp.getInvoiceCode())
					+ String.format("%-30s", temp.getPersonAssociated().getName())
					+ String.format("%-30s", temp.getCustomerAssociated().getName())
					+ String.format("$%9.2f", temp.subtotal()) + "  "
					+ String.format("$%9.2f", temp.discounts()) + "  "
					+ String.format("$%9.2f", temp.fees()) + "  " + String.format("$%9.2f", temp.taxes())
					+ "  " + String.format("$%7.2f", temp.total()));
		}
		writer.println(("-").repeat(128));
		writer.println(String.format("%-70s", "Totals:") + String.format("$%9.2f", subtotal) + "  "
				+ String.format("$%9.2f", discounts) + "  " + String.format("$%9.2f", fees) + "  "
				+ String.format("$%9.2f", taxes) + "  " + String.format("$%7.2f", total));

		// Writes each individual invoice report
		for (int i = 0; i < this.invoices.getSize(); i++) {
			writer.println("");
			writer.println("");
			writer.println("");
			writer.println("Invoice Details:");
			writer.println(("-").repeat(128));
			writer.println("Invoice " + this.invoices.getObject(i).getInvoiceCode());
			writer.println(("-").repeat(30));

			writer.println("Owner:");
			writer.println(
					String.format("        %-40s", this.invoices.getObject(i).getPersonAssociated().getName()));
			if (this.invoices.getObject(i).getPersonAssociated().emailsToString() != null)
				writer.println(String.format("        %-40s",
						this.invoices.getObject(i).getPersonAssociated().emailsToString()));
			writer.println(String.format("        %-40s",
					this.invoices.getObject(i).getPersonAssociated().getAddress().getStreet()));
			writer.println(String.format("        %-40s",
					this.invoices.getObject(i).getPersonAssociated().getAddress().getCity() + ", "
							+ this.invoices.getObject(i).getPersonAssociated().getAddress().getState() + " "
							+ this.invoices.getObject(i).getPersonAssociated().getAddress().getCountry() + " "
							+ this.invoices.getObject(i).getPersonAssociated().getAddress().getZip()));

			writer.println("Customer:");
			writer.println(
					String.format("        %-40s", this.invoices.getObject(i).getCustomerAssociated().getName()));
			writer.println(String.format("        %-40s",
					this.invoices.getObject(i).getCustomerAssociated().getAddress().getStreet()));
			writer.println(String.format("        %-40s",
					this.invoices.getObject(i).getCustomerAssociated().getAddress().getCity() + ", "
							+ this.invoices.getObject(i).getCustomerAssociated().getAddress().getState() + " "
							+ this.invoices.getObject(i).getCustomerAssociated().getAddress().getCountry() + " "
							+ this.invoices.getObject(i).getCustomerAssociated().getAddress().getZip()));

			writer.println("Products:");
			writer.println("  " + String.format("%-12s", "Code") + String.format("%-70s", "Description")
					+ String.format("%-12s", "Subtotal") + String.format("%-12s", "Discounts")
					+ String.format("%-12s", "Taxes") + String.format("%-10s", "Total"));
			writer.println("  " + ("-").repeat(124));

			// Writes out each product
			//Calculations calculator = new Calculations(this.invoices.getObject(i));
			Invoice tempTwo = this.invoices.getObject(i);
			for (int j = 0; j < this.invoices.getObject(i).getProductsAssociated().size(); j++) {
				InvoiceProduct temp = this.invoices.getObject(i).getProductsAssociated().get(j);
				String descriptionOne = "";
				String descriptionTwo = "";

				if (temp.getProduct().getProductType().equals("R")) {
					Rental tempRental = (Rental) temp.getProduct();
					descriptionOne += tempRental.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " days @ $" + String.format("%.2f", tempRental.getDailyCost()) + "/day)";
					descriptionTwo += "(+ $" + String.format("%.2f", tempRental.getCleaningFee()) + " cleaning fee, -$"
							+ String.format("%.2f", tempRental.getDeposit()) + " deposit refund)";
				}
				if (temp.getProduct().getProductType().equals("F")) {
					Repair tempRepair = (Repair) temp.getProduct();
					descriptionOne += tempRepair.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " hours of labour @ $" + String.format("%.2f", tempRepair.getHourlyLaborCost())
							+ "/hour)";
					descriptionTwo += "(+ $" + String.format("%.2f", tempRepair.getPartsCost()) + " for parts)";
				}
				if (temp.getProduct().getProductType().equals("C")) {
					Concession tempConcession = (Concession) temp.getProduct();
					descriptionOne += tempConcession.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " units @ $" + String.format("%.2f", tempConcession.getUnitCost()) + "/unit)";
				}
				if (temp.getProduct().getProductType().equals("T")) {
					Towing tempTowing = (Towing) temp.getProduct();
					descriptionOne += tempTowing.getProductLabel() + " (" + String.format("%.2s", temp.getNums())
							+ " miles @ $" + String.format("%.2f", tempTowing.getCostPerMile()) + "/mile)";
				}

				writer.println("  " + String.format("%-12s", temp.getProduct().getProductCode())
						+ String.format("%-70s", descriptionOne)
						+ String.format("$%9.2f", tempTwo.individualSubtotal(j)) + "  "
						+ String.format("$%9.2f", tempTwo.individualDiscount(j)) + "  "
						+ String.format("$%9.2f", tempTwo.individualTaxes(j)) + "  "
						+ String.format("$%7.2f", tempTwo.individualTotal(j)));
				writer.println("  " + String.format("%-12s", " ") + String.format("%-70s", descriptionTwo));
			}
			writer.println(("-").repeat(126));
			writer.println(String.format("%-84s", "Item Totals:") + String.format("$%9.2f", tempTwo.subtotal())
					+ "  " + String.format("$%9.2f", tempTwo.discounts()) + "  "
					+ String.format("$%9.2f", tempTwo.taxes()) + "  " + String.format("$%7.2f", tempTwo.total()));

			if (this.invoices.getObject(i).getCustomerAssociated().getCustomerType().equals("B"))
				writer.println(
						String.format("%-120s", "Business Account Fees:") + String.format("$%7.2f", tempTwo.fees()));
			if (tempTwo.loyalCustomer() != 0)
				writer.println(String.format("%-120s", "Loyal Customer Discount (5% off):")
						+ String.format("$%7.2f", tempTwo.loyalCustomer()));

			writer.println(String.format("%-120s", "Grand Total:") + String.format("$%7.2f", tempTwo.total()));
			writer.println((" ".repeat(30) + "Thank you for doing business with us!"));
		}

		writer.close();
	}
}
