Feature: Login

	Scenario: Successful Login
	Given the login page is opened
	When I enter valid users's email in the email field
	And I enter valid password in the password field
	And I submit
	Then my email should be displayed in the upper right corner
	And I should be able to logout
    
	Scenario: Unsuccessful Login
	Given the login page is opened
	When I enter invalid users's email in the email field
	And I enter invalid password in the password field
	And I submit
	Then an error text appears
	And I'm not logged in
