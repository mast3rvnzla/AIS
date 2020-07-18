package es.codeurjc.ais.tictactoe;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

class TestSistema {
	
	protected WebDriver jugador1;
	protected WebDriver jugador2;
	
	@BeforeAll
	 public static void setupClass() {
	 //System.setProperty("webdriver.chrome.driver",
	 //"/usr/bin/chromedriver/chromedriver");
		
		WebDriverManager.chromedriver().setup();
	 }
	
	@BeforeEach
	 public void setupTest() {
		 jugador1 = new ChromeDriver();
		 jugador2 = new ChromeDriver();
		 jugador1.get("localhost:8080");
		 jugador1.findElement(By.id("nickname")).sendKeys("jugador1");
			
		 jugador2.get("localhost:8080");
		 jugador2.findElement(By.id("nickname")).sendKeys("jugador2");
			
		 jugador1.findElement(By.id("startBtn")).click();
		 jugador2.findElement(By.id("startBtn")).click();
	 }
	
	@AfterEach
	 public void teardown() {
		 if (jugador1 != null) {
			 jugador1.quit();
		 }
		 if (jugador2 != null) {
			 jugador2.quit();
		 }
	 }

	@Test
	void primerJugadorQuePoneFichaGana() {
		
		 jugador1.findElement(By.id("cell-0")).click();
		 jugador2.findElement(By.id("cell-4")).click();
		 jugador1.findElement(By.id("cell-1")).click();
		 jugador2.findElement(By.id("cell-2")).click();
		 jugador1.findElement(By.id("cell-6")).click();
		 jugador2.findElement(By.id("cell-5")).click();
		 jugador1.findElement(By.id("cell-3")).click();
		 assertEquals(jugador1.switchTo().alert().getText(),"jugador1 wins! jugador2 looses.");
		 assertEquals(jugador2.switchTo().alert().getText(),"jugador1 wins! jugador2 looses.");

	}
	
	@Test
	void segundoJugadorQuePoneFichaGana() {
		
		 jugador1.findElement(By.id("cell-0")).click();
		 jugador2.findElement(By.id("cell-4")).click();
		 jugador1.findElement(By.id("cell-1")).click();
		 jugador2.findElement(By.id("cell-2")).click();
		 jugador1.findElement(By.id("cell-6")).click();
		 jugador2.findElement(By.id("cell-3")).click();
		 jugador1.findElement(By.id("cell-7")).click();
		 jugador2.findElement(By.id("cell-5")).click();

		 assertEquals(jugador1.switchTo().alert().getText(),"jugador2 wins! jugador1 looses.");
		 assertEquals(jugador2.switchTo().alert().getText(),"jugador2 wins! jugador1 looses.");

	}
	
	@Test
	void empate() {
		
		 jugador1.findElement(By.id("cell-0")).click();
		 jugador2.findElement(By.id("cell-4")).click();
		 jugador1.findElement(By.id("cell-1")).click();
		 jugador2.findElement(By.id("cell-2")).click();
		 jugador1.findElement(By.id("cell-6")).click();
		 jugador2.findElement(By.id("cell-3")).click();
		 jugador1.findElement(By.id("cell-5")).click();
		 jugador2.findElement(By.id("cell-7")).click();
		 jugador1.findElement(By.id("cell-8")).click();

		 assertEquals(jugador1.switchTo().alert().getText(),"Draw!");
		 assertEquals(jugador2.switchTo().alert().getText(),"Draw!");

	}
}
