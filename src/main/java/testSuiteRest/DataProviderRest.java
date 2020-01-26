package testSuiteRest;

import org.testng.annotations.DataProvider;

public class DataProviderRest {

    @DataProvider(name = "data_login_rest_test")
    public static Object[][] dataProviderLoginRestMethod() {
        return  new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "SUCCESS"},
                {"simon_dow@some.domaine.com", "123456789", "SUCCESS"},
                {"john_dow@some.domaine.com", "123", "FAILED"},
                {"simon_dow@some.domaine.com", "123", "FAILED"},
                {"simon_dow@some.domaine.com", "", "FAILED"},
                {"", "123456789", "FAILED"},
                {"", "", "FAILED"},
                {"sam.com", "987", "FAILED"},
        };
    }

    @DataProvider(name = "data_logout_rest_test")
    public static Object[][] dataProviderLogoutRestMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "login-page"},
                {"simon_dow@some.domaine.com", "123456789", "login-page"},
        };
    }

    @DataProvider(name = "data_insert_rest_test")
    public static Object[][] dataProviderInsertRestMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "Note example"},
                {"simon_dow@some.domaine.com", "123456789", "Note example"},
        };
    }

    @DataProvider(name = "data_remove_rest_test")
    public static Object[][] dataProviderRemoveRestMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "420"},
                {"simon_dow@some.domaine.com", "123456789", "422"},
        };
    }

    @DataProvider(name = "data_getall_rest_test")
    public static Object[][] dataProviderGetAllRestMethod() {
        return new Object[][]{
                {"john_dow@some.domaine.com", "123456789", "todoList"},
                {"simon_dow@some.domaine.com", "123456789", "todoList"},
        };
    }
}
