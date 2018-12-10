package annotation;

import java.util.List;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/7 10:44
 * @Description:使用注释
 */
public class PasswordUtil {

    @UseCase(id=47,description = "校验密码")
    public boolean validatePassword(String password)
    {
        return password.matches("\\w*\\d\\w*");
    }

    @UseCase(id=48)
    public String encryptPassword(String password)
    {
        return new StringBuilder(password).reverse().toString();
    }

    @UseCase(id=49,description = "new password can't contain previous password")
    public boolean checkForNewPassword(List<String> oldPasswords, String password)
    {
        return !oldPasswords.contains(password);
    }
}
