package 五子棋.初版.demo.frame;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DemoFrame2 extends javax.swing.JFrame {
	public static DemoFrame2 df; 	
	public int vqpan[][];//虚拟棋盘（二维数组）
	public boolean isPeple;//是否人先落子
	
	public DemoFrame2(){		
		InitPan();			
	}
	
	//初始化棋盘
	public void InitPan(){
		
		this.setTitle("欢乐五子棋   作者：云南工商学院 17计科N班  jiyc");	
		this.setResizable(false);//窗口大小不可改变
		this.setSize(458,480);//窗口大小		
		this.setLocation(500, 100);//窗口位置
		this.setVisible(true);//窗口可视化
		//初始化虚拟棋盘（二维数组）
		vqpan=new int[15][15];
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				vqpan[i][j]=0;
			}
		}
		isPeple=true;
	}
	//重写绘制方法；
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		ImageIcon ibg=new ImageIcon("src//img//qipan.png");//棋盘图片
		ImageIcon bimg=new ImageIcon("src//img//bai.png");//白棋子图片
		ImageIcon himg=new ImageIcon("src//img//hei.png");//黑棋子图片
		Graphics gh=this.getGraphics();
		
		//绘制棋盘；
		gh.drawImage(ibg.getImage(), 3, 25, null);	
		
		//根据虚拟棋盘绘制棋子视图
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				//绘制白子
				if(vqpan[i][j]==1){
					int x=19+30*j;
					int y=40+30*i;
					gh.drawImage(bimg.getImage(), x-13, y-13, null);
				}
				//绘制黑子
				if(vqpan[i][j]==2){
					int x=19+30*j;
					int y=40+30*i;
					gh.drawImage(himg.getImage(), x-13, y-13, null);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		df=new DemoFrame2();
		df.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//计算虚拟棋盘（二维数组）落子坐标(i,j)
				int x,y,i,j;
				x=e.getPoint().x-19;
				y=e.getPoint().y-40;
				if(x%30>15){
					j=x/30+1;
				}
				else{
					j=x/30;
				}
				if(y%30>15){
					i=y/30+1;
				}
				else{
					i=y/30;
				}
				if(df.vqpan[i][j]==0){
					if(df.isPeple) {
						df.isPeple=false;
						df.vqpan[i][j]=1;
					df.vqpan[i][j]=1;//虚拟棋盘落白子
					for(int q=0;q<15;q++) {
						for(int w=0;w<15;w++) {
							if(df.vqpan[q][w]==1&&df.vqpan[q][w+1]==1&&df.vqpan[q][w+2]==1&&df.vqpan[q][w+3]==1&&df.vqpan[q][w+4]==1||
								df.vqpan[q][w]==1&&df.vqpan[q+1][w]==1&&df.vqpan[q+2][w]==1&&df.vqpan[q+3][w]==1&&df.vqpan[q+4][w]==1||
								df.vqpan[q][w]==1&&df.vqpan[q+1][w+1]==1&&df.vqpan[q+2][w+2]==1&&df.vqpan[q+3][w+3]==1&&df.vqpan[q+4][w+4]==1||
								df.vqpan[q][w]==1&&df.vqpan[q+1][w-1]==1&&df.vqpan[q+2][w-2]==1&&df.vqpan[q+3][w-3]==1&&df.vqpan[q+4][w-4]==1
									) {
								JOptionPane.showMessageDialog(null, "白棋获胜", "对战结果:", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					/////添加判断输赢代码		
					}
					else {
					df.isPeple=true;
					df.vqpan[i][j]=2;//计算机虚拟棋盘落黑子
					}
					for(int q=0;q<15;q++) {
						for(int w=0;w<15;w++) {
							if(df.vqpan[q][w]==2&&df.vqpan[q][w+1]==2&&df.vqpan[q][w+2]==2&&df.vqpan[q][w+3]==2&&df.vqpan[q][w+4]==2||
								df.vqpan[q][w]==2&&df.vqpan[q+1][w]==2&&df.vqpan[q+2][w]==2&&df.vqpan[q+3][w]==2&&df.vqpan[q+4][w]==2||
								df.vqpan[q][w]==2&&df.vqpan[q+1][w+1]==2&&df.vqpan[q+2][w+2]==2&&df.vqpan[q+3][w+3]==2&&df.vqpan[q+4][w+4]==2||
								df.vqpan[q][w]==2&&df.vqpan[q+1][w-1]==2&&df.vqpan[q+2][w-2]==2&&df.vqpan[q+3][w-3]==2&&df.vqpan[q+4][w-4]==2
									) {
								JOptionPane.showMessageDialog(null, "电脑获胜", "对战结果:", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					/////添加判断输赢代码					
					df.repaint();//刷新，重新绘画
				}
			}
		});
	}
	
}

