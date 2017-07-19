package util;

/**
 * Created by Elereman on 17.07.2017.
 */
public class PropertiesHolder {
    private static volatile PropertiesHolder instance;
    private static String classPath;
    private static String indexFileName = "index.txt";
    private static final String SEPARATOR = "::";

    private PropertiesHolder() {
        StringBuilder path;
        path = new StringBuilder(PropertiesHolder.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        path.delete(path.lastIndexOf("/"), path.length());
        path.append('/');

        if (System.getProperty("os.name").startsWith("Windows")) {
            classPath = path.toString().substring(1);
        } else {
            classPath = path.toString();
        }
    }

    public static PropertiesHolder getInstance() {
        if (instance == null) {
            synchronized (PropertiesHolder.class) {
                if (instance == null) {
                    instance = new PropertiesHolder();
                    return instance;
                }
            }
        }
        return instance;
    }

    public static String getSeparator() {
        if (isInstanceNull()) {
            getInstance();
        }
        return SEPARATOR;
    }

    public static String getClassPath() {
        if (isInstanceNull()) {
            getInstance();
        }
        return classPath;
    }

    public static String getIndexFileName() {
        if (isInstanceNull()) {
            getInstance();
        }
        return indexFileName;
    }

    private static boolean isInstanceNull() {
        return instance == null;
    }
}
