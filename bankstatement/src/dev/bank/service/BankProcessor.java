package dev.bank.service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import dev.bank.model.BankTransaction;

public class BankProcessor {
	private final List<BankTransaction> bankTransactions;

	public BankProcessor(List<BankTransaction> bankTransactions) {
		super();
		this.bankTransactions = bankTransactions;
	}
	
	// 총 입출금 내역 조회 메서드
	public long calculateTotalAmount() {
		long total = 0L;
		
		for (BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	// 특정 월의 총 입출금 내역 조회 메서드
	public long calculateTotalAmountInMonth(Month month) {
		long totalForMonth = 0L;
		
		for (BankTransaction bankTransaction : bankTransactions) {
			LocalDate date = bankTransaction.getDate();
			
			if(date.getMonth() == month) {
				totalForMonth += bankTransaction.getAmount();
			}
		}
		
		return totalForMonth;
	}
	

}
