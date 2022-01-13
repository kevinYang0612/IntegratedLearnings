import javax.swing.*;
import java.awt.*;
import java.util.Objects;
import java.util.Scanner;

public class ThreadLearning
{
    public static void main(String[] args)
    {
        /**Task 1 here console level */
//         Scanner scanner = new Scanner(System.in);
//         System.out.println("Type something to stop printing");
//
//        Counter1 counter1 = new Counter1();
//        Thread thread = new Thread(counter);
//        thread.start();
//
//        String flag = scanner.nextLine();
//        if (!flag.isEmpty())
//        {
//            counter.setRequestStop();
//        }
        /** Task 2 and 3 GUI level */
//        Counter counter = new Counter();
//        Thread t = new Thread(counter);
//        t.start();
//        Frame frame = new Frame();
//        Counter counter1 = new Counter(frame);
//        Thread t = new Thread(counter1);
//        t.start();
//        while (true)
//        {
//            while (!frame.isPrinting())
//            {
//                t.suspend();
//            }
//            t.resume();
//        }
        /** Task 4 reverse GUI level */
        Counter counter = new Counter();
        CounterReverse counterReverse = new CounterReverse();
        Thread t = new Thread(counter);

        Thread t1 = new Thread(counterReverse);
        t.start();
        t1.start();
    }
}

class CounterReverse extends Counter
{
    private int temp = 9;
    @Override
    public void run()
    {
        sleep(4000);
        while (true)
        {
            if (this.isPrinting())
            {
                for ( ; temp >= 0 && super.isPrinting(); temp--)
                {
                    this.getTextArea().append("" + temp);
                    sleep(100);
                }
                if (temp < 0)
                {
                    temp = 9;
                    this.getTextArea().append("\n");
                }
            }
            this.getTextArea().append("");
        }
    }
}
class Counter extends JFrame implements Runnable
{
    private final JTextArea textArea = new JTextArea(100, 100);
    private JButton startButton = new JButton("start");
    private JButton stopButton = new JButton("stop");
    private final JPanel southPanel = new JPanel(new FlowLayout());
    private boolean isPrinting = true;
    private int temp;

    public Counter()
    {
        this.setTitle("simple print work by Kevin Yang");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 400);
        setTextArea();
        setButtons();
        this.setVisible(true);
    }
    public boolean isPrinting() {
        return isPrinting;
    }
    public JTextArea getTextArea() {
        return textArea;
    }
    public void setTextArea()
    {
        this.textArea.setBounds(80, 75, 100, 100);
        this.textArea.setFont(new Font("Ink Free", Font.BOLD,15));
        this.textArea.setBackground(Color.WHITE);
        this.textArea.setLineWrap(true);
        this.textArea.setVisible(true);
        this.textArea.setEnabled(false);
        this.add(this.textArea);
    }
    public void setButtons()
    {
        this.southPanel.add(this.startButton);
        this.southPanel.add(this.stopButton);
        this.add(this.southPanel, BorderLayout.SOUTH);
        this.startButton.addActionListener(e ->
        {
            this.isPrinting = true;
        });
        this.stopButton.addActionListener(e ->
        {
            this.isPrinting = false;
        });
    }
    @Override
    public void run()
    {
        sleep(4000);
        while (true)
        {
            if (this.isPrinting())
            {
                for ( ; temp <= 9 && this.isPrinting; temp++)
                {
                    this.getTextArea().append("" + temp);
                    sleep(100);
                }
                if (temp > 9)
                {
                    temp = 0;
                    this.getTextArea().append("\n");
                }
            }
            this.getTextArea().append("");
        }

    }
    public void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}


class Counter1 implements Runnable // print 0 to 9 on a single line and skips to the next line
{
    private boolean requestStop = false;
    public synchronized void setRequestStop() { this.requestStop = true; }
    public synchronized boolean isRequestStop() {return this.requestStop; }

    public void sleep(long millis)
    {
        try
        {
            Thread.sleep(millis);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void run()
    {
        while (!isRequestStop())
        {
            for (int i = 0; i <= 9; i++)
            {
                System.out.print(i);
            }
            System.out.println();
            sleep(2000);
        }
        System.out.println("Stopped");
    }
}