package annotation.DataBaseAnnotation;

/**
 * @Auther: mengxiangxiang
 * @Date: 2018/12/7 15:36
 * @Description:
 */
@DBTable(name = "MEMBER")
public class Member {
    @SQLString(20)  String firstName;
    @SQLString(30)  String lastName;
    @SQLInteger Integer age;
    @SQLString(value=30,constraints=@Constraints(primaryKey = true))
    String handle;
    static int memberCount;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public static int getMemberCount() {
        return memberCount;
    }
    public String toString()
    {
        return handle;
    }
}
