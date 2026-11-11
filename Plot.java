/*
 * Class: CMSC203 
 * Instructor: 
 * Description: Represents a plot of land with x, y coordinates, width, and depth.
 * Due: 11/10/2026
 * Platform/compiler: Eclipse / Java
 * I pledge that I have completed the programming 
 * assignment independently. I have not copied the code 
 * from a student or any source. I have not given my code 
 * to any student.
   Print Your Name Here: Ezedin
*/

public class Plot {
    private int px;
    private int py;
    private int pWidth;
    private int pDepth;

    public Plot() {
        this(0, 0, 1, 1);
    }

    public Plot(int xCord, int yCord, int w, int d) {
        px = xCord;
        py = yCord;
        pWidth = w;
        pDepth = d;
    }

    public Plot(Plot sample) {
        if (sample != null) {
            px = sample.px;
            py = sample.py;
            pWidth = sample.pWidth;
            pDepth = sample.pDepth;
        } else {
            px = 0;
            py = 0;
            pWidth = 1;
            pDepth = 1;
        }
    }

    public boolean overlaps(Plot target) {
        if (target == null) return false;
        boolean disjoint = (px >= target.px + target.pWidth) ||
                           (px + pWidth <= target.px) ||
                           (py >= target.py + target.pDepth) ||
                           (py + pDepth <= target.py);
        return !disjoint;
    }

    public boolean encompasses(Plot target) {
        if (target == null) return false;
        return target.px >= px &&
               target.py >= py &&
               (target.px + target.pWidth) <= (px + pWidth) &&
               (target.py + target.pDepth) <= (py + pDepth);
    }

    public int getX() { return px; }
    public void setX(int x) { px = x; }

    public int getY() { return py; }
    public void setY(int y) { py = y; }

    public int getWidth() { return pWidth; }
    public void setWidth(int width) { pWidth = width; }

    public int getDepth() { return pDepth; }
    public void setDepth(int depth) { pDepth = depth; }

    @Override
    public String toString() {
        return px + "," + py + "," + pWidth + "," + pDepth;
    }
}
