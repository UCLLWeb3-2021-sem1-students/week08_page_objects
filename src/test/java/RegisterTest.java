import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterTest {
	private WebDriver driver;
	private String path = "http://localhost:8080/Controller";
	
	@Before
	public void setUp() {
		//System.setProperty("webdriver.chrome.driver", "/Users/.../web3pers/chromedriver");
			// windows: gebruik dubbele \\ om pad aan te geven
			// hint: zoek een werkende test op van web 2 ...
		System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");
		driver = new ChromeDriver();
	}
	
	@After
	public void clean() {
		driver.quit();
	}

	@Test
	public void test_Register_AllFieldsFilledInCorrectly_UserIsRegistered() {
		// GIVEN STEP = context
		SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		signUpPage.setFirstName("Jan");
		signUpPage.setLastName("Janssens");
		signUpPage.setEmail("jan.janssens@hotmail.com");
		signUpPage.setPassword("1234");

		// WHEN STEP = action
		HomePage homePage = signUpPage.submitValid();

		// THEN STEP = result
		assertEquals("Home", homePage.getTitle());
		PersonOverviewPage personOverviewPage = new PersonOverviewPage(driver);
		assertTrue(personOverviewPage.containsUserWithEmail("jan.janssens@hotmail.com"));
	}
	
	@Test
	public void test_Register_FirstNameNotFilledIn_ErrorMessageGivenForFirstNameAndOtherFieldsValueKept(){
		// werkt niet => dan vindt hij de webelements niet via @FindBy annotation
		// SignUpPage signUpPage = new SignUpPage(driver);
		SignUpPage signUpPage = PageFactory.initElements(driver, SignUpPage.class);
		signUpPage.setFirstName("");
		signUpPage.setLastName("Janssens");
		signUpPage.setEmail("jan.janssens@hotmail.com");
		signUpPage.setPassword("1234");

		signUpPage.submitInvalid();

		assertEquals("Sign Up", signUpPage.getTitle());
		assertTrue(signUpPage.hasErrorMessage("No firstname given"));
		assertTrue(signUpPage.hasEmptyFirstName());
		assertTrue(signUpPage.hasStickyLastName("Janssens"));
		assertTrue(signUpPage.hasStickyEmail("jan.janssens@hotmail.com"));
	}
// TO DO: REFACTOR WITH PAGE OBJECT PATTERN
/*
	@Test
	public void test_Register_LastNameNotFilledIn_ErrorMessageGivenForLastNameAndOtherFieldsValueKept(){
		submitForm("jakke", "Jan", "", "jan.janssens@hotmail.com", "1234");
		
		String title = driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No last name given", errorMsg.getText());

		WebElement fieldUserid=driver.findElement(By.id("userid"));
		assertEquals("jakke",fieldUserid.getAttribute("value"));

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
	}

	@Test
	public void test_Register_EmailNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
		submitForm("jakke", "Jan", "Janssens", "", "1234");
		
		String title = driver.getTitle();
		assertEquals("Sign Up",title);

		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No email given", errorMsg.getText());

		WebElement fieldUserid=driver.findElement(By.id("userid"));
		assertEquals("jakke",fieldUserid.getAttribute("value"));
		
		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("",fieldEmail.getAttribute("value"));
	}


	@Test
	public void test_Register_PasswordNotFilledIn_ErrorMessageGivenForEmailAndOtherFieldsValueKept(){
		submitForm("jakke", "Jan", "Janssens", "jan.janssens@hotmail.com", "");
		
		String title = driver.getTitle();
		assertEquals("Sign Up",title);
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("No password given", errorMsg.getText());

		WebElement fieldUserid=driver.findElement(By.id("userid"));
		assertEquals("jakke",fieldUserid.getAttribute("value"));

		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Jan",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Janssens",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("jan.janssens@hotmail.com",fieldEmail.getAttribute("value"));
	}
	
	@Test
	public void test_Register_UserAlreadyExists_ErrorMessageGiven(){
		String useridRandom = generateRandomUseridInOrderToRunTestMoreThanOnce("pierke");
		submitForm(useridRandom, "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");
		
		driver.get(path+"?action=signUp");

		submitForm(useridRandom, "Pieter", "Pieters", "pieter.pieters@hotmail.com", "1234");
		
		WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
		assertEquals("User already exists", errorMsg.getText());

		WebElement fieldUserid=driver.findElement(By.id("userid"));
		assertEquals(useridRandom,fieldUserid.getAttribute("value"));
		
		WebElement fieldFirstName=driver.findElement(By.id("firstName"));
		assertEquals("Pieter",fieldFirstName.getAttribute("value"));
		
		WebElement fieldLastName=driver.findElement(By.id("lastName"));
		assertEquals("Pieters",fieldLastName.getAttribute("value"));
		
		WebElement fieldEmail=driver.findElement(By.id("email"));
		assertEquals("pieter.pieters@hotmail.com",fieldEmail.getAttribute("value"));
	}
*/
}
