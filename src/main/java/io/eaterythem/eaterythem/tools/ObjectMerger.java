package io.eaterythem.eaterythem.tools;

import java.lang.reflect.Field;

public class ObjectMerger {

    /**
     * Merge non-null fields from source into target.
     * @param target Object to update
     * @param source Object with values to copy
     * @param <T> mealType of the objects
     * @return target after merge
     */
    public static <T> T mergeNonNullFields(T target, T source) {
        if (target == null || source == null) {
            throw new IllegalArgumentException("Target and source cannot be null");
        }

        Class<?> clazz = target.getClass();
        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            field.setAccessible(true); // allow access to private fields
            try {
                Object value = field.get(source);
                if (value != null) {
                    field.set(target, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException("Failed to merge fields", e);
            }
        }
        return target;
    }
}
