/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ClassExplorerForm.java
 *
 * Created on 15-jan-2012, 18:01:20
 */
package net.aurora.sorcery.ui.frame;

import java.util.ArrayList;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.aurora.sorcery.Sorcery;
import net.aurora.sorcery.bytecode.viewer.ClassFilter;
import net.aurora.sorcery.ui.panel.ClassViewPanel;
import serp.bytecode.BCClass;

/**
 *
 * @author Devel
 */
public class ClassWizardForm extends javax.swing.JInternalFrame {
    
    private long lastClickTime = 0;
    private int lastClickRow = 0;
    private BCClass[] classes;
    private DefaultTableModel myClassTableModel = new DefaultTableModel() {
        
        private final String[] columns = {"Class name", "Superclass", "Interfaces", "A", "I", "E"};
        
        @Override
        public int getRowCount() {
            return classes != null ? classes.length : 0;
        }
        
        @Override
        public int getColumnCount() {
            return columns.length;
        }
        
        @Override
        public String getColumnName(int columnIndex) {
            return columns[columnIndex];
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            BCClass selectedClass = classes[rowIndex];
            switch (columnIndex) {
                case 0:
                    return selectedClass.getName();
                
                case 1:
                    return selectedClass.getSuperclassName();
                
                case 2:
                    String interfaces = "";
                    String[] interfaceNames = selectedClass.getInterfaceNames();
                    for (int i = 0; i < selectedClass.getInterfaceNames().length; i++) {
                        interfaces += interfaceNames[i] + (i != (interfaceNames.length - 1) ? ", " : "");
                    }
                    return interfaces;
                
                case 3:
                    return String.valueOf(selectedClass.isAbstract());
                
                case 4:
                    return String.valueOf(selectedClass.isInterface());
                
                case 5:
                    return String.valueOf(selectedClass.isEnum());
            }
            return null;
        }
    };
    private ClassFilter uiClassFilter = new ClassFilter() {
        
        @Override
        public boolean accept(BCClass clazz) {
            int fieldCount = clazz.getFields().length;
            int methodCount = clazz.getMethods().length;
            
            if (fieldCountTxt.getText().length() > 0) {
                String matchType = (String) fieldCountCmb.getSelectedItem();
                if (matchType.equalsIgnoreCase("lower then") && fieldCount > Integer.parseInt(fieldCountTxt.getText())) {
                    return false;
                } else if (matchType.equalsIgnoreCase("equal to") && fieldCount != Integer.parseInt(fieldCountTxt.getText())) {
                    return false;
                } else if (matchType.equalsIgnoreCase("higher then") && fieldCount < Integer.parseInt(fieldCountTxt.getText())) {
                    return false;
                }
            }
            if (methodCountTxt.getText().length() > 0) {
                String matchType = (String) methodCountCmb.getSelectedItem();
                if (matchType.equalsIgnoreCase("lower then") && methodCount > Integer.parseInt(methodCountTxt.getText())) {
                    return false;
                } else if (matchType.equalsIgnoreCase("equal to") && methodCount != Integer.parseInt(methodCountTxt.getText())) {
                    return false;
                } else if (matchType.equalsIgnoreCase("higher then") && methodCount < Integer.parseInt(methodCountTxt.getText())) {
                    return false;
                }
            }
            
             System.out.println("IM A SURVIVOR "+  clazz.getName());           
            return ((abstractChkBox.isSelected() && clazz.isAbstract())
                    || (interfaceChkBox.isSelected() && clazz.isInterface())
                    || (enumChkBox.isSelected() && clazz.isEnum()) || (!enumChkBox.isSelected() && !abstractChkBox.isSelected() && !interfaceChkBox.isSelected()))
                    && ((classNameTxt.getText().length() > 0 ? clazz.getName().toLowerCase().contains(classNameTxt.getText().toLowerCase()) : true)
                    && (superClassNameTxt.getText().length() > 0 && clazz.getSuperclassName() != null ? clazz.getSuperclassName().toLowerCase().contains(superClassNameTxt.getText().toLowerCase()) : true));
            
        }
    };

