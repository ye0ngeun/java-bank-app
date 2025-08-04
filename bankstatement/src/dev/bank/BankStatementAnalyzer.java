package dev.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import dev.bank.model.BankTransaction;
import dev.bank.parser.BankDataCSVParser;

public class BankStatementAnalyzer {
	
	private static final String RESOURCES = "resources/";
	
	public static void main(String[] args) {
		// 1. 입출금 내역 파일 읽기
		// 1-1. 파일을 읽기 위한 해당 경로(Path)에 대한 정보
		final Path path = Paths.get(RESOURCES + "bank-data.csv");
		
		// 1-2. 실제 파일 읽기
		try {
			List<String> lines = Files.readAllLines(path);
			
			if(lines.isEmpty()) {
				System.out.println("입출금 내역이 존재하지 않습니다.");
				return; // main을 종료
			}
			
			// 2. 입출금내역 파일 파싱
			BankDataCSVParser csvParser = new BankDataCSVParser();
			List<BankTransaction> bankTransactions = csvParser.parseLinesFromCSV(lines); // lines 객체를 전달
			
			// 3. 총 입출금 내역 조회 및 출력
			String result = String.format("총 입출금액은 %d원 입니다", calculateTotalAmount(bankTransactions));
			System.out.println(result);
			
			// 4. 특정 월의 입출금 내역 조회 및 출력
			String resultForMonth = String.format("1월의 입출금액은 %d원 입니다.", calculateTotalAmountInMonth(bankTransactions, Month.JANUARY));
			System.out.println(resultForMonth);
			
		} catch(IOException e) {
			System.out.println("입출금 파일 내역이 존재하지 않습니다.");
		}
	}
	
	// 총 입출금 내역 조회 메서드
	private static long calculateTotalAmount(List<BankTransaction> bankTransactions) {
		long total = 0L;
		
		for (BankTransaction bankTransaction : bankTransactions) {
			total += bankTransaction.getAmount();
		}
		
		return total;
	}
	
	// 특정 월의 입출금 내역 조회 메서드
	private static long calculateTotalAmountInMonth(List<BankTransaction> bankTransactions, Month month) {
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
