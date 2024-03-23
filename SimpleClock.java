package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame implements ActionListener {
    
        Calendar calendar;
        SimpleDateFormat timeFormat;
        SimpleDateFormat dayFormat;
        SimpleDateFormat dateFormat;
    
        JLabel timeLabel;
        JLabel dayLabel;
        JLabel dateLabel;
        String time;
        String day;
        String date;

    JToggleButton twelveToTwentyfourButton = new JToggleButton("12 hour", false);
    JToggleButton localToGMT = new JToggleButton("LocalTime", false);

        SimpleClock() {
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.setTitle("Digital Clock");
            this.setLayout(new FlowLayout());
            this.setSize(400, 250);
            this.setResizable(false);
    
            timeFormat = new SimpleDateFormat("hh:mm:ss a");
            dayFormat=new SimpleDateFormat("EEEE");
            dateFormat=new SimpleDateFormat("dd MMMMM, yyyy");
            timeLabel = new JLabel();
            timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
            timeLabel.setBackground(Color.BLACK);
            timeLabel.setForeground(Color.WHITE);
            timeLabel.setOpaque(true);
            dayLabel=new JLabel();
            dayLabel.setFont(new Font("Ink Free",Font.BOLD,34));
            twelveToTwentyfourButton.setBounds(200, 100, 100, 50);
            twelveToTwentyfourButton.addActionListener(this);
            twelveToTwentyfourButton.setFocusable(false);
            localToGMT.setBounds(-200, -100, 100, 50);
            localToGMT.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(localToGMT.isSelected() == true) {
                        localToGMT.setText("GMT");
                        timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
                    }
                    else {
                        localToGMT.setText("LocalTime");
                        timeFormat.setTimeZone(TimeZone.getDefault());
                    }
                }
            });
            localToGMT.setFocusable(false);
    
            dateLabel=new JLabel();
            dateLabel.setFont(new Font("Ink Free",Font.BOLD,30));
    
            this.add(timeLabel);
            this.add(dayLabel);
            this.add(dateLabel);
            this.setVisible(true);
            this.add(twelveToTwentyfourButton);
            this.add(localToGMT);
    
            setTimer();

        }
    
        public void setTimer() {
            while (!Thread.currentThread().isInterrupted()) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);
    
                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);
    
                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

    
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    Thread.currentThread().isInterrupted();
                    e.getStackTrace();
                }
            }
        }



        public static void main(String[] args) {
            new SimpleClock();
        }

    @Override
    public void actionPerformed(ActionEvent e) {
            if(twelveToTwentyfourButton.isSelected() == true) {
                twelveToTwentyfourButton.setText("24 hour");
                timeFormat = new SimpleDateFormat("HH:mm:ss a");
            }
            else {
                timeFormat = new SimpleDateFormat("hh:mm:ss a");
                twelveToTwentyfourButton.setText("12");
            }
    }
    public void actionTwoPerformed(ActionEvent e) {

    }
}
