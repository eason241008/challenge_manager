package ui;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import control.TeamManager;
import model.BeanTeam;
import model.BeanTeamMember;

public class TeamChangeInfoPanel extends JPanel {
    private JTextField teamIdField;
    private JTextField teamNameField;
    private JTextField teamNameEngField;
    private JTextField leaderIdField;
    private JTextField memberIdsField;
    private TeamManager teamManager;

    public TeamChangeInfoPanel() {
        teamManager = new TeamManager();

        Font labelFont = new Font("SansSerif", Font.BOLD, 11);
        Font textFieldFont = new Font("SansSerif", Font.BOLD, 11);
        Font buttonFont = new Font("SansSerif", Font.BOLD, 11);

        JLabel lblTeamId = new JLabel("队伍ID:");
        lblTeamId.setFont(labelFont);

        teamIdField = new JTextField();
        teamIdField.setColumns(10);
        teamIdField.setFont(textFieldFont);

        JButton searchButton = new JButton("查找队伍");
        searchButton.setFont(buttonFont);
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTeamActionPerformed(e);
            }
        });

        JLabel lblTeamName = new JLabel("队伍名称:");
        lblTeamName.setFont(labelFont);

        teamNameField = new JTextField();
        teamNameField.setColumns(10);
        teamNameField.setFont(textFieldFont);

        JLabel lblTeamNameEng = new JLabel("队伍英文名称:");
        lblTeamNameEng.setFont(labelFont);

        teamNameEngField = new JTextField();
        teamNameEngField.setColumns(10);
        teamNameEngField.setFont(textFieldFont);

        JLabel lblLeaderId = new JLabel("队长学号:");
        lblLeaderId.setFont(labelFont);

        leaderIdField = new JTextField();
        leaderIdField.setColumns(10);
        leaderIdField.setFont(textFieldFont);

        JLabel lblMemberIds = new JLabel("队员学号 (用逗号分隔):");
        lblMemberIds.setFont(labelFont);

        memberIdsField = new JTextField();
        memberIdsField.setColumns(10);
        memberIdsField.setFont(textFieldFont);

        JButton updateButton = new JButton("更新队伍信息");
        updateButton.setFont(buttonFont);
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateTeamActionPerformed(e);
            }
        });

        JButton modifyMembersButton = new JButton("修改成员");
        modifyMembersButton.setFont(buttonFont);
        modifyMembersButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modifyMembersActionPerformed(e);
            }
        });

        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblTeamId)
                        .addComponent(lblTeamName)
                        .addComponent(lblTeamNameEng)
                        .addComponent(lblLeaderId)
                        .addComponent(lblMemberIds))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(teamIdField, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addComponent(teamNameField, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addComponent(teamNameEngField, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addComponent(leaderIdField, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                        .addComponent(memberIdsField, GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(modifyMembersButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE))
                    .addGap(30))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(30)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTeamId)
                        .addComponent(teamIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTeamName)
                        .addComponent(teamNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblTeamNameEng)
                        .addComponent(teamNameEngField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(searchButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblLeaderId)
                        .addComponent(leaderIdField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(updateButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addGap(18)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblMemberIds)
                        .addComponent(memberIdsField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(modifyMembersButton, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(34, Short.MAX_VALUE))
        );
        setLayout(groupLayout);
    }

    private void searchTeamActionPerformed(ActionEvent e) {
        String teamIdText = teamIdField.getText();
        if (teamIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入队伍ID！");
            return;
        }

        int teamId = Integer.parseInt(teamIdText);
        BeanTeam team = teamManager.getTeam(teamId);

        if (team != null) {
            loadTeamData(teamId);
        } else {
            JOptionPane.showMessageDialog(this, "未找到队伍！");
        }
    }

    private void updateTeamActionPerformed(ActionEvent e) {
        String teamIdText = teamIdField.getText();
        if (teamIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入队伍ID！");
            return;
        }

        int teamId = Integer.parseInt(teamIdText);
        String teamName = teamNameField.getText();
        String teamNameEng = teamNameEngField.getText();
        String leaderIdText = leaderIdField.getText();

        if (!teamManager.studentExists(Integer.parseInt(leaderIdText))) {
            JOptionPane.showMessageDialog(this, "队长学号不存在，请检查输入！");
            return;
        }

        BeanTeam team = new BeanTeam();
        team.setTeamId(teamId);
        team.setTeamName(teamName);
        team.setTeamNameEng(teamNameEng);
        boolean success = teamManager.updateTeam(team);

        if (success) {
            teamManager.updateTeamLeader(teamId, Integer.parseInt(leaderIdText));
            JOptionPane.showMessageDialog(this, "队伍信息更新成功！");
        } else {
            JOptionPane.showMessageDialog(this, "队伍信息更新失败！");
        }
    }

    private void modifyMembersActionPerformed(ActionEvent e) {
        String teamIdText = teamIdField.getText();
        if (teamIdText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "请输入队伍ID！");
            return;
        }

        int teamId = Integer.parseInt(teamIdText);
        String leaderIdText = leaderIdField.getText();
        String[] memberIdsArray = memberIdsField.getText().split(",");

        // Ensure the total number of members does not exceed 5
        if (memberIdsArray.length > 4) {
            JOptionPane.showMessageDialog(this, "一个队伍最多只能有5名成员！");
            return;
        }

        if (!teamManager.studentExists(Integer.parseInt(leaderIdText))) {
            JOptionPane.showMessageDialog(this, "队长学号不存在，请检查输入！");
            return;
        }

        for (String memberIdText : memberIdsArray) {
            int memberId = Integer.parseInt(memberIdText.trim());
            if (!teamManager.studentExists(memberId)) {
                JOptionPane.showMessageDialog(this, "队员学号 " + memberId + " 不存在，请检查输入！");
                return;
            }
        }

        // Clear existing members
        teamManager.deleteTeamMemberByTeamId(teamId);

        // Add leader as a member
        teamManager.addTeamMember(new BeanTeamMember(Integer.parseInt(leaderIdText), teamId, true));

        // Add new members
        for (String memberIdText : memberIdsArray) {
            int memberId = Integer.parseInt(memberIdText.trim());
            boolean success = teamManager.addTeamMember(new BeanTeamMember(memberId, teamId, false));
            if (!success) {
                JOptionPane.showMessageDialog(this, "队员添加失败！");
                return;
            }
        }

        JOptionPane.showMessageDialog(this, "成员更新成功！");
    }

    private String getTeamMembersString(int teamId) {
        List<BeanTeamMember> members = teamManager.getTeamMembers(teamId);
        StringBuilder sb = new StringBuilder();
        for (BeanTeamMember member : members) {
            if (!member.isLeader()) {
                sb.append(member.getStudentId()).append(",");
            }
        }
        if (sb.length() > 0) {
            sb.setLength(sb.length() - 1); 
        }
        return sb.toString();
    }

    private int getTeamLeader(int teamId) {
        List<BeanTeamMember> members = teamManager.getTeamMembers(teamId);
        for (BeanTeamMember member : members) {
            if (member.isLeader()) {
                return member.getStudentId();
            }
        }
        return -1;
    }

    public void loadTeamData(int teamId) {
        BeanTeam team = teamManager.getTeam(teamId);
        if (team != null) {
            teamIdField.setText(String.valueOf(team.getTeamId()));
            teamNameField.setText(team.getTeamName());
            teamNameEngField.setText(team.getTeamNameEng());
            leaderIdField.setText("" + getTeamLeader(teamId));
            memberIdsField.setText(getTeamMembersString(teamId));
        } else {
            JOptionPane.showMessageDialog(this, "未找到队伍！");
        }
    }
}
