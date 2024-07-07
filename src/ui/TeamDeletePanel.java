package ui;

import javax.swing.JPanel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import control.TeamManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeamDeletePanel extends JPanel {
    private JTextField textField;

    /**
     * Create the panel.
     */
    public TeamDeletePanel() {
        JLabel lblNewLabel = new JLabel("����ID:");
        lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 22));

        textField = new JTextField();
        textField.setColumns(10);
        textField.setFont(new Font("SansSerif", Font.BOLD, 22));

        JButton button = new JButton("ɾ������");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteTeamactionPerformed(e);
            }
        });
        button.setFont(new Font("SansSerif", Font.BOLD, 22));

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.TRAILING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(74)
                    .addComponent(lblNewLabel)
                    .addGap(18)
                    .addComponent(textField, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(86, Short.MAX_VALUE))
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap(183, Short.MAX_VALUE)
                    .addComponent(button, GroupLayout.PREFERRED_SIZE, 200, GroupLayout.PREFERRED_SIZE)
                    .addGap(67))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.CENTER)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(100)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(47)
                    .addComponent(button, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
                    .addGap(103))
        );
        setLayout(groupLayout);
    }

    private void deleteTeamactionPerformed(ActionEvent e) {
        String teamIdText = textField.getText();
        if (teamIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "���������ID��");
            return;
        }

        int teamId = Integer.parseInt(teamIdText);

        TeamManager teamManager = new TeamManager();
        if (!teamManager.teamExists(teamId)) {
            JOptionPane.showMessageDialog(this, "���鲻���ڣ�");
            return;
        }

        if (teamManager.teamHasAwards(teamId)) {
            JOptionPane.showMessageDialog(this, "�ö����л���Ϣ������ɾ����");
            return;
        }

        boolean success1 = teamManager.deleteTeamMember(teamId);
        boolean success2 = teamManager.deleteTeam(teamId);
        if (success1) {
            JOptionPane.showMessageDialog(this, "����ɾ���ɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "����ɾ��ʧ��!");
        }
        if (success2) {
            JOptionPane.showMessageDialog(this, "��Աɾ���ɹ�!");
        } else {
            JOptionPane.showMessageDialog(this, "��Աɾ��ʧ��!");
        }
    }
}
