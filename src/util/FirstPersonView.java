package util;

public class FirstPersonView {

    private int WIDTH;
    private int HEIGHT;
    private int slopeMultipler;


    public FirstPersonView(int w, int h){
        this.WIDTH = w-1;
        this.HEIGHT = h-1;
        this.slopeMultipler = 4;
    }

    public void leftWall(int depth, Screen screen){
        
        int start = 6*depth;

        if(depth == 1){
            start = 1;
        }

        screen.drawLine('x', //top line
            start, start/this.slopeMultipler+1,
            12*depth, (this.slopeMultipler*depth));

        screen.drawLine('x', //bottom line
            start, this.HEIGHT-start/this.slopeMultipler-1,
            12*depth, this.HEIGHT-(this.slopeMultipler*depth));

        screen.drawLine('x', //left line
            start, start/this.slopeMultipler+1,
            start, this.HEIGHT-start/this.slopeMultipler-1);

        screen.drawLine('x', //right line
            12*depth, (this.slopeMultipler*depth),
            12*depth, this.HEIGHT-(this.slopeMultipler*depth));
    }

    public void rightWall(int depth, Screen screen){
        int start = 6*depth;

        if(depth == 1){
            start = 1;
        }
        
        screen.drawLine('x', //top line
            this.WIDTH-start, start/this.slopeMultipler+1,
            this.WIDTH-12*depth, (this.slopeMultipler*depth));

        screen.drawLine('x', //bottom line
            this.WIDTH-start, this.HEIGHT-start/this.slopeMultipler-1,
            this.WIDTH-12*depth, this.HEIGHT-(this.slopeMultipler*depth));

        screen.drawLine('x', ////left line
            this.WIDTH-12*depth, (this.slopeMultipler*depth),
            this.WIDTH-12*depth, this.HEIGHT-(this.slopeMultipler*depth));

        screen.drawLine('x', ////right line
            this.WIDTH-start, start/this.slopeMultipler+1,
            this.WIDTH-start, this.HEIGHT-start/this.slopeMultipler-1);
    }
    
}
