package com.koreait.crawling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BanaPresso {
	public static void main(String[] args) {
		 String DRIVER_ID = "webdriver.chrome.driver";
	      String DRIVER_PATH = "C:/Gookbi/JSP/chromedriver.exe";

		System.setProperty(DRIVER_ID, DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		String base_url = "https://www.banapresso.com/store";

		try {
			driver.get(base_url);

			List<WebElement> elements1 = driver.findElements(By.cssSelector(".store_name_map i"));
																
			List<WebElement> elements2 = driver.findElements(By.cssSelector(".store_name_map > span"));		

			for(int j = 0; j < elements1.size();j++) {
				System.out.println("매장 명 : "+ elements1.get(j).getText());
				System.out.println("위치 : "+ elements2.get(j).getText());
				System.out.println("-----------------------------------------------");
				Thread.sleep(1000);						
			}

			int i = 2;
			while (true) {
				try {
					WebElement Nextpage = driver.findElement(By.cssSelector(".pagination > ul > li:nth-child(" + i + ")"));
					
					Nextpage.click();

					elements1 = driver.findElements(By.cssSelector(".store_name_map i"));
																//store_name_map 클래스 안의 모든 i태그 css요소 선택 
					elements2 = driver.findElements(By.cssSelector(".store_name_map > span"));
																//store_name_map 클래스 안의 바로 하위 span 태그 css요소 선택 
					for(int j = 0; j < elements1.size();j++) {
						System.out.println("매장 명 : " +elements1.get(j).getText());
						System.out.println("위치 : "+elements2.get(j).getText());
						System.out.println("-----------------------------------------------");
						Thread.sleep(1000);						
					}
					
					if(i < 5) {
						i++;
					}else if(i == 5){
						WebElement Nextpage1 = driver.findElement(By.cssSelector(".pagination > span > a"));
						Nextpage1.click();
						i = 1;
					}
				} catch (Exception e) {
					System.out.println("프로그램을 종료합니다");
					break;					
				}
			}			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

