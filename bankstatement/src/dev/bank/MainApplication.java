package dev.bank;

public class MainApplication {

	public static void main(String[] args) {
		// 입출금 분석기 실행
		String filename = args[0]; // ex. args = "bank-data.csv"
		BankStatementAnalyzer.analyze(filename); // static 키워드
	}

}
