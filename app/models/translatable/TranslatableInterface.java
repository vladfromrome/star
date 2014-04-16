package models.translatable;

import javax.persistence.MappedSuperclass;

/**
 * Author: Vladimir Romanov
 * Date: 16.04.14
 * Time: 19:14
 */
public interface TranslatableInterface {
    public String getTranslation();
}
