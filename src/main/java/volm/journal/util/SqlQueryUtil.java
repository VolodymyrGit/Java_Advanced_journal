package volm.journal.util;

import volm.journal.annotation.IgnoreColumn;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SqlQueryUtil {

    public static <T> String getSaveQuery(T obj) {
        Class currentClass = obj.getClass();
        String tableName = currentClass.getSimpleName();
        List<String> fieldsNames = Stream.of(currentClass.getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .filter(f -> !f.isAnnotationPresent(IgnoreColumn.class))
                .map(f -> f.getName())
                .collect(Collectors.toList());

        List<String> values = Stream.of(currentClass.getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .filter(f -> !f.isAnnotationPresent(IgnoreColumn.class))
                .map(f -> {
                    try {
                        String s = f.get(obj).toString();
                        return "'" + s + "'";
                    } catch (IllegalAccessException e) {
                        return null;
                    }
                })
                .collect(Collectors.toList());
        return "INSERT INTO " + tableName +
                " (" + toLine(fieldsNames) + ") VALUES (" +
                toLine(values) + ");";
    }


    public static <T, ID> String getFindByIdQuery(T obj, ID modelId) {
        String tableName = getTableName(obj);
        return "SELECT * FROM " + tableName + " WHERE id = " + modelId;
    }



    private static <T> String getTableName(T obj) {
        return obj.getClass().getSimpleName();
    }


    private static String toLine(List<String> list) {
        StringBuilder sb = new StringBuilder();
        list.forEach(s -> sb.append(s).append(","));
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
