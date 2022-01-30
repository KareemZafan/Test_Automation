Feature: Test Login functionality

Scenario Outline: Login with invalid credentials
  Given User open the website and go to login page
  When  User fill email as "<username>" and "<pass>" and click on login
  Then Error message should appear
  Examples:
    |  username | pass     |
    |  qacart   | password |
    |  ahmed    | ahmed    |
    |  kareem   | kareem   |

