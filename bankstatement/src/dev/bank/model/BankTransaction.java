package dev.bank.model;

import java.time.LocalDate;

/**
 * model 패키지 - 모델링한 결과 클래스들을 모아둔 패키지
 * 
 * BankTransaction - 단 건 입출금 내역
 * ex. 2020-12-20, Costco, -152200
 * 
 * 입출금 내역 데이터 파일로부터 읽어들인 각각의 개별 데이터들을 하나의 의미있는 클래스로 추상화
 * 
 * 필드 summary 
 * LocalDate date - 입출금 날짜 
 * String description - 거래처 
 * long amount - 입출금액
 */

public class BankTransaction {
	private LocalDate date;
	private String description;
	private long amount;
	
	public BankTransaction(LocalDate date, String description, long amount) {
		super();
		this.date = date;
		this.description = description;
		this.amount = amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public long getAmount() {
		return amount;
	}
	
	
	
}