package com.app.ManagePengeluaran.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.ManagePengeluaran.R;
import com.app.ManagePengeluaran.create.CreateActivity;
import com.app.ManagePengeluaran.edit.EditDialogFragment;
import com.app.ManagePengeluaran.utils.FunctionHelper;
import com.app.ManagePengeluaran.utils.database.DaoHandler;
import com.app.ManagePengeluaran.utils.database.DaoSession;
import com.app.ManagePengeluaran.utils.database.TblPengeluaran;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity
        implements PengeluaranAdapter.PengeluaranAdapterCallback,
        EditDialogFragment.EditDialogListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.rvNote)
    RecyclerView rvNote;
    @BindView(R.id.fabAdd)
    FloatingActionButton fabAdd;
    @BindView(R.id.tvTotal)
    TextView tvTotal;

    private DaoSession daoSession;
    private PengeluaranAdapter pengeluaranAdapter;

    private List<TblPengeluaran> tblPengeluaranList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        daoSession = DaoHandler.getInstance(this);
        setSupportActionBar(toolbar);

        /*
        Buat Read Database
         */
        tblPengeluaranList = daoSession.getTblPengeluaranDao().queryBuilder().list();
        pengeluaranAdapter = new PengeluaranAdapter(tblPengeluaranList, this);
        rvNote.setLayoutManager(new LinearLayoutManager(this));
        rvNote.setItemAnimator(new DefaultItemAnimator());
        rvNote.setAdapter(pengeluaranAdapter);
        pengeluaranAdapter.notifyDataSetChanged();

        tvTotal.setText(FunctionHelper.convertRupiah(getTotal()));

        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CreateActivity.class));
            }
        });
    }

    /*
    Buar Send data Ke Database
     */
    @Override
    public void onLongClick(int position) {
        long id = tblPengeluaranList.get(position).getIdTblPengeluaran();
        String pembelian = tblPengeluaranList.get(position).getPengeluaran();
        int nominal = tblPengeluaranList.get(position).getNominal();

        FragmentManager fm = getSupportFragmentManager();
        EditDialogFragment editDialogFragment = EditDialogFragment.newInstance(id, pembelian, nominal);
        editDialogFragment.show(fm, "dialog_edit");
    }

    /*
    Buat Delete
     */
    @Override
    public void onDelete(int position) {
        String name = tblPengeluaranList.get(position).getPengeluaran();
        showDialogDelete(position, name);
    }

    /*
    Fungsi Menghitung Total
     */
    private int getTotal(){
        int total = 0;
        for (int i = 0; i < tblPengeluaranList.size(); i++){
            int nominal = tblPengeluaranList.get(i).getNominal();
            total = total + nominal;
        }
        return total;
    }

    /*
    Memunculkan alert peringatan
     */
    private void showDialogDelete(final int position, String name){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(HomeActivity.this);
        builder1.setMessage("Yakin untuk menghapus item "+ name + " ?");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Ya",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        /*
                        Untuk Delete datanya
                         */
                        long idTbl = tblPengeluaranList.get(position).getIdTblPengeluaran();
                        daoSession.getTblPengeluaranDao().deleteByKey(idTbl);

                        tblPengeluaranList.remove(position);
                        pengeluaranAdapter.notifyItemRemoved(position);
                        pengeluaranAdapter.notifyItemRangeChanged(position, tblPengeluaranList.size());

                        tvTotal.setText(FunctionHelper.convertRupiah(getTotal()));

                        dialog.dismiss();
                    }
                });

        builder1.setNegativeButton(
                "Tidak",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    /*
    Nerima data
     */
    @Override
    public void requestUpdate(long id, String pembelian, int nominal) {
        TblPengeluaran tblPengeluaran = daoSession.getTblPengeluaranDao().load(id);
        tblPengeluaran.setPengeluaran(pembelian);
        tblPengeluaran.setNominal(nominal);
        daoSession.getTblPengeluaranDao().update(tblPengeluaran);

        pengeluaranAdapter.notifyDataSetChanged();
        tvTotal.setText(FunctionHelper.convertRupiah(getTotal()));
    }
}
