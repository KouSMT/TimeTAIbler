/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package timetaiblerai;

import oracle.jdbc.dcn.DatabaseChangeEvent;
import oracle.jdbc.dcn.DatabaseChangeListener;

/**
 *
 * @author Dennis
 */
public class AI implements DatabaseChangeListener {
    
    

    Main demo;

    AI(Main dem) {
        demo = dem;
    }

    @Override
    public void onDatabaseChangeNotification(DatabaseChangeEvent dce) {
        Thread t = Thread.currentThread();
        //handle class changes first

        if (dce.getTableChangeDescription()[0].getTableName().equals("CLASS")) {
            String classId = dce.getTableChangeDescription()[0].getRowChangeDescription()[0].getRowid().toString();

        } else if (dce.getTableChangeDescription()[0].getTableName().equals("PREFERENCE")) {

        }
        synchronized (demo) {
            demo.notify();
        }
    }

}
