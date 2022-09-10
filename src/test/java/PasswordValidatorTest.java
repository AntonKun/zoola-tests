import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;

class PasswordValidatorTest {
    CommonPasswordChecker commonPasswordChecker = Mockito.mock(CommonPasswordChecker.class);
    PasswordValidator passwordValidator = new PasswordValidator(commonPasswordChecker);

    @Test
    public void nullPasswordShouldReturnFalse() {
        assertFalse(passwordValidator.checkPasswordNull(null));
    }

    @Test
    public void passwordShouldBeLargerThanEight() {
        assertFalse(passwordValidator.checkPasswordLength("short"));
    }

    @Test
    public void passwordShouldBeSmallerThanTwentyFive() {
        assertFalse(passwordValidator.checkPasswordLength("longlonglonglonglonglonglonglong"));
    }

    @Test
    public void passwordShouldBeSmallerThanTwentyFiveAndLargerThanEight() {
        assertTrue(passwordValidator.checkPasswordLength("greatPassword"));
    }

    @Test
    public void passwordShouldNotConsistOnlyLowerCase() {
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("lowercase"));
    }

    @Test
    public void passwordShouldNotConsistOnlyUpperCase() {
        assertFalse(passwordValidator.checkPasswordUpperLowerCase("UPPERCASE"));
    }

    @Test
    public void passwordShouldConsistUpperAndLowerCase() {
        assertTrue(passwordValidator.checkPasswordUpperLowerCase("UPPERCASElowercase"));
    }

    @Test
    public void passwordShouldHaveNumber() {
        assertFalse(passwordValidator.checkPasswordHasNumber("noNumbers"));
    }

    @Test
    public void passwordShouldHaveSpecialCharacter() {
        assertFalse(passwordValidator.checkPasswordSpecialCharacter("mypassword"));
    }

    @Test
    public void passwordShouldNotHaveContinuousNumbers() {
        assertFalse(passwordValidator.checkPasswordContinuousNumbers("123asd"));
    }

    @Test
    public void passwordShouldNotHaveSameNumber() {
        assertFalse(passwordValidator.checkPasswordSameNumber("1111465"));
    }

    @Test
    public void passwordShouldMeetAllRequirements() {
        Mockito.when(commonPasswordChecker.checkCommonPassword(anyString())).thenReturn(false);
        assertTrue(passwordValidator.checkPassword("ReallyC00lP@$$wWorDD"));
    }
}
