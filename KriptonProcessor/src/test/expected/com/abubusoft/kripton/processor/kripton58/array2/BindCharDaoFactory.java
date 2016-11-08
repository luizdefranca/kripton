package com.abubusoft.kripton.processor.kripton58.array2;

import com.abubusoft.kripton.android.sqlite.BindDaoFactory;

/**
 * <p>
 * Represents dao factory interface for CharDataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see CharDataSource
 * @see CharDao
 * @see CharDaoImpl
 * @see CharBean
 */
public interface BindCharDaoFactory extends BindDaoFactory {
  /**
   *
   * retrieve dao CharDao
   */
  CharDaoImpl getCharDao();
}