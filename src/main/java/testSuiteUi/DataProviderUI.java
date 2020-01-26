package testSuiteUi;

import org.testng.annotations.DataProvider;

public class DataProviderUI {

    @DataProvider(name = "data_login_ui_test")
    public static Object[][] dataProviderLoginUiMethod() {
        return  new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "http://localhost:7844/main"},
                {"simon_dow@some.domaine.com", "123456789", "http://localhost:7844/main"},
                {"john_dow@some.domaine.com", "123", "http://localhost:7844/login"},
                {"simon_dow@some.domaine.com", "123", "http://localhost:7844/login"},
                {"simon_dow@some.domaine.com", "", "http://localhost:7844/login"},
                {"", "123456789", "http://localhost:7844/login"},
                {"", "", "http://localhost:7844/login"},
                {"sam.com", "987", "http://localhost:7844/login"},
        };
    }

    @DataProvider(name = "data_logout_ui_test")
    public static Object[][] dataProviderLogoutUiMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "http://localhost:7844/login"},
                {"simon_dow@some.domaine.com", "123456789", "http://localhost:7844/login"},
        };
    }

    @DataProvider(name = "data_positive_insert_ui_test")
    public static Object[][] dataProviderPositiveInsertUiMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "Note example"},
                {"simon_dow@some.domaine.com", "123456789", "Note example"},
        };
    }

    @DataProvider(name = "data_negative_insert_ui_test")
    public static Object[][] dataProviderNegativeInsertUiMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", ""},
                {"simon_dow@some.domaine.com", "123456789", "Software testing is an investigation conducted to provide stakeholders with information about the quality of the software product or service under test."},
        };
    }

    @DataProvider(name = "data_remove_ui_test")
    public static Object[][] dataProviderRemoveUiMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "2"},
                {"simon_dow@some.domaine.com", "123456789", "1"},
        };
    }
}
