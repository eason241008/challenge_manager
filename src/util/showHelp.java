package util;

import javax.swing.JOptionPane;

public class showHelp {
	public static void showstuHelpMessage() {
        String message = "<html><body>" +
            "<h3>�������ѣ�</h3>" +
            "<p><strong>1. ���ѧ����Ϣ��</strong></p>" +
            "<ul>" +
            "<li>��ȷ�������ֶβ�Ϊ�գ�</li>" +
            "<ul>" +
            "<li>ѧ��</li>" +
            "<li>����</li>" +
            "<li>�༶</li>" +
            "<li>�꼶</li>" +
            "<li>רҵ</li>" +
            "</ul>" +
            "<li>ֻ�������б����ֶξ�����д������£����ܳɹ����ѧ����Ϣ��</li>" +
            "</ul>" +
            "<p><strong>2. ɾ��ѧ����Ϣ��</strong></p>" +
            "<ul>" +
            "<li>ͨ��ѧ�Ž���ɾ��������</li>" +
            "<li>���ṩҪɾ����ѧ����ѧ�š�</li>" +
            "</ul>" +
            "<p><strong>3. �޸�ѧ����Ϣ��</strong></p>" +
            "<ul>" +
            "<li>ͨ��ѧ�Ž����޸Ĳ�����</li>" +
            "<li>���ṩҪ�޸ĵ�ѧ����ѧ�š�</li>" +
            "<li>�޸�ʱ��ȷ����д�����ֶΣ�</li>" +
            "<ul>" +
            "<li>����</li>" +
            "<li>�༶</li>" +
            "<li>�꼶</li>" +
            "<li>רҵ</li>" +
            "</ul>" +
            "</ul>" +
            "<p><strong>4. ����ѧ����Ϣ��</strong></p>" +
            "<ul>" +
            "<li>ͨ��ѧ�Ų���ѧ����Ϣ��</li>" +
            "<li>���ṩҪ���ҵ�ѧ����ѧ�š�</li>" +
            "</ul>" +
            "</body></html>";
        
        JOptionPane.showMessageDialog(null, message, "��������", JOptionPane.INFORMATION_MESSAGE);
    }
	public static void showteacherHelpMessage() {
		String message = "<html><body>" +
			    "<h3>�������ѣ�</h3>" +
			    "<p><strong>1. ��ӽ�ʦ��Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>��ȷ�������ֶβ�Ϊ�գ�</li>" +
			    "<ul>" +
			    "<li>��ʦID</li>" +
			    "<li>����</li>" +
			    "<li>����</li>" +
			    "<li>�ֻ���</li>" +
			    "<li>����</li>" +
			    "</ul>" +
			    "<li>ֻ�������б����ֶξ�����д������£����ܳɹ���ӽ�ʦ��Ϣ��</li>" +
			    "</ul>" +
			    "<p><strong>2. ɾ����ʦ��Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>ͨ����ʦID����ɾ��������</li>" +
			    "<li>���ṩҪɾ���Ľ�ʦ��ID��</li>" +
			    "</ul>" +
			    "<p><strong>3. �޸Ľ�ʦ��Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>ͨ����ʦID�����޸Ĳ�����</li>" +
			    "<li>���ṩҪ�޸ĵĽ�ʦ��ID��</li>" +
			    "<li>�޸�ʱ��ȷ����д�����ֶΣ�</li>" +
			    "<ul>" +
			    "<li>����</li>" +
			    "<li>����</li>" +
			    "</ul>" +
			    "</ul>" +
			    "<p><strong>4. ���ҽ�ʦ��Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>ͨ����ʦID���ҽ�ʦ��Ϣ��</li>" +
			    "<li>���ṩҪ���ҵĽ�ʦ��ID��</li>" +
			    "</ul>" +
			    "</body></html>";

        
        JOptionPane.showMessageDialog(null, message, "��������", JOptionPane.INFORMATION_MESSAGE);
    }
	public static void showchallengeHelpMessage() {
		String message = "<html><body>" +
			    "<h3>�������ѣ�</h3>" +
			    "<p><strong>1. ��Ӿ�����Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>��ȷ�������ֶβ�Ϊ�գ�</li>" +
			    "<ul>" +
			    "<li>����ID</li>" +
			    "<li>��������</li>" +
			    "<li>���췽</li>" +
			    "<li>�а췽</li>" +
			    "<li>����</li>" +
			    "</ul>" +
			    "<li>ֻ�������б����ֶξ�����д������£����ܳɹ���Ӿ�����Ϣ��</li>" +
			    "</ul>" +
			    "<p><strong>2. ɾ��������Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>ͨ������ID����ɾ��������</li>" +
			    "<li>���ṩҪɾ���ľ�����ID��</li>" +
			    "<li>ɾ��������Ϣʱ�������������´��ڣ��������ʾ����ֹɾ����</li>" +
			    "</ul>" +
			    "<p><strong>3. �޸ľ�����Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>ͨ������ID�����޸Ĳ�����</li>" +
			    "<li>���ṩҪ�޸ĵľ�����ID��</li>" +
			    "<li>�޸�ʱ��ȷ����д�����ֶΣ�</li>" +
			    "<ul>" +
			    "<li>��������</li>" +
			    "<li>���췽</li>" +
			    "<li>�а췽</li>" +
			    "<li>����</li>" +
			    "</ul>" +
			    "</ul>" +
			    "<p><strong>4. ���Ҿ�����Ϣ��</strong></p>" +
			    "<ul>" +
			    "<li>ͨ������ID���Ҿ�����Ϣ��</li>" +
			    "<li>���ṩҪ���ҵľ�����ID��</li>" +
			    "</ul>" +
			    "</body></html>";


        
        JOptionPane.showMessageDialog(null, message, "��������", JOptionPane.INFORMATION_MESSAGE);
    }
}
