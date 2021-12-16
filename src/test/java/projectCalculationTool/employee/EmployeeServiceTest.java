package projectCalculationTool.employee;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import projectCalculationTool.util.exception.ValidateException;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        employeeService = new EmployeeService(null);
    }

    @ParameterizedTest
    @CsvSource(value = {"test@", "test", "@hej.dk", ".", "hej.dk"})
    public void invalidEmailTest(String email) {
        assertThrows(ValidateException.class, () -> employeeService.validateEmail(email));
    }

    @ParameterizedTest
    @CsvSource(value = {"test@yes.dk", "test@hej.com", "hans@hej.dk", "kurt@hej.dk"})
    public void validEmailTest(String email) {
        assertDoesNotThrow(() -> employeeService.validateEmail(email));
    }

    @Test
    void emailTooLong() {
        String email = "Thismailisgoingtobeaverylong@emailandcrashtheprogram´justafewmorejusttobesafebecauseimnotsurehowlongthisisjhagshfgaslfhgasfhgasfkjahsgfjhsafgkjasfgkasfgkhasfgasjkfgsahfkghasgashkf.com";

        Throwable exception = assertThrows(ValidateException.class, () -> employeeService.validateEmail(email));

        assertEquals("Incorrect login information", exception.getMessage());
    }

    @Test
    void validatePasswordLength() throws ValidateException {
        String pw = "what a password";

        String actual = employeeService.validatePasswordLength(pw);

        assertEquals(pw, actual);
    }

    @Test
    void invalidatePasswordLengthToLong() {
        String pw = "what a passwordadasdasdasdasdasdasdasdasdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddpasswordadasdasdasdasdasdasdasdasdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddpasswordadasdasdasdasdasdasdasdasdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddpasswordadasdasdasdasdasdasdasdasdasdddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd";

        assertThrows(ValidateException.class, () -> employeeService.validatePasswordLength(pw));
    }

    @Test
    void invalidatePasswordLengthNull() {
        String pw = null;

        assertThrows(ValidateException.class, () -> employeeService.validatePasswordLength(pw));
    }
}