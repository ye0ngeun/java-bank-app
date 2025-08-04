package dev.bank.parser;

import java.time.format.DateTimeFormatter;
import java.util.List;

import dev.bank.model.BankTransaction;

/**
 * dev.bank.parser 패키지 - 읽어들인 파일 데이터에 대한 확장자별로 변환 처리를 담당하는 클래스들만 모아둔 패키지
 * 
 * BankDataCSVParser.java - 읽어들인 csv 파일을 Java 프로그램에서 사용할 수 있도록 변환 처리 역할 수행
 */
public class BankDataCSVParser implements BankDataParser { 
	
	@Override
	public List<BankTransaction> parse(List<String> lines) {
		String delimeter = ",";

		return createBankTransactions(lines, delimeter);
	}
}
