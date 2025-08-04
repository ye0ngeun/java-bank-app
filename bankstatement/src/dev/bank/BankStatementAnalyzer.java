package dev.bank;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;

import dev.bank.model.BankTransaction;
import dev.bank.parser.BankDataTSVParser;
import dev.bank.service.BankProcessor;

public class BankStatementAnalyzer {
	
	private static final String RESOURCES = "resources/";
	
	public static void main(String[] args) {
		// 1. 입출금 내역 파일 읽기
		// 1-1. 파일을 읽기 위한 해당 경로(Path)에 대한 정보
		final Path path = Paths.get(RESOURCES + "bank-data.txt");
		
		// 1-2. 실제 파일 읽기
		try {
			List<String> lines = Files.readAllLines(path);
			
			if(lines.isEmpty()) {
				System.out.println("입출금 내역이 존재하지 않습니다.");
				return; // main을 종료
			}
			
			// 2. 입출금내역 파일 파싱
			BankDataTSVParser tsvParser = new BankDataTSVParser();
			List<BankTransaction> bankTransactions = tsvParser.parseLinesFromTSV(lines);
			
			// 3. 입출금 내역 조회 로직을 위한 BankProcessor 객체 생성
			BankProcessor processor = new BankProcessor(bankTransactions);
			
			// 4. 총 입출금 내역 조회 및 출력
			String result = String.format("총 입출금액은 %d원 입니다", processor.calculateTotalAmount());
			System.out.println(result);
			
			// 5. 특정 월의 입출금 내역 조회 및 출력
			String resultForMonth = String.format("1월의 입출금액은 %d원 입니다.", processor.calculateTotalAmountInMonth(Month.JANUARY));
			System.out.println(resultForMonth);
			
		} catch(IOException e) {
			System.out.println("입출금 파일 내역이 존재하지 않습니다.");
		}
	}
	
	
}
