package cn.myself.day3.screenbroadcast;

import javax.swing.*;

/**
 * Created by WY on 2017/11/14.
 */
public class StudentUI extends JFrame{
    private JLabel lblIcon ;

    public StudentUI(){
        init();
    }

    private void init() {
        this.setTitle("学生端");
        this.setBounds(0, 0, 1280, 800);
        this.setLayout(null);

        //标签空间
        lblIcon = new JLabel();
        lblIcon.setBounds(0, 0, 1280, 800);

        //图标
        ImageIcon icon = new ImageIcon("D:/Koala.jpg");
        lblIcon.setIcon(icon);
        this.add(lblIcon);
        this.setVisible(true);
    }

    /**
     * 更新图标
     */
    public void updateIcon(byte[] dataBytes){
        ImageIcon icon = new ImageIcon(dataBytes);
        lblIcon.setIcon(icon);
    }
}
