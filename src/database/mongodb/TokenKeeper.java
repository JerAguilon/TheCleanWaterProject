package database.mongodb;

/*
 * Created by jeremy on 10/23/16.
 */
class TokenKeeper {
    private static String currentToken = null;

    public static void setToken(String token) {
        currentToken = token;
    }

    public static String getToken() {
        return currentToken;
    }
}
