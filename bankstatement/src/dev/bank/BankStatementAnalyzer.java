package dev.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankStatementAnalyzer {
	
	private static final String RESOURCES = "resources/";
	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public static void main(String[] args) {
		// 1. 입출금 내역 파일 읽기
		// 1-1. 파일을 읽기 위한 해당 경로(Path)에 대한 정보
		final Path path = Paths.get(RESOURCES + "bank-data.csv");
		
		// 1-2. 실제 파일 읽기
		try {
			List<String> lines = Files.readAllLines(path); // 가변배열(List)에는 문자열 타입만 들어울 수 있음
			// ["2020-12-29,Costco,-152200", 하나의 입출금 내역 요소가 통째로 문자열로 감싸져 있음 
			//	"2020-12-31,Cinema,-46000", // 리스트의 2번째 요소 
			//	"2021-01-02,Rent,-150000", 
			//	2021-01-10,Salary,3500000, 
			//	2021-02-03,Pizza,-24000] // 5개의 요소가 들어있음
			
			if(lines.isEmpty()) {
				System.out.println("입출금 내역이 존재하지 않습니다.");
				return; // main을 종료
			}
			
			// 3. 총 입출금 내역 조회 및 출력
			String result = String.format("총 입출금액은 %d원 입니다.", findTotalTransactions(lines));
			System.out.println(result);
			
			// 4. 1월의 입출금 내역 조회 및 출력
			String resultForMonth = String.format("1월의 입출금액은 %d원 입니다.", findTransactionsInJanuary(lines));
			System.out.println(resultForMonth);
			
		} catch(IOException e) {
			System.out.println("입출금 파일 내역이 존재하지 않습니다.");
		}
	}
	
	// 총 입출금 내역 조회
	private static long findTotalTransactions(List<String> lines) {
		long total = 0L;
		
		for (String line : lines) {
			String[] columns = line.split(",");
			long amount = Long.parseLong(columns[2]); 
			total += amount;
		}
		
		return total;
	}
	
	// 특정 월의 입출금 내역 조회
	private static long findTransactionsInJanuary(List<String> lines) {
		long totalForMonth = 0L;
		
		for (String line : lines) {
			String[] columns = line.split(",");
			LocalDate dateTime = LocalDate.parse(columns[0], DATE_PATTERN);
			
			if (dateTime.getMonth() == Month.JANUARY) {
				totalForMonth += Long.parseLong(columns[2]); // 문자열값을 long타입으로 파싱
			}
		}
		
		return totalForMonth;
	}
}
