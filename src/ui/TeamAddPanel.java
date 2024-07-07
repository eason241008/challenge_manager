package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.*;

import control.TeamManager;
import model.BeanTeam;
import model.BeanTeamMember;
import javax.swing.SwingConstants;

public class TeamAddPanel extends JPanel {
    private JTextField teamId;
    private JTextField competitionId;
    private JTextField teamName;
    private JTextField teamNameEng;
    private JTextField createTime;
    private JTextField remark;
    private JTextField memberCounts;
    private JTextField leaderId;
    private JTextField memberIds;
    private TeamManager teamManager;

    public TeamAddPanel() {
        teamManager = new TeamManager();
        setLayout(new BorderLayout(10, 10));

        Border padding = BorderFactory.createEmptyBorder(30, 50, 30, 50);
        setBorder(padding);
        JPanel inputPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        teamId = createTextField();
        competitionId = createTextField();
        teamName = createTextField();
        teamNameEng = createTextField();
        createTime = createTextField();
        remark = createTextField();
        memberCounts = createTextField();
        leaderId = createTextField();
        memberIds = createTextField();

        addTextFieldWithLabel(inputPanel, "队伍ID:", teamId);
        addTextFieldWithLabel(inputPanel, "竞赛ID:", competitionId);
        addTextFieldWithLabel(inputPanel, "队伍名称:", teamName);
        addTextFieldWithLabel(inputPanel, "队伍英文名称:", teamNameEng);
        addTextFieldWithLabel(inputPanel, "创建时间:", createTime);
        addTextFieldWithLabel(inputPanel, "备注:", remark);
        addTextFieldWithLabel(inputPanel, "成员数量:", memberCounts);
        addTextFieldWithLabel(inputPanel, "队长学号:", leaderId);
        addTextFieldWithLabel(inputPanel, "队员学号 (用逗号分隔):", memberIds);

        add(inputPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton("创建队伍", e -> createTeam(), buttonPanel);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("SansSerif", Font.PLAIN, 11));
        textField.setBorder(new EmptyBorder(1, 10, 1, 10));
        return textField;
    }

    private void addTextFieldWithLabel(JPanel panel, String labelText, JTextField textField) {
        JLabel label = new JLabel(labelText);
        label.setBorder(new EmptyBorder(1, 1, 1, 1));
        panel.add(label);
        panel.add(textField);
    }

    private void addButton(String buttonText, ActionListener action, JPanel panel) {
        JButton button = new JButton(buttonText);
        button.addActionListener(action);
        panel.add(button);
    }

    private void createTeam() {
        if (teamId.getText().isEmpty() ||
            competitionId.getText().isEmpty() ||
            teamName.getText().isEmpty() ||
            teamNameEng.getText().isEmpty() ||
            createTime.getText().isEmpty() ||
            leaderId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "所有字段必须填写完整才能创建队伍！");
            return;
        }

        int leaderIdValue = Integer.parseInt(leaderId.getText());
        if (!teamManager.studentExists(leaderIdValue)) {
            JOptionPane.showMessageDialog(this, "队长学号不存在，请检查输入！");
            return;
        }

        String[] memberIdArray = memberIds.getText().split(",");
        if (memberIdArray.length > 4) { // 4个成员加上1个队长，总共最多5人
            JOptionPane.showMessageDialog(this, "一个队伍最多只能有5名成员！");
            return;
        }

        for (String memberId : memberIdArray) {
            int memberIdValue = Integer.parseInt(memberId.trim());
            if (!teamManager.studentExists(memberIdValue)) {
                JOptionPane.showMessageDialog(this, "队员学号 " + memberIdValue + " 不存在，请检查输入！");
                return;
            }
        }

        BeanTeam team = new BeanTeam();
        team.setTeamId(Integer.parseInt(teamId.getText()));
        team.setCompetitionId(Integer.parseInt(competitionId.getText()));
        team.setTeamName(teamName.getText());
        team.setTeamNameEng(teamNameEng.getText());
        team.setCreateTime(createTime.getText());
        team.setRemark(remark.getText());
        team.setMemberCounts(memberIdArray.length + 1); // 加上队长

        boolean success = teamManager.addTeam(team);
        if (success) {
            // 添加队长
            BeanTeamMember leader = new BeanTeamMember();
            leader.setStudentId(leaderIdValue);
            leader.setTeamId(team.getTeamId());
            leader.setLeader(true);
            teamManager.addTeamMember(leader);

            // 添加队员
            for (String memberId : memberIdArray) {
                BeanTeamMember teamMember = new BeanTeamMember();
                teamMember.setStudentId(Integer.parseInt(memberId.trim()));
                teamMember.setTeamId(team.getTeamId());
                teamMember.setLeader(false);
                teamManager.addTeamMember(teamMember);
            }
            JOptionPane.showMessageDialog(this, "队伍创建成功!");
        } else {
            JOptionPane.showMessageDialog(this, "队伍创建失败!");
        }
    }
}
