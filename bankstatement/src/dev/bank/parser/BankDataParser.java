package dev.bank.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dev.bank.model.BankTransaction;

/**
 * 추상 클래스보다 더 추상화된 클래스
 * '추상 메서드'만 작성할 수 있음
 * 
 * 추상 메서드 - 메서드의 구현부가 없고, 선언부만 작성된 메서드
 * 
 * 
 * Parser 클래스에 대한 구현 명세
 * - 모든 Parser 클래스들은 이 인터페이스의 규격에 맞게 구현을 해야함
 * 
 */
public interface BankDataParser {
	
	DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	// 추상 메서드 정의
	List<BankTransaction> parse(List<String> lines); // 이 메서드에 대한 명세는 CSVParser와 TSVParser가 각각 구현
	
	// 디폴트 메서드(공통적인 로직)
		default List<BankTransaction> createBankTransactions(List<String> lines, String delimeter) {
			List<BankTransaction> bankTransactions = new ArrayList<>(); // 가변길이 배열 생성

			for (String line : lines) {
				String[] columns = line.split(delimeter);

				LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
				String description = columns[1];
				long amount = Long.parseLong(columns[2]);

				BankTransaction bankTransaction = new BankTransaction(date, description, amount);

				bankTransactions.add(bankTransaction);
			}

			return bankTransactions;
		}
}