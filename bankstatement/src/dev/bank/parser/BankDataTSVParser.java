package dev.bank.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dev.bank.model.BankTransaction;

/**
 * TSV - Tab seperated value, 탭으로 구분된 파일
 */
public class BankDataTSVParser {
	private static final DateTimeFormatter DATE_PATTERN 
	= DateTimeFormatter.ofPattern("yyyy-MM-dd"); 

/**
 * TSV 파일을 파싱해주는 처리를 수행하는 메서드
 * 
 * @param lines - 1줄씩 읽어들인 입출금 내역 리스트
 * @return List<BankTransaction> - 단 건 입출금 내역 데이터 리스트
 */
public List<BankTransaction> parseLinesFromTSV(List<String> lines) {
	List<BankTransaction> bankTransactions = new ArrayList<>(); // 가변길이 배열 객체
	
	for (String line : lines) {
		String[] columns = line.split("\t");
		
		LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
		String description = columns[1];
		long amount = Long.parseLong(columns[2]);
		
		BankTransaction bankTransaction 
			= new BankTransaction(date, description, amount);
		
		bankTransactions.add(bankTransaction);
	}
	
	return bankTransactions; 
}
}