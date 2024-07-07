package util;

import javax.swing.JOptionPane;

public class showHelp {
	public static void showstuHelpMessage() {
        String message = "<html><body>" +
            "<h3>操作提醒：</h3>" +
            "<p><strong>1. 添加学生信息：</strong></p>" +
            "<ul>" +
            "<li>请确保以下字段不为空：</li>" +
            "<ul>" +
            "<li>学号</li>" +
            "<li>姓名</li>" +
            "<li>班级</li>" +
            "<li>年级</li>" +
            "<li>专业</li>" +
            "</ul>" +
            "<li>只有在所有必填字段均已填写的情况下，才能成功添加学生信息。</li>" +
            "</ul>" +
            "<p><strong>2. 删除学生信息：</strong></p>" +
            "<ul>" +
            "<li>通过学号进行删除操作。</li>" +
            "<li>请提供要删除的学生的学号。</li>" +
            "</ul>" +
            "<p><strong>3. 修改学生信息：</strong></p>" +
            "<ul>" +
            "<li>通过学号进行修改操作。</li>" +
            "<li>请提供要修改的学生的学号。</li>" +
            "<li>修改时请确保填写以下字段：</li>" +
            "<ul>" +
            "<li>姓名</li>" +
            "<li>班级</li>" +
            "<li>年级</li>" +
            "<li>专业</li>" +
            "</ul>" +
            "</ul>" +
            "<p><strong>4. 查找学生信息：</strong></p>" +
            "<ul>" +
            "<li>通过学号查找学生信息。</li>" +
            "<li>请提供要查找的学生的学号。</li>" +
            "</ul>" +
            "</body></html>";
        
        JOptionPane.showMessageDialog(null, message, "操作提醒", JOptionPane.INFORMATION_MESSAGE);
    }
	public static void showteacherHelpMessage() {
		String message = "<html><body>" +
			    "<h3>操作提醒：</h3>" +
			    "<p><strong>1. 添加教师信息：</strong></p>" +
			    "<ul>" +
			    "<li>请确保以下字段不为空：</li>" +
			    "<ul>" +
			    "<li>教师ID</li>" +
			    "<li>姓名</li>" +
			    "<li>部门</li>" +
			    "<li>手机号</li>" +
			    "<li>邮箱</li>" +
			    "</ul>" +
			    "<li>只有在所有必填字段均已填写的情况下，才能成功添加教师信息。</li>" +
			    "</ul>" +
			    "<p><strong>2. 删除教师信息：</strong></p>" +
			    "<ul>" +
			    "<li>通过教师ID进行删除操作。</li>" +
			    "<li>请提供要删除的教师的ID。</li>" +
			    "</ul>" +
			    "<p><strong>3. 修改教师信息：</strong></p>" +
			    "<ul>" +
			    "<li>通过教师ID进行修改操作。</li>" +
			    "<li>请提供要修改的教师的ID。</li>" +
			    "<li>修改时请确保填写以下字段：</li>" +
			    "<ul>" +
			    "<li>姓名</li>" +
			    "<li>部门</li>" +
			    "</ul>" +
			    "</ul>" +
			    "<p><strong>4. 查找教师信息：</strong></p>" +
			    "<ul>" +
			    "<li>通过教师ID查找教师信息。</li>" +
			    "<li>请提供要查找的教师的ID。</li>" +
			    "</ul>" +
			    "</body></html>";

        
        JOptionPane.showMessageDialog(null, message, "操作提醒", JOptionPane.INFORMATION_MESSAGE);
    }
	public static void showchallengeHelpMessage() {
		String message = "<html><body>" +
			    "<h3>操作提醒：</h3>" +
			    "<p><strong>1. 添加竞赛信息：</strong></p>" +
			    "<ul>" +
			    "<li>请确保以下字段不为空：</li>" +
			    "<ul>" +
			    "<li>竞赛ID</li>" +
			    "<li>竞赛名称</li>" +
			    "<li>主办方</li>" +
			    "<li>承办方</li>" +
			    "<li>描述</li>" +
			    "</ul>" +
			    "<li>只有在所有必填字段均已填写的情况下，才能成功添加竞赛信息。</li>" +
			    "</ul>" +
			    "<p><strong>2. 删除竞赛信息：</strong></p>" +
			    "<ul>" +
			    "<li>通过竞赛ID进行删除操作。</li>" +
			    "<li>请提供要删除的竞赛的ID。</li>" +
			    "<li>删除竞赛信息时，如果有相关赛事存在，会给出提示并禁止删除。</li>" +
			    "</ul>" +
			    "<p><strong>3. 修改竞赛信息：</strong></p>" +
			    "<ul>" +
			    "<li>通过竞赛ID进行修改操作。</li>" +
			    "<li>请提供要修改的竞赛的ID。</li>" +
			    "<li>修改时请确保填写以下字段：</li>" +
			    "<ul>" +
			    "<li>竞赛名称</li>" +
			    "<li>主办方</li>" +
			    "<li>承办方</li>" +
			    "<li>描述</li>" +
			    "</ul>" +
			    "</ul>" +
			    "<p><strong>4. 查找竞赛信息：</strong></p>" +
			    "<ul>" +
			    "<li>通过竞赛ID查找竞赛信息。</li>" +
			    "<li>请提供要查找的竞赛的ID。</li>" +
			    "</ul>" +
			    "</body></html>";


        
        JOptionPane.showMessageDialog(null, message, "操作提醒", JOptionPane.INFORMATION_MESSAGE);
    }
}