    /** Creates new form ClassExplorerForm */
    public ClassWizardForm() {
        initComponents();
        this.classes = Sorcery.getSorcery().getClassContainer().getClasses();
        this.classTable.setModel(myClassTableModel);
        
        setVisible(true);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchPanel = new javax.swing.JPanel();
        superClassNameLbl = new javax.swing.JLabel();
        methodCountLbl = new javax.swing.JLabel();
        classNameTxt = new javax.swing.JTextField();
        classNameLbl = new javax.swing.JLabel();
        superClassNameTxt = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();
        interfaceNamesTxt = new javax.swing.JTextField();
        fieldCountTxt = new javax.swing.JTextField();
        methodCountTxt = new javax.swing.JTextField();
        fieldCountCmb = new javax.swing.JComboBox();
        methodCountCmb = new javax.swing.JComboBox();
        fieldCountLbl = new javax.swing.JLabel();
        interfaceNamesLbl = new javax.swing.JLabel();
        abstractChkBox = new javax.swing.JCheckBox();
        enumChkBox = new javax.swing.JCheckBox();
        interfaceChkBox = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        classTable = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Class Wizard");

        superClassNameLbl.setText("Superclass name:");

        methodCountLbl.setText("Method count:");

        classNameLbl.setText("Class name:");

        searchBtn.setText("Search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });

        fieldCountCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Higher then", "Lower then", "Equal to" }));

        methodCountCmb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Higher then", "Lower then", "Equal to" }));

        fieldCountLbl.setText("Field count:");

        interfaceNamesLbl.setText("Interface names:");

        abstractChkBox.setSelected(true);
        abstractChkBox.setText("Abstract");

        enumChkBox.setSelected(true);
        enumChkBox.setText("Enum");

        interfaceChkBox.setSelected(true);
        interfaceChkBox.setText("Interface");

        javax.swing.GroupLayout searchPanelLayout = new javax.swing.GroupLayout(searchPanel);
        searchPanel.setLayout(searchPanelLayout);
        searchPanelLayout.setHorizontalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                        .addComponent(classNameLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(classNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, searchPanelLayout.createSequentialGroup()
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(fieldCountLbl)
                            .addComponent(methodCountLbl))
                        .addGap(22, 22, 22)
                        .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(methodCountTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                            .addComponent(fieldCountTxt))))
                .addGap(18, 18, 18)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(methodCountCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(fieldCountCmb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(superClassNameLbl))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(superClassNameTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(interfaceNamesLbl)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                    .addGroup(searchPanelLayout.createSequentialGroup()
                        .addComponent(abstractChkBox)
                        .addGap(18, 18, 18)
                        .addComponent(enumChkBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addComponent(interfaceChkBox))
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(interfaceNamesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBtn))
                .addContainerGap())
        );
        searchPanelLayout.setVerticalGroup(
            searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(classNameLbl)
                    .addComponent(superClassNameLbl)
                    .addComponent(classNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(superClassNameTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(interfaceNamesLbl)
                    .addComponent(interfaceNamesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fieldCountLbl)
                    .addComponent(fieldCountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fieldCountCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abstractChkBox)
                    .addComponent(enumChkBox))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(searchPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(methodCountLbl)
                    .addComponent(methodCountTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(methodCountCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(interfaceChkBox)
                    .addComponent(searchBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        classTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        classTable.setFocusCycleRoot(true);
        classTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                classTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(classTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(searchPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 784, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(searchPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void classTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_classTableMouseClicked
    if ((System.currentTimeMillis() - lastClickTime) < 800 && lastClickRow == classTable.getSelectedRow()) {
        getParent().add(new ClassViewerForm(this.classes[this.classTable.getSelectedRow()]));
    }
    lastClickTime = System.currentTimeMillis();
    lastClickRow = classTable.getSelectedRow();
}//GEN-LAST:event_classTableMouseClicked
    
private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
    ArrayList<BCClass> filteredClasses = new ArrayList<BCClass>();
    BCClass[] classes = Sorcery.getSorcery().getClassContainer().getClasses();
    
    for (BCClass clazz : classes) {
        if (uiClassFilter.accept(clazz)) {
            filteredClasses.add(clazz);
        }
    }
    this.classes = filteredClasses.toArray(new BCClass[filteredClasses.size()]);
    ((DefaultTableModel)this.classTable.getModel()).fireTableDataChanged();
}//GEN-LAST:event_searchBtnActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox abstractChkBox;
    private javax.swing.JLabel classNameLbl;
    private javax.swing.JTextField classNameTxt;
    private javax.swing.JTable classTable;
    private javax.swing.JCheckBox enumChkBox;
    private javax.swing.JComboBox fieldCountCmb;
    private javax.swing.JLabel fieldCountLbl;
    private javax.swing.JTextField fieldCountTxt;
    private javax.swing.JCheckBox interfaceChkBox;
    private javax.swing.JLabel interfaceNamesLbl;
    private javax.swing.JTextField interfaceNamesTxt;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox methodCountCmb;
    private javax.swing.JLabel methodCountLbl;
    private javax.swing.JTextField methodCountTxt;
    private javax.swing.JButton searchBtn;
    private javax.swing.JPanel searchPanel;
    private javax.swing.JLabel superClassNameLbl;
    private javax.swing.JTextField superClassNameTxt;
    // End of variables declaration//GEN-END:variables
}
