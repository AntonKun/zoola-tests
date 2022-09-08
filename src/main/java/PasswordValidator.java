public class PasswordValidator {
    private final CommonPasswordChecker commonPassword;

    public PasswordValidator(CommonPasswordChecker commonPasswordChecker) {
        this.commonPassword = commonPasswordChecker;
    }

    public boolean checkPassword(String password) {
        if (commonPassword.checkCommonPassword(password)) return false;
        if (!checkPasswordNull(password)) return false;
        if (!checkPasswordLength(password)) return false;
        if (!checkPasswordUpperLowerCase(password)) return false;
        if (!checkPasswordHasNumber(password)) return false;
        if (!checkPasswordSpecialCharacter(password)) return false;
        if (!checkPasswordContinuousNumbers(password)) return false;
        if (!checkPasswordSameNumber(password)) return false;
        else return true;
    }

    public boolean checkPasswordNull(String password) {
        return password != null;
    }

    public boolean checkPasswordLength(String password) {
        return password.length() >= 8 && password.length() <= 25;
    }

    public boolean checkPasswordUpperLowerCase(String password) {
        return !password.equals(password.toLowerCase()) && !password.equals(password.toUpperCase());
    }

    public boolean checkPasswordHasNumber(String password) {
        int letterCount = 0;
        char[] pwArr = password.toCharArray();
        for (char c : pwArr) {
            if (Character.isDigit(c)) letterCount++;
        }
        return letterCount != 0;
    }

    public boolean checkPasswordSpecialCharacter(String password) {
        int specialCount = 0;
        char[] pwArr = password.toCharArray();
        for (char c : pwArr) {
            if (c < 'A' || c > 'z' || (c > 'Z' && c < 'a')) {
                switch (c) {
                    case '(':
                    case ')':
                    case '#':
                    case '$':
                    case '?':
                    case '!':
                    case '%':
                    case '/':
                    case '@':
                        specialCount++;
                        break;
                    default:
                        break;
                }
            }
        }
        return specialCount != 0;
    }

    public boolean checkPasswordContinuousNumbers(String password) {
        char[] pwArr = password.toCharArray();
        for (int i = 0; i < pwArr.length; i++) {
            if (pwArr[i] >= '0' && pwArr[i] <= '9') {
                if (i < (pwArr.length - 2) &&
                        Character.getNumericValue(pwArr[i]) == Character.getNumericValue(pwArr[i + 1]) - 1 &&
                        Character.getNumericValue(pwArr[i]) == Character.getNumericValue(pwArr[i + 2]) - 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkPasswordSameNumber(String password) {
        char[] pwArr = password.toCharArray();
        for (int i = 0; i < pwArr.length; i++) {
            if (pwArr[i] >= '0' && pwArr[i] <= '9') {
                if (i < (pwArr.length - 3) && pwArr[i] == pwArr[i + 1] && pwArr[i] == pwArr[i + 2] && pwArr[i] == pwArr[i + 3]) {
                    return false;
                }
            }
        }
        return true;
    }
}
