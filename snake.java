import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;
public class snake extends JFrame{
    snake(){
        add(new bord());
        pack(); // reset the frame
        this.setTitle("SNAKE GAME");
        this.setBounds(500, 150, 500, 500);
        
        this.setVisible(true);
    }
    public class bord extends JPanel implements ActionListener{
        public int dots; 
        int apple_x;
        int apple_y;
        int random_number=29;
        Image apple;
        Image dot;
        Image head;
        Timer timer;
        int hight=500;
        int width=500;
        public final int one_dot = 10;
        public final int total_dot = (hight*width)/one_dot;
        public final int x[] = new int[total_dot];
        public final int y[] = new int[total_dot];
        boolean leftmove = false;
        boolean rightmove = true;
        boolean upmove = false;
        boolean downmove = false;
        boolean ingame = true;

        bord(){
            addKeyListener(new TAdapter());
            setBackground(Color.BLACK);
            setFocusable(true);
            
            init_game();
            load_images();
            
        }

        public void load_images(){
            head = Toolkit.getDefaultToolkit().getImage("D://Java//SOMNATH//icons//head.png");
            dot = Toolkit.getDefaultToolkit().getImage("D:\\Java\\SOMNATH\\icons\\dot.png");
            apple =Toolkit.getDefaultToolkit().getImage("D:\\Java\\SOMNATH\\icons\\apple.png");
            
        }

        public void locate_apple(){
            int r = (int)(Math.random() * random_number);
            apple_x = r*one_dot; 
            r = (int)(Math.random() * random_number);
            apple_y = r*one_dot; 
        }

        public void init_game(){
            dots =3;
            for(int i=0;i<dots;i++){
                y[i]=50;
                x[i] = 50 - (i*one_dot);
            }
            locate_apple();

            timer = new Timer(100, this);//call the action performed method after 140 ms
            timer.start();
        }

        public void paintComponent(Graphics g){
            super.paintComponent(g);

            draw(g);
        } 

        public void draw(Graphics g){
            if(ingame==true){
                g.drawImage(apple, apple_x, apple_y, this);
                for(int i =0 ; i<dots;i++){
                    if(i==0){
                        g.drawImage(head, x[i], y[i], this);
                    }
                    else{
                        g.drawImage(dot, x[i], y[i], this);
                    }
                }
            }
        else{
            game_over(g);
        }
            
    }

        public void game_over(Graphics g){
            Font font1 = new Font("Console", Font.BOLD, 50);
            g.setFont(font1);
            g.setColor(Color.RED);
            String msg = "Game Over";
            g.drawString(msg, 110, 210);

            Font font2 = new Font("Console", Font.BOLD, 30);
            g.setFont(font2);
            g.setColor(Color.WHITE);
            String d = "" + (dots-3);
            g.drawString("Score : "+d, 110, 300);
            
        }
        public void move(){
            for(int i=dots;i>0;i--){
                x[i]=x[i-1];
                y[i]=y[i-1];
            }
            if(leftmove==true){
                x[0]= x[0] - one_dot;
            }
            if(rightmove==true){
                x[0]= x[0] + one_dot;
            }
            if(upmove==true){
                y[0] = y[0] - one_dot;
            }
            if(downmove==true){
                y[0] = y[0] + one_dot;
            }
            
        }
        public void apple_eat(){
            if((x[0]==apple_x) && (y[0]==apple_y)){
                dots++;
                locate_apple();
            }
        }
        public void colletion(){
            for (int i=dots;i>0;i--){

                if((i>4 && x[0]==x[i]) && (i>4 && y[0]==y[i])){
                    ingame = false;
                }
            
                if(x[0]>=470 || y[0]>=470 || x[0]<0 || y[0]<0){
                    ingame=false;
                }
                if(ingame==false){
                    timer.stop();
                }
            }
        }
        public void actionPerformed(ActionEvent e){
            if(ingame==true){
                colletion();
                apple_eat();
                move();
            }
            
            repaint();
        }

        public class TAdapter extends KeyAdapter{
            public void keyPressed(KeyEvent e){
                int key = e.getKeyCode();

                if(key == KeyEvent.VK_LEFT && (rightmove == false)){
                    leftmove=true;
                    upmove=false;
                    downmove=false;
                }
                if(key == KeyEvent.VK_RIGHT && (leftmove == false)){
                    rightmove=true;
                    upmove=false;
                    downmove=false;
                }
                if(key == KeyEvent.VK_UP && (downmove == false)){
                    leftmove=false;
                    upmove=true;
                    rightmove=false;
                }
                if(key == KeyEvent.VK_DOWN && (upmove == false)){
                    leftmove=false;
                    downmove=true;
                    rightmove=false;
                }
            }
        }
    }
    
    
    public static void main(String[] args) {
        new snake();
    }

}
