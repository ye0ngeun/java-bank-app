package dev.bank.parser;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import dev.bank.model.BankTransaction;

/**
 * TSV - Tab seperated value, 탭으로 구분된 파일
 */
public class BankDataTSVParser implements BankDataParser {

	@Override
	public List<BankTransaction> parse(List<String> lines) {
		String delimeter = "\t";

		return createBankTransactions(lines, delimeter);
	}
}