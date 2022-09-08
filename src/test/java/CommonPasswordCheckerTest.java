import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CommonPasswordCheckerTest {
    CommonPasswordChecker commonPasswordChecker = new CommonPasswordChecker();

    @ParameterizedTest
    @ValueSource(strings = {"1234567", "qwerty", "abcdef", "putinhuilo"})
    public void commonPasswordsHasRequiredPasswords(String password) {
        assertTrue(commonPasswordChecker.checkCommonPassword(password));
    }
}
