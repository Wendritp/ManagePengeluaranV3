package com.app.ManagePengeluaran.utils.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "TBL_PENGELUARAN".
*/
public class TblPengeluaranDao extends AbstractDao<TblPengeluaran, Long> {

    public static final String TABLENAME = "TBL_PENGELUARAN";

    /**
     * Properties of entity TblPengeluaran.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property IdTblPengeluaran = new Property(0, Long.class, "idTblPengeluaran", true, "_id");
        public final static Property Pengeluaran = new Property(1, String.class, "pengeluaran", false, "PENGELUARAN");
        public final static Property Nominal = new Property(2, int.class, "nominal", false, "NOMINAL");
    }


    public TblPengeluaranDao(DaoConfig config) {
        super(config);
    }
    
    public TblPengeluaranDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"TBL_PENGELUARAN\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: idTblPengeluaran
                "\"PENGELUARAN\" TEXT," + // 1: pengeluaran
                "\"NOMINAL\" INTEGER NOT NULL );"); // 2: nominal
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"TBL_PENGELUARAN\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, TblPengeluaran entity) {
        stmt.clearBindings();
 
        Long idTblPengeluaran = entity.getIdTblPengeluaran();
        if (idTblPengeluaran != null) {
            stmt.bindLong(1, idTblPengeluaran);
        }
 
        String pengeluaran = entity.getPengeluaran();
        if (pengeluaran != null) {
            stmt.bindString(2, pengeluaran);
        }
        stmt.bindLong(3, entity.getNominal());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, TblPengeluaran entity) {
        stmt.clearBindings();
 
        Long idTblPengeluaran = entity.getIdTblPengeluaran();
        if (idTblPengeluaran != null) {
            stmt.bindLong(1, idTblPengeluaran);
        }
 
        String pengeluaran = entity.getPengeluaran();
        if (pengeluaran != null) {
            stmt.bindString(2, pengeluaran);
        }
        stmt.bindLong(3, entity.getNominal());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public TblPengeluaran readEntity(Cursor cursor, int offset) {
        TblPengeluaran entity = new TblPengeluaran( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // idTblPengeluaran
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // pengeluaran
            cursor.getInt(offset + 2) // nominal
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, TblPengeluaran entity, int offset) {
        entity.setIdTblPengeluaran(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setPengeluaran(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNominal(cursor.getInt(offset + 2));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(TblPengeluaran entity, long rowId) {
        entity.setIdTblPengeluaran(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(TblPengeluaran entity) {
        if(entity != null) {
            return entity.getIdTblPengeluaran();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(TblPengeluaran entity) {
        return entity.getIdTblPengeluaran() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}