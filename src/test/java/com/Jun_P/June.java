package com.Jun_P;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.base.Base_Class;
import com.interfaceelements.LoginPageInterfaceElement;

public class June extends Base_Class implements LoginPageInterfaceElement {

	@FindBy(xpath = signIn_xpath)
	private WebElement signin;
	@FindBy(xpath = email_xpath)
	private WebElement email;
	@FindBy ( xpath = password_xpath )
	private WebElement password;
	@FindBy(xpath = Login_xpath)
	private WebElement Login;

	
	public June() {
		PageFactory.initElements(driver, this);
	}
	
}

