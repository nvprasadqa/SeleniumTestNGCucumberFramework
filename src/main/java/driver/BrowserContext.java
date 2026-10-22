package driver;
public class BrowserContext {
    private static ThreadLocal<String> browser = new ThreadLocal<>();
    public static void set(String b) { browser.set(b.toLowerCase()); }
    public static String get() { return browser.get(); }
    public static void remove() { browser.remove(); }
}