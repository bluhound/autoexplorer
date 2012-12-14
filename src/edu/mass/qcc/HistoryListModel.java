/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mass.qcc;
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;


/**
 *
 * @author Ian
 */


class HistoryListModel extends AbstractListModel implements ComboBoxModel {
  //Populate the History Combobox
        
        java.util.List<String> historyArray;
        String selection = "";
         
    HistoryListModel(autoexplorer _ax){
    
    historyArray = _ax.browser.getAllHistory();
           
  
    }
    @Override
  public Object getElementAt(int index) {
    return historyArray.get(index);
  }

    @Override
  public int getSize() {
    return historyArray.size();
  }

    @Override
  public void setSelectedItem(Object anItem) {
    selection = (String) anItem; 
  } 

    @Override
  public Object getSelectedItem() {
    return selection; 
  }
}
