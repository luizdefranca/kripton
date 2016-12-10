package sqlite.kripton64;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.abubusoft.kripton.android.KriptonLibrary;
import com.abubusoft.kripton.android.Logger;
import com.abubusoft.kripton.android.sqlite.AbstractDataSource;
import java.lang.Override;
import java.lang.String;

/**
 * <p>
 * Represents implementation of datasource Bean64DataSource.
 * This class expose database interface through Dao attribute.
 * </p>
 *
 * @see Bean64DataSource
 * @see BindBean64DaoFactory
 * @see BeanDao
 * @see BeanDaoImpl
 * @see Bean64
 */
public class BindBean64DataSource extends AbstractDataSource implements BindBean64DaoFactory, Bean64DataSource {
  /**
   * <p><singleton of datasource,/p>
   */
  private static BindBean64DataSource instance;

  /**
   * <p><file name used to save database,/p>
   */
  public static final String name = "dummy";

  /**
   * <p>database version</p>
   */
  public static final int version = 1;

  /**
   * <p>dao instance</p>
   */
  protected BeanDaoImpl beanDao = new BeanDaoImpl(this);

  protected BindBean64DataSource(Context context) {
    super(context, name, null, version);
  }

  @Override
  public BeanDaoImpl getBeanDao() {
    return beanDao;
  }

  /**
   * <p>executes a transaction. This method is synchronized to avoid concurrent problems. The database will be open in write mode.</p>
   *
   * @param transaction transaction to execute
   */
  public synchronized void execute(Transaction transaction) {
    SQLiteDatabase connection=openDatabase();
    try {
      connection.beginTransaction();
      if (transaction!=null && transaction.onExecute(this)) {
        connection.setTransactionSuccessful();
      }
    } finally {
      connection.endTransaction();
      close();
    }
  }

  /**
   * instance
   */
  public static synchronized BindBean64DataSource instance() {
    if (instance==null) {
      instance=new BindBean64DataSource(KriptonLibrary.context());
    }
    return instance;
  }

  /**
   * onCreate
   */
  @Override
  public void onCreate(SQLiteDatabase database) {
    // generate tables
    Logger.info("DDL: %s",Bean64Table.CREATE_TABLE_SQL);
    database.execSQL(Bean64Table.CREATE_TABLE_SQL);
  }

  /**
   * onUpgrade
   */
  @Override
  public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    // drop tables
    Logger.info("DDL: %s",Bean64Table.DROP_TABLE_SQL);
    database.execSQL(Bean64Table.DROP_TABLE_SQL);

    // generate tables
    Logger.info("DDL: %s",Bean64Table.CREATE_TABLE_SQL);
    database.execSQL(Bean64Table.CREATE_TABLE_SQL);
  }

  /**
   * interface to define transactions
   */
  public interface Transaction extends AbstractTransaction<BindBean64DaoFactory> {
  }
}