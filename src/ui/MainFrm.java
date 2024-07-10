package ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import control.TeamManager;
import model.*;
import util.*;

public class MainFrm extends JFrame {

    private JPanel contentPane;
    private TeacherPanel teacherPanel;
    private StudentPanel studentPanel;
    private ChallengePanel challengePanel;
    private Challenge_ArrangementPanel challenge_ArrangementPanel;
    private showHelp showHelp;
    private TeamAddPanel teamPanel;
    private TeamDeletePanel teamDeletePanel;
    private TeamChangeInfoPanel teamChangeInfoPanel;
    private JTable table;
    private DefaultTableModel tableModel;
    private AwardMajorPanel awardMajorPanel;
    private TeamManager teamManager;
    private AwardClassPanel awardClassPanel;
    private AwardsearchPanel awardsearchPanel;
    private AwardstuPanel awardstuPanel;
    private AwardstuCalPanel awardstuCalPanel;
    private AwardstuLoadPanel awardstuLoadPanel;
    private ResetstuPanel resetstuPanel;
    private UserInfoPanel userInfoPanel;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainFrm frame = new MainFrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public MainFrm() {
        setTitle("�����񽱹���ϵͳ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        teamDeletePanel = new TeamDeletePanel();
        studentPanel = new StudentPanel();
        teacherPanel = new TeacherPanel();
        challengePanel = new ChallengePanel();
        teamPanel = new TeamAddPanel();
        teamChangeInfoPanel = new TeamChangeInfoPanel();
        challenge_ArrangementPanel = new Challenge_ArrangementPanel();
        awardMajorPanel = new AwardMajorPanel();
        awardClassPanel = new AwardClassPanel();
        awardsearchPanel = new AwardsearchPanel();
        awardstuPanel = new AwardstuPanel();
        awardstuCalPanel=new AwardstuCalPanel();
        awardstuLoadPanel=new AwardstuLoadPanel();
        userInfoPanel =new UserInfoPanel();
        resetstuPanel =new ResetstuPanel();
        JMenuItem mntmNewMenuItem = new JMenuItem("ѧ������");
        mntmNewMenuItem.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                studentactionPerformed(e);
            }
        });

        JMenuItem mntmNewMenuItem_8 = new JMenuItem("\u8FD4\u56DE");
        mntmNewMenuItem_8.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showMainPanel();
            }
        });
        menuBar.add(mntmNewMenuItem_8);
        menuBar.add(mntmNewMenuItem);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("��ʦ����");
        mntmNewMenuItem_5.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teacheractionPerformed(e);
            }
        });
        menuBar.add(mntmNewMenuItem_5);

        JMenuItem mntmNewMenuItem_6 = new JMenuItem("��������");
        mntmNewMenuItem_6.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                challengeactionPerformed(e);
            }
        });
        menuBar.add(mntmNewMenuItem_6);

        JMenuItem mntmNewMenuItem_7 = new JMenuItem("���¹���");
        mntmNewMenuItem_7.setHorizontalAlignment(SwingConstants.CENTER);
        mntmNewMenuItem_7.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                competitionactionPerformed(e);
            }
        });
        menuBar.add(mntmNewMenuItem_7);
        
        JMenuItem mntmNewMenuItem_1 = new JMenuItem("\u7ADE\u8D5B\u4FE1\u606F\u5F55\u5165");
        mntmNewMenuItem_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		showstuloadAwardhangePanel();
        	}
        });
        mntmNewMenuItem_1.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mntmNewMenuItem_1);

        JMenu mnNewMenu_6 = new JMenu("�������");
        mnNewMenu_6.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnNewMenu_6);

        JMenuItem mntmNewMenuItem_17 = new JMenuItem("��Ӷ���");
        mntmNewMenuItem_17.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamaddactrionPerformed(e);
            }
        });
        mnNewMenu_6.add(mntmNewMenuItem_17);

        JMenuItem mntmNewMenuItem_19 = new JMenuItem("ɾ������");
        mntmNewMenuItem_19.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamdeteactionPerformed(e);
            }
        });
        mnNewMenu_6.add(mntmNewMenuItem_19);

        JMenuItem mntmNewMenuItem_20 = new JMenuItem("������Ϣ�޸�");
        mntmNewMenuItem_20.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                teamchangeinfoactionPerformed(e);
            }
        });
        mnNewMenu_6.add(mntmNewMenuItem_20);

        JMenu mnNewMenu = new JMenu("������Ϣ��ѯ");
        mnNewMenu.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem_12 = new JMenuItem("ѧ���񽱲�ѯ");
        mntmNewMenuItem_12.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                awardstuactionPerformed(e);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_12);

        JMenuItem mntmNewMenuItem_21 = new JMenuItem("ѧ����ͳ��");
        mntmNewMenuItem_21.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                awardstuCalactionPerformed(e);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_21);

        JMenuItem mntmNewMenuItem_24 = new JMenuItem("���»�ͳ��");
        mntmNewMenuItem_24.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showchallengeawardactionPerformed(e);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_24);

        JMenuItem mntmNewMenuItem_22 = new JMenuItem("�༶��ͳ��");
        mntmNewMenuItem_22.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                showClassAwardhangePanel(e);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_22);

        JMenuItem mntmNewMenuItem_23 = new JMenuItem("רҵ��ͳ��");
        mntmNewMenuItem_23.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                awardmajoractionPerformed(e);
            }
        });
        mnNewMenu.add(mntmNewMenuItem_23);

        JMenu mnNewMenu_2 = new JMenu("�û�����");
        mnNewMenu_2.setHorizontalAlignment(SwingConstants.CENTER);
        menuBar.add(mnNewMenu_2);

        JMenuItem mntmNewMenuItem_3 = new JMenuItem("����ѧ������");
        mntmNewMenuItem_3.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	resetstuactionPerformed(e);
        	}
        });
        mnNewMenu_2.add(mntmNewMenuItem_3);

        JMenuItem mntmNewMenuItem_4 = new JMenuItem("������Ϣ����");
        mntmNewMenuItem_4.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		userinfoactionPerformed(e);
        	}
        });
        mnNewMenu_2.add(mntmNewMenuItem_4);

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        showMainPanel();

        teamManager = new TeamManager();
    }

	protected void userinfoactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		showuserinfoPanel();
	}

	protected void resetstuactionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		showstuResetPanel();
	}

	protected void awardstuCalactionPerformed(ActionEvent e) {
        showstuCalAwardhangePanel();
    }

    protected void awardstuactionPerformed(ActionEvent e) {
        showstuAwardhangePanel();
    }

    protected void showchallengeawardactionPerformed(ActionEvent e) {
        showchallengeAwardhangePanel();
    }

    protected void showClassAwardhangePanel(ActionEvent e) {
        showClassAwardhangePanel();
    }

    protected void awardmajoractionPerformed(ActionEvent e) {
        showMajorAwardhangePanel();
    }

    protected void teamchangeinfoactionPerformed(ActionEvent e) {
        showTeamchangePanel();
    }

    protected void teamdeteactionPerformed(ActionEvent e) {
        showTeamdeletePanel();
    }

    protected void teamaddactrionPerformed(ActionEvent e) {
        showTeamaddPanel();
    }
    private void showuserinfoPanel() {
        contentPane.removeAll();
        contentPane.add(userInfoPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuResetPanel() {
        contentPane.removeAll();
        contentPane.add(resetstuPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuloadAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardstuLoadPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuCalAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardstuCalPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeamchangePanel() {
        contentPane.removeAll();
        contentPane.add(teamChangeInfoPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showstuAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardstuPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showchallengeAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardsearchPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showMajorAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardMajorPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeamdeletePanel() {
        contentPane.removeAll();
        contentPane.add(teamDeletePanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeamaddPanel() {
        contentPane.removeAll();
        contentPane.add(teamPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    protected void competitionactionPerformed(ActionEvent e) {
        showCompetitionPanel();
    }

    protected void challengeactionPerformed(ActionEvent e) {
        showChallengePanel();
    }

    protected void studentactionPerformed(ActionEvent e) {
        showStudentPanel();
    }

    protected void teacheractionPerformed(ActionEvent e) {
        showTeacherPanel();
    }

    private void showChallengePanel() {
        contentPane.removeAll();
        contentPane.add(challengePanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showClassAwardhangePanel() {
        contentPane.removeAll();
        contentPane.add(awardClassPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showCompetitionPanel() {
        contentPane.removeAll();
        contentPane.add(challenge_ArrangementPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showTeacherPanel() {
        contentPane.removeAll();
        contentPane.add(teacherPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showStudentPanel() {
        contentPane.removeAll();
        contentPane.add(studentPanel, BorderLayout.CENTER);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void searchChallengeActionPerformed(ActionEvent e) {
        showSearchPanel("Challenge ID", "����������ID:", "SELECT * FROM ChallengeInfo WHERE challenge_id = ?", "SELECT * FROM ChallengeInfo", new String[]{"Challenge ID", "Challenge Name", "Host", "Organizer", "Whether Zudui", "Description"});
    }

    private void searchTeacherActionPerformed(ActionEvent e) {
        showSearchPanel("Teacher ID", "�������ʦID:", "SELECT * FROM TeacherInfo WHERE teacher_id = ?", "SELECT * FROM TeacherInfo", new String[]{"Teacher ID", "Teacher Name", "Department", "Mobile Phone", "Email"});
    }

    private void searchCompetitionActionPerformed(ActionEvent e) {
        showSearchPanel("Competition ID", "�����뾺��ID:", "SELECT * FROM Challenge_ArrangementInfo WHERE competition_id = ?", "SELECT * FROM Challenge_ArrangementInfo", new String[]{"Competition ID", "Competition Name", "Held Time", "Held Address"});
    }

    private void searchStudentActionPerformed(ActionEvent e) {
        showSearchPanel("Student ID", "������ѧ��ID:", "SELECT * FROM StudentInfo WHERE student_id = ?", "SELECT * FROM StudentInfo", new String[]{"Student ID", "Student Name", "Enrollment Year", "Class", "Grade", "Major", "Mobile Phone", "Email", "QQ"});
    }

    private void searchTeamActionPerformed(ActionEvent e) {
        showSearchPanel("Team ID", "���������ID:", "SELECT * FROM TeamInfo WHERE team_id = ?", "SELECT * FROM TeamInfo", new String[]{"Team ID", "Competition ID", "Team Name", "Team Name Eng", "Create Time", "Remark", "Member Counts"});
    }

    private void showSearchPanel(String label, String dialogMessage, String queryById, String queryAll, String[] columnNames) {
        contentPane.removeAll();

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        contentPane.add(searchPanel, BorderLayout.NORTH);

        JPanel inputPanel = new JPanel();
        JLabel idLabel = new JLabel(label + ":");
        JTextField idField = new JTextField(10);
        JButton searchButton = new JButton("����");
        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(searchButton);
        searchPanel.add(inputPanel, BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[][]{}, columnNames);
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.setRowCount(0); // Clear the table

                try (Connection con = DBUtil.getConnection();
                     PreparedStatement pstmt = con.prepareStatement(queryById)) {
                    pstmt.setString(1, id);
                    try (ResultSet rs = pstmt.executeQuery()) {
                        while (rs.next()) {
                            Object[] row = new Object[columnNames.length];
                            for (int i = 0; i < columnNames.length; i++) {
                                row[i] = rs.getObject(i + 1);
                            }
                            model.addRow(row);
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        showAllData(queryAll, columnNames);
        contentPane.revalidate();
        contentPane.repaint();
    }

    private void showAllData(String query, String[] columnNames) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the table

        try (Connection con = DBUtil.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Object[] row = new Object[columnNames.length];
                for (int i = 0; i < columnNames.length; i++) {
                    row[i] = rs.getObject(i + 1);
                }
                model.addRow(row);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void showMainPanel() {
        contentPane.removeAll();

        JPanel leftPanel = new JPanel();
        contentPane.add(leftPanel, BorderLayout.WEST);
        leftPanel.setLayout(new BorderLayout(0, 0));

        JPanel buttonPanel = new JPanel();
        leftPanel.add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 1, 0, 10));

        JButton studentSearchButton = new JButton("ѧ������");
        studentSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(studentSearchButton);

        JButton teacherSearchButton = new JButton("��ʦ����");
        teacherSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(teacherSearchButton);

        JButton competitionSearchButton = new JButton("��������");
        competitionSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(competitionSearchButton);

        JButton challengeSearchButton = new JButton("���²���");
        challengeSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(challengeSearchButton);

        JButton teamSearchButton = new JButton("�������");
        teamSearchButton.setFont(new Font("SansSerif", Font.BOLD, 11));
        buttonPanel.add(teamSearchButton);

        JScrollPane scrollPane = new JScrollPane();
        contentPane.add(scrollPane, BorderLayout.CENTER);

        tableModel = new DefaultTableModel(new Object[][]{}, new String[]{});
        table = new JTable(tableModel);
        scrollPane.setViewportView(table);

        this.setLocationRelativeTo(null);

        studentSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchStudentActionPerformed(e);
            }
        });

        teacherSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTeacherActionPerformed(e);
            }
        });

        competitionSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchCompetitionActionPerformed(e);
            }
        });

        challengeSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchChallengeActionPerformed(e);
            }
        });

        teamSearchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                searchTeamActionPerformed(e);
            }
        });

        contentPane.revalidate();
        contentPane.repaint();
    }
}
