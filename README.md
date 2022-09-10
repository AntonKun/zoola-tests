# zoola-tests
Initial code of **checkPassword** method of **PasswordValidator** class was changed a little bit, cuz I didn't understand why we need to check if password is present in our commonPasswords set :) 
In my opinion, it is better to keep there too simple passwords that are **not** allowed to use (i. e. password, 123123, qwerty and etc). Therefore, I have changed

```java
if (!commonPassword.checkCommonPassword(password)) return false;
```
to
```java
if (commonPassword.checkCommonPassword(password)) return false;
```
...and move this statement to the top of the checkPassword method, so that we don't have to do other checks if the password is in our list of simple passwords.

To implement init tests for PasswordValidator, I used the Mockito approach:

```java
CommonPasswordChecker commonPasswordChecker = Mockito.mock(CommonPasswordChecker.class);
```

```java
    @Test
    void passwordShouldMeetAllRequirements() {
        Mockito.when(commonPasswordChecker.checkCommonPassword(anyString())).thenReturn(false);
        assertTrue(passwordValidator.checkPassword("ReallyC00lP@$$wWorDD"));
    }
```

I also attach proof that all lines are covered by tests:

![image](https://user-images.githubusercontent.com/59668952/189089619-6528e6c0-e879-4241-90a1-40fb2cd4fc90.png)
