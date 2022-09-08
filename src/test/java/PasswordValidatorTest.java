import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class PasswordValidatorTest {
    CommonPasswordChecker commonPasswordChecker = Mockito.mock(CommonPasswordChecker.class);
    PasswordValidator passwordValidator = new PasswordValidator(commonPasswordChecker);

    @Test
    void nullPasswordShouldReturnFalse() {
        assertFalse(passwordValidator.checkPasswordNull(null));
    }

    @Test
    void passwordShouldBeLargerThanEightAndSmallerThanTwentyFive() {
        assertFalse(passwordValidator.checkPasswordLength("short"));
        assertFalse(passwordValidator.checkPasswordLength("longlonglonglonglonglonglonglong"));
        assertTrue(passwordValidator.checkPasswordLength("123qwe456"));
    }

    @Test
    void passwordShouldConsistUpperAndLowerCases() {
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("lowercase"));
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("UPPERCASE"));
        assertTrue(passwordValidator.checkPasswordUpperLowerCase("lowerAndUpper"));
    }

    @Test
    void passwordShouldHaveNumber() {
        assertFalse(passwordValidator.checkPasswordHasNumber("noNumbers"));
        assertTrue(passwordValidator.checkPasswordHasNumber("w1thNumb3r5"));
    }

    @Test
    void passwordShouldHaveSpecialCharacter() {
        assertFalse(passwordValidator.checkPasswordSpecialCharacter("mypassword"));
        assertTrue(passwordValidator.checkPasswordSpecialCharacter("mypa$$word"));
    }

    @Test
    void passwordShouldNotHaveContinuousNumbers() {
        assertFalse(passwordValidator.checkPasswordContinuousNumbers("123asd"));
        assertTrue(passwordValidator.checkPasswordContinuousNumbers("32251asd"));
    }

    @Test
    void passwordShouldNotHaveSameNumber() {
        assertFalse(passwordValidator.checkPasswordSameNumber("1111465"));
        assertTrue(passwordValidator.checkPasswordSameNumber("15616431"));
    }

    @Test
    void passwordShouldMeetAllRequirements() {
        Mockito.when(commonPasswordChecker.checkCommonPassword(anyString())).thenReturn(false);
        assertTrue(passwordValidator.checkPassword("ReallyC00lP@$$wWorDD"));
    }
}
