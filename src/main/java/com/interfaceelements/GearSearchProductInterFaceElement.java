package com.interfaceelements;

public interface GearSearchProductInterFaceElement {
  String Gear_xpath = "//span[text()='Gear']";
  String Bag_xpath ="//a[text()='Bags']";
  String BagName_xpath = "(//a[@class='product-item-link'])[2]";
  String BagCount_xpath ="//input[@id='qty']";
  String AddToCart_xpath="(//button[@type='submit'])[2]";
 
	
	
}
