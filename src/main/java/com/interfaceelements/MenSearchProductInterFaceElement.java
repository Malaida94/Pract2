package com.interfaceelements;

public interface MenSearchProductInterFaceElement {
	String men_xpath = "(//span[@class='ui-menu-icon ui-icon ui-icon-carat-1-e'])[4]";
	String jackets_LineTextxpath= "//a[text()='Jackets']";
	String model_dressxpath ="(//a[@class='product-item-link'])[4]";
	String size_xpath ="(//div[text()='M'])";
	String colour_xpath = "(//div[@class='swatch-option color'])[3]";
	String addtocart_xpath = "(//button[@title='Add to Cart'])";
}
