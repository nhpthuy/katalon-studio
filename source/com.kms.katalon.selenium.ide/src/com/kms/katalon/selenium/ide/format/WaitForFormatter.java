package com.kms.katalon.selenium.ide.format;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.kms.katalon.selenium.ide.model.Command;

public class WaitForFormatter implements Formatter {
	
	@Override
	public String format(Command command) {
		String formatted = StringUtils.EMPTY;
		try {
			formatted = checked(command);
			if (StringUtils.isBlank(formatted)) {
				formatted = notChecked(command);
			}
			if (StringUtils.isBlank(formatted)) {
				formatted = whether(command);
			}
			if (StringUtils.isBlank(formatted)) {
				formatted = notWhether(command);
			}
			if (StringUtils.isBlank(formatted)) {
				formatted = notPresent(command);
			}
			if (StringUtils.isBlank(formatted)) {
				formatted = present(command);
			}
			if (StringUtils.isBlank(formatted)) {
				formatted = normal(command);
			}
		} catch (Exception e) {
			formatted = String.format("Method %s is not found", command.getCommand());
		}
		return formatted + "\n";
	}
	
	private String normal(Command command) throws Exception {
		StringBuffer formatted = new StringBuffer();
		Pattern pattern = Pattern.compile("(waitFor)(.*?)$");
		Matcher matcher = pattern.matcher(command.getCommand());
		if (matcher.find()) {
			String suffixMethodName = matcher.group(2);
			String condition = conditionWithMatchingOrNotForWaitFor(suffixMethodName, command.getTarget(), command.getValue());
			return returnPattern(condition);
		}
		return formatted.toString();
	}

	private String present(Command command) throws Exception {
		StringBuffer formatted = new StringBuffer();
		Pattern pattern = Pattern.compile("(waitFor)(.*?)(Present)");
		Matcher matcher = pattern.matcher(command.getCommand());
		if (matcher.find()) {
			String method = getBoolMethod(matcher.group(2), command.getTarget());
			return returnPattern(method);
		}
		return formatted.toString();
	}

	private String notPresent(Command command) throws Exception {
		StringBuffer formatted = new StringBuffer();
		Pattern pattern = Pattern.compile("(waitFor)(.*?)(Not)(Present)");
		Matcher matcher = pattern.matcher(command.getCommand());
		if (matcher.find()) {
			String method = getBoolMethod(matcher.group(2), command.getTarget());
			return returnPattern("!" + method);
		}
		return formatted.toString();
	}
	
	public String checked(Command command) throws Exception {
		Pattern pattern = Pattern.compile("(waitFor)(Checked|Editable|Ordered|Visible|SomethingSelected)$");
		Matcher matcher = pattern.matcher(command.getCommand());
		if (matcher.find()) {
			String method = getBoolCheckedMethod(matcher.group(2), command.getTarget());
			return returnPattern(method);
		}
		return StringUtils.EMPTY;
	}
	
	public String whether(Command command) throws Exception {
		Pattern pattern = Pattern.compile("(waitFor)(Whether.*?)$");
		Matcher matcher = pattern.matcher(command.getCommand());
		if (matcher.find()) {
			String method = getBoolWhetherMethod(matcher.group(2), command.getTarget(), command.getValue());
			return returnPattern(method);
		}
		return StringUtils.EMPTY;
	}
	
	public String notWhether(Command command) throws Exception {
		Pattern pattern = Pattern.compile("(waitForNot)(Whether.*?)$");
		Matcher matcher = pattern.matcher(command.getCommand());
		if (matcher.find()) {
			String method = getBoolWhetherMethod(matcher.group(2), command.getTarget(), command.getValue());
			return returnPattern("!" + method);
		}
		return StringUtils.EMPTY;
	}
	
	public String notChecked(Command command) throws Exception {
		Pattern pattern = Pattern.compile("(waitForNot)(Checked|Editable|Ordered|Visible|SomethingSelected)$");
		Matcher matcher = pattern.matcher(command.getCommand());
		if (matcher.find()) {
			String method = getBoolCheckedMethod(matcher.group(2), command.getTarget());
			return returnPattern("!" + method);
		}
		return StringUtils.EMPTY;
	}

	private String returnPattern(String condition) {
		return "selenium.waitFor({" + condition + "})";
	}
	
}