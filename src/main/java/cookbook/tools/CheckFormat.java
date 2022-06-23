package cookbook.tools;

public class CheckFormat {
    public static boolean isNumber(String num) {
        return num.matches("[\\d]+");
    }

    public static void main(String[] args) {
        System.out.println(isNumber("123"));
        System.out.println(isNumber("ads"));
    }

    public static boolean isRecipeName(String num) {
        return num.matches("[a-zA-z\\s]+");
    }

    public static boolean isUserName(String username) {
        return username.matches("^[a-zA-Z]\\w{5,17}$");

    }

    public static boolean isPassword(String password) {
        return password.matches("^[a-zA-Z0-9]{6,16}$");

    }

}
