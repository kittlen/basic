package 五子棋.初版.demo.frame;


import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class DemoFrame extends javax.swing.JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7601989336697296444L;
	public static DemoFrame df; 	
	public static int vqpan[][];//虚拟棋盘（二维数组）
	//public boolean isPeple;//是否人先落子
	
	public DemoFrame(){		
		InitPan();			
	}
	
	//初始化棋盘
	public void InitPan(){
		
		this.setTitle("欢乐五子棋   作者：云南工商学院 17计科N班  jiyc");//视图标题
		this.setResizable(false);//设置窗体是否可由用户调节
		this.setSize(458,480);	//宽高	
		this.setLocation(500, 100);	//设置组件所在位置
		this.setVisible(true);//将窗体显示出来
		//初始化虚拟棋盘（二维数组）
		vqpan=new int[15][15];
		for(int i=0;i<15;i++){
			for(int j=0;j<15;j++){
				vqpan[i][j]=0;
			}
		}
		//isPeple=true;
	}
	//重写绘制方法；
	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		ImageIcon ibg=new ImageIcon("src//img//qipan.png");//棋盘图片
		ImageIcon bimg=new ImageIcon("src//img//bai.png");//白棋子图片
		ImageIcon himg=new ImageIcon("src//img//hei.png");//黑棋子图片
		Graphics gh=this.getGraphics();//重写JFrame对象的paint方法,获取'画笔'Graohics
		
		//绘制棋盘；
		gh.drawImage(ibg.getImage(), 3, 25, null);	
		//绘制指定图像中已缩放到适合指定矩形内部的图像
		/*img - 要绘制的指定图像。如果 img 为 null，则此方法不执行任何操作。
		x - x 坐标。
		y - y 坐标。
		observer - 转换了更多图像时要通知的对象。*/
		
		/*img - 要绘制的指定图像。如果 img 为 null，则此方法不执行任何动作。
		x - x 坐标。
		y - y 坐标。
		width - 矩形的宽度。
		height - 矩形的高度。
		observer - 当转换了更多图像时要通知的对象*/
		
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
		df=new DemoFrame();
		//获得鼠标事件
		df.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {//鼠标按钮在组件上释放时调用
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {// 鼠标按键在组件上按下时调用。
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {//鼠标离开组件时调用。
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {// 鼠标进入到组件上时调用。
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {// 鼠标按键在组件上单击（按下并释放）时调用。
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
					df.vqpan[i][j]=1;//虚拟棋盘落白子
					for(int q=0;q<15;q++) {
						for(int w=0;w<15;w++) {/////添加判断输赢代码	
							if(df.vqpan[q][w]==1&&df.vqpan[q][w+1]==1&&df.vqpan[q][w+2]==1&&df.vqpan[q][w+3]==1&&df.vqpan[q][w+4]==1||
								df.vqpan[q][w]==1&&df.vqpan[q+1][w]==1&&df.vqpan[q+2][w]==1&&df.vqpan[q+3][w]==1&&df.vqpan[q+4][w]==1||
								df.vqpan[q][w]==1&&df.vqpan[q+1][w+1]==1&&df.vqpan[q+2][w+2]==1&&df.vqpan[q+3][w+3]==1&&df.vqpan[q+4][w+4]==1||
								df.vqpan[q][w]==1&&df.vqpan[q+1][w-1]==1&&df.vqpan[q+2][w-2]==1&&df.vqpan[q+3][w-3]==1&&df.vqpan[q+4][w-4]==1
									) {
								JOptionPane.showMessageDialog(null, "白棋获胜", "对战结果:", JOptionPane.INFORMATION_MESSAGE);return;
								//弹窗
							}
						}
					}
					
					boolean g=true;
					for(int qq=0;qq<15;qq++){
						for(int ww=0;ww<15;ww++){
							if(g==true){//计算机虚拟棋盘落黑子
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq][ww+1]==1&&df.vqpan[qq][ww+2]==1&&df.vqpan[qq][ww+3]==2){
								df.vqpan[qq][ww-2]=2;g=false;break;//4
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww]==1&&df.vqpan[qq+2][ww]==1&&df.vqpan[qq+3][ww]==2){
								df.vqpan[qq-2][ww]=2;g=false;break;//4
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww+1]==1&&df.vqpan[qq+2][ww+2]==1&&df.vqpan[qq+3][ww+3]==2){
								df.vqpan[qq-2][ww-2]=2;g=false;break;//4
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww-1]==1&&df.vqpan[qq+2][ww-2]==1&&df.vqpan[qq+3][ww-3]==2){
								df.vqpan[qq+2][ww-2]=0;g=false;break;//4
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq-1][ww+1]==1&&df.vqpan[qq-2][ww+2]==1&&df.vqpan[qq-3][ww+3]==2){
								df.vqpan[qq+2][ww-2]=2;g=false;break;//4;
							}
							if((df.vqpan[qq][ww]==1&&df.vqpan[qq][ww+1]==1&&df.vqpan[qq][ww+2]==1)&&df.vqpan[qq][ww+3]==0){
								df.vqpan[qq][ww+3]=2;g=false;break;//3
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww]==1&&df.vqpan[qq+2][ww]==1&&df.vqpan[qq+3][ww]==0){
								df.vqpan[qq+3][ww]=2;g=false;break;//3
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww+1]==1&&df.vqpan[qq+2][ww+2]==1&&df.vqpan[qq+3][ww+3]==0){
								df.vqpan[qq+3][ww+3]=2;g=false;break;//3
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww-1]==1&&df.vqpan[qq+2][ww-2]==1&&df.vqpan[qq+3][ww-3]==0){
								df.vqpan[qq+3][ww-3]=0;g=false;break;//3
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq-1][ww+1]==1&&df.vqpan[qq-2][ww+2]==1&&df.vqpan[qq-3][ww+3]==0){
								df.vqpan[qq-3][ww+3]=2;g=false;break;//3
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq-1][ww-1]==1&&df.vqpan[qq-2][ww-2]==1&&df.vqpan[qq-3][ww-3]==0){
								df.vqpan[qq][ww]=2;g=false;break;
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww]==1&&df.vqpan[qq+2][ww]==0){
								df.vqpan[qq+2][ww]=2;g=false;break;//2
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq][ww+1]==1&&df.vqpan[qq][ww+2]==0){
								df.vqpan[qq][ww+2]=2;g=false;break;//2
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww-1]==1&&df.vqpan[qq+2][ww-2]==0){
								df.vqpan[qq+2][ww-2]=2;g=false;break;
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq-1][ww+1]==1&&df.vqpan[qq-2][ww+2]==0){
								df.vqpan[qq-2][ww+2]=2;g=false;break;
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq+1][ww]==0){
								df.vqpan[qq+1][ww]=2;g=false;break;//1
							}
							if(df.vqpan[qq][ww]==1&&df.vqpan[qq][ww+1]==0){
								df.vqpan[qq][ww+1]=2;g=false;break;//1
							}
							}
						}
					}
					//df.vqpan[X][y]=2;
					for(int q=0;q<15;q++) {
						for(int w=0;w<15;w++) {/////添加判断输赢代码	
							if(df.vqpan[q][w]==2&&df.vqpan[q][w+1]==2&&df.vqpan[q][w+2]==2&&df.vqpan[q][w+3]==2&&df.vqpan[q][w+4]==2||
								df.vqpan[q][w]==2&&df.vqpan[q+1][w]==2&&df.vqpan[q+2][w]==2&&df.vqpan[q+3][w]==2&&df.vqpan[q+4][w]==2||
								df.vqpan[q][w]==2&&df.vqpan[q+1][w+1]==2&&df.vqpan[q+2][w+2]==2&&df.vqpan[q+3][w+3]==2&&df.vqpan[q+4][w+4]==2||
								df.vqpan[q][w]==2&&df.vqpan[q+1][w-1]==2&&df.vqpan[q+2][w-2]==2&&df.vqpan[q+3][w-3]==2&&df.vqpan[q+4][w-4]==2
									) {
								JOptionPane.showMessageDialog(null, "电脑获胜", "对战结果:", JOptionPane.ERROR_MESSAGE);
							}
						}
					}
					
					
					//重新绘制棋盘
					df.repaint();
				}
			}
		});
	}
	
}

