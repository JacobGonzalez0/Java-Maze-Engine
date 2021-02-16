package util;

public class Screen {
    private final int WIDTH;
    private final int HEIGHT;
    private StringBuilder[] scanlines;

    public Screen(int w, int h){
        this.WIDTH = w;
        this.HEIGHT = h;
        scanlines = new StringBuilder[h];
        for(int i = 0; i < this.HEIGHT-1; i++){
            scanlines[i] = new StringBuilder();
            scanlines[i].setLength(this.WIDTH); //inits every line
            for(int o = 0; o < this.WIDTH-1;o++){ //fill with empty characters
                scanlines[i].setCharAt(o, ' ');
            }
        }
    }

    public void clearBuffer(){
        for(int i = 0; i < this.HEIGHT-1; i++){
            scanlines[i].setLength(0); //clears the scanline
            scanlines[i].setLength(this.WIDTH); //reinit
            for(int o = 0; o < this.WIDTH-1;o++){ //fill with empty characters
                scanlines[i].setCharAt(o, ' ');
            }
        }
    }

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public void draw(){
        clearScreen();
        for(int i = 0; i < this.HEIGHT-1; i++){
            System.out.println(scanlines[i]); 
        }
    }

    public void drawChar(char c, int x, int y){
        scanlines[y].setCharAt(x,c);
    }

    public void drawLine(char c, int x1, int y1, int x2, int y2){

        int x, y, dx, dy, dx1, dy1, px, py, xe, ye, i;
        // Calculate line deltas
        dx = x2 - x1;
        dy = y2 - y1;
        // Create a positive copy of deltas (makes iterating easier)
        dx1 = Math.abs(dx);
        dy1 = Math.abs(dy);
        // Calculate error intervals for both axis
        px = 2 * dy1 - dx1;
        py = 2 * dx1 - dy1;

        // The line is X-axis dominant
        if (dy1 <= dx1) {
            // Line is drawn left to right
            if (dx >= 0) {
                x = x1; y = y1; xe = x2;
            } else { // Line is drawn right to left (swap ends)
                x = x2; y = y2; xe = x1;
            }
            drawChar(c, x, y); // Draw first pixel
            for (i = 0; x < xe; i++) {
                x = x + 1;
                if (px < 0) {
                    px = px + 2 * dy1;
                } else {
                    if ((dx < 0 && dy < 0) || (dx > 0 && dy > 0)) {
                        y = y + 1;
                    } else {
                        y = y - 1;
                    }
                    px = px + 2 * (dy1 - dx1);
                }
                // Draw pixel from line span at
                // currently rasterized position
                drawChar(c, x, y);
            }
        } else { // The line is Y-axis dominant
            // Line is drawn bottom to top
            if (dy >= 0) {
                x = x1; y = y1; ye = y2;
            } else { // Line is drawn top to bottom
                x = x2; y = y2; ye = y1;
            }
            drawChar(c, x, y); // Draw first pixel
            // Rasterize the line
            for (i = 0; y < ye; i++) {
                y = y + 1;
                if (py <= 0) {
                    py = py + 2 * dx1;
                } else {
                    if ((dx < 0 && dy<0) || (dx > 0 && dy > 0)) {
                        x = x + 1;
                    } else {
                        x = x - 1;
                    }
                    py = py + 2 * (dx1 - dy1);
                }
                // Draw pixel from line span at
                // currently rasterized position
                drawChar(c, x, y);;
            }
        }
        
    }


}
