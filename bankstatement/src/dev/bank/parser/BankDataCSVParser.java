package dev.bank.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dev.bank.model.BankTransaction;

/**
 * dev.bank.parser 패키지 - 읽어들인 파일 데이터에 대한 확장자별로 변환 처리를 담당하는 클래스들만 모아둔 패키지
 * 
 * BankDataCSVParser.java - 읽어들인 csv 파일을 Java 프로그램에서 사용할 수 있도록 변환 처리 역할 수행
 */
public class BankDataCSVParser implements BankDataParser {
	
	private static final DateTimeFormatter DATE_PATTERN 
		= DateTimeFormatter.ofPattern("yyyy-MM-dd"); 
	
	/**
	 * CSV 파일을 파싱해주는 처리를 수행하는 메서드
	 * 
	 * @param lines - 1줄씩 읽어들인 입출금 내역 리스트
	 * @return List<BankTransaction> - 단 건 입출금 내역 데이터 리스트
	 */
	public List<BankTransaction> parseLinesFrom(List<String> lines) {
		// 전체 입출금 내역 데이터를 반환할 값
		List<BankTransaction> bankTransactions = new ArrayList<>(); // 가변길이 배열 객체
		
		for (String line : lines) {
			// 중요한 파싱 로직
			String[] columns = line.split(",");
			
			// 각 컬럼별 데이터 타입으로 파싱(읽어들인 값이 전부 문자열 타입이기 때문에)
			LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
			String description = columns[1];
			long amount = Long.parseLong(columns[2]);
			
			// 모델링한 객체로 추상화
			// 파싱된 개별 타입(기본 타입)의 값들을 BankTransaction이라는 클래스로 묶어줌(Wrapping)
			BankTransaction bankTransaction 
				= new BankTransaction(date, description, amount);
			
			// 생성한 BankTransaction 객체를 배열의 요소로 추가
			bankTransactions.add(bankTransaction); // [입출금 내역 객체,입출금 내역2,입출금 내역3,..];
		}
		
		return bankTransactions; // 입출금 내역 데이터 리스트를 반환 
	}
}
