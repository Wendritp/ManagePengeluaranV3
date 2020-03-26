package com.app.ManagePengeluaran.utils.database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.app.ManagePengeluaran.utils.database.TblPengeluaran;

import com.app.ManagePengeluaran.utils.database.TblPengeluaranDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig tblPengeluaranDaoConfig;

    private final TblPengeluaranDao tblPengeluaranDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        tblPengeluaranDaoConfig = daoConfigMap.get(TblPengeluaranDao.class).clone();
        tblPengeluaranDaoConfig.initIdentityScope(type);

        tblPengeluaranDao = new TblPengeluaranDao(tblPengeluaranDaoConfig, this);

        registerDao(TblPengeluaran.class, tblPengeluaranDao);
    }
    
    public void clear() {
        tblPengeluaranDaoConfig.clearIdentityScope();
    }

    public TblPengeluaranDao getTblPengeluaranDao() {
        return tblPengeluaranDao;
    }

}