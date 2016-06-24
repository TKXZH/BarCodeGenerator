/* name: ENA-13 Bar-code Generator *
 * author: XVZH from CCNU          *
 * time:2016-05-04                 *
 * all right reserved              */
 

import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Iterator;
import java.util.List;






@SuppressWarnings("serial")
public class MainFrame extends Frame{
	TextField tf = new TextField(13);
	Label l = new Label("输入12位ENA条码号：");
	Button bt = new Button("生成条码图片");
	BinaryNumGenerator generator;
	java.awt.Image offscreenImage = null;
	
	
	public MainFrame(int x, int y, int width, int height, Color color) {		
			super("EAN-13条码生成");
			this.setBackground(color);
			this.setLayout(new FlowLayout());
			this.add(l);
			this.add(tf);
			this.add(bt);
			this.setBounds(x, y, width, height);
			this.setVisible(true);
			this.setResizable(true);
			validate();
	}
	
	public static void main(String[] args) {
		MainFrame m = new MainFrame(50,50,800,600,Color.white);
		m.launch();
	}
	
	public void launch() {
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e) {
				System.exit(0);//重写父类窗口关闭方法
			}
		});//使用匿名内部类实现监听器
		
		this.bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String sequence = tf.getText();
				if(sequence.length()!=12) {
					tf.setText("输入错误");
				}
				
				else {
					char[] s = sequence.toCharArray();
					int [] data = new int[13];
					for(int i=0; i<12; i++) {
						data[i] = s[i]-48;
					}
					int sum1 = data[11] + data[9] + data[7] + data[5] + data[3] + data[1];
					int sum2 = data[10] + data[8] + data[6] + data[4] + data[2] + data[0];
					int sum = 3*sum1 + sum2;
					System.out.println(sum);
					int unit = sum - (sum/10)*10;
					System.out.println(unit);
					if(unit == 0){
						data[12] = 0;
					}
					else {
						data[12] = 10 - unit;
					}
					generator = new BinaryNumGenerator(data);
				}
				
			}
		});
		new Thread(new PaintThread()).start();//启动重画线程
	}
	
	public Iterator<int[]> getIT() {
		List<int[]> binaryCodes = generator.getBinaryCodes();
		Iterator<int[]> it = binaryCodes.iterator();
		return it;
	}

	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		if(generator!=null) {
			int pos_x = 150;
			int pos_y = 150;
			int width = 5;
			int height = 200;
			int j = 1; 
			Iterator<int[]>it = this.getIT();
			while(it.hasNext()) {
				int[] a = it.next();
				for(int i=0; i<a.length; i++) {
					if(j==1|j==2|j==3|j==95|j==94|j==93|j==46|j==47|j==48|j==49|j==50){
						height = 220;
					}
					if(a[i]==0) {
						g.setColor(Color.white);
						g.fillRect(pos_x, pos_y, width, height);
						j++;
						height = 200;
						pos_x+=width;
					}
					if(a[i]==1) {
						g.setColor(Color.black);
						g.fillRect(pos_x, pos_y, width, height);
						j++;
						height = 200;
						pos_x+=width;
					}
				}
			}
		}

	}
	
	public class PaintThread implements Runnable {
		public void run() {
			while(true) {
				repaint();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}
		
	}   //界面重画线程
	
	public void update(Graphics g) {
		if(offscreenImage == null) {
			offscreenImage = this.createImage(800, 600);	
		}
		Graphics gOffScreen = offscreenImage.getGraphics();
		gOffScreen.setColor(Color.white);
		gOffScreen.fillRect(0, 0, 800, 600);
		paint(gOffScreen);
		g.drawImage(offscreenImage, 0, 0, null);
	} //双缓冲消除重影
}


