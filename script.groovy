#!/usr/bin/env groovy
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.GnuParser;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.Parser;

public class OptionsParser {
	public static void main(String[] args) throws IOException {
		
		BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
		while (sin.ready()) {
			System.out.println(sin.readLine());
		}
		// create the command line parser
		Parser parser = new GnuParser();

		// create the Options
		Options options = new Options();
		options.addOption("a", "all", false,
				"do not hide entries starting with .");
		options.addOption("A", "almost-all", false,
				"do not list implied . and ..");
		options.addOption("b", "escape", false,
				"print octal escapes for nongraphic " + "characters");
		options.addOption(OptionBuilder.withLongOpt("block-size")
				.withDescription("use SIZE-byte blocks").hasArg()
				.withArgName("SIZE").create());
		options.addOption("B", "ignore-backups", false,
				"do not list implied entried " + "ending with ~");
		options.addOption("c", false,
				"with -lt: sort by, and show, ctime (time of last "
						+ "modification of file status information) with "
						+ "-l:show ctime and sort by name otherwise: sort "
						+ "by ctime");
		options.addOption("C", false, "list entries by columns");

		// This comes from run.sh
		//args = new String[1];
		//args[0]= "--block-size=10" ;

		try {
			// parse the command line arguments
			CommandLine line = parser.parse(options, args);

			System.out.println("leftovers: " + line.getArgs());
			
			// validate that block-size has been set
			if (line.hasOption("block-size")) {
				// print the value of block-size
				System.out.println(line.getOptionValue("block-size"));
			}
		} catch (ParseException exp) {
			System.out.println("Unexpected exception:" + exp.getMessage());
		}
	}
}
