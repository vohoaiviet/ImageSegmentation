import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.List;
import java.util.ArrayList;


public class P4C {


    /** Convert an image to a graph of Pixels with edges between
     *  north, south, east and west neighboring pixels.
     *  @param image the image to convert
     *  @param pd the distance object for pixels
     *  @return the graph that was created
     */
    static WGraph<Pixel> imageToGraph(BufferedImage image, PixelDistance pd) {

        WGraphP4<Pixel> graph = new WGraphP4<Pixel>();

        

        int y = image.getHeight();
        int x = image.getWidth();
        ArrayList<ArrayList<GVertex<Pixel>>> grid = new ArrayList<ArrayList<GVertex<Pixel>>>(); 

        int rgb;
        //make gird of pixels
        for (int i = 0; i < y; i++) {
            ArrayList<GVertex<Pixel>> row = new ArrayList<GVertex<Pixel>>();
            for (int j = 0; j < x; j++) {

                rgb = image.getRGB(j, i);
                Pixel pix = new Pixel(j, i, rgb);
                GVertex<Pixel> ver = new GVertex<>(pix, graph.nextID());
                row.add(ver);
            }
            grid.add(row);
        }



        System.out.println("vertices: " + graph.numVerts() + "\tedges: " + graph.numEdges());
    



        //add edges
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < x; j++) {
                GVertex<Pixel> curr = grid.get(i).get(j);

                //edge cases 
                if (i == 0 && j == 0) {
                    GVertex<Pixel> lower = grid.get(i+1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j+1);

                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                } else if (i == y-1 && j == x-1) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
    
                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                } else if (i == 0 && j == x-1) {
                    GVertex<Pixel> lower = grid.get(i+1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);

                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                } else if (i == y-1 && j == 0) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j+1);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                } else if (i == 0) {
                    

                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> right = grid.get(i).get(j+1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                } else if (j == 0) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> right = grid.get(i).get(j+1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                } else if (i == y-1) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> right = grid.get(i).get(j+1);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                } else if (j == x-1) {
                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));
                } else {
                    //general case

                    GVertex<Pixel> upper = grid.get(i-1).get(j);
                    GVertex<Pixel> left = grid.get(i).get(j-1);
                    GVertex<Pixel> right = grid.get(i).get(j+1);
                    GVertex<Pixel> lower = grid.get(i+1).get(j);

                    graph.addEdge(curr, upper, pd.distance(curr.data(),upper.data()));
                    graph.addEdge(curr, left, pd.distance(curr.data(),left.data()));
                    graph.addEdge(curr, right, pd.distance(curr.data(),right.data()));
                    graph.addEdge(curr, lower, pd.distance(curr.data(),lower.data()));

                }
            }
        }
    
        System.out.println("vertices: " + graph.numVerts() + "\tedges: " + graph.numEdges());

        //TODO: Distance<Pixel> confusion as second parameter
        return graph;
    }


    /** Return a list of edges in a minimum spanning forest by
     *  implementing Kruskal's algorithm using fast union/finds.
     *  @param g the graph to segment
     *  @param kvalue the value to use for k in the merge test
     *  @return a list of the edges in the minimum spanning forest
     */

    static List<WEdge<Pixel>> segmenter(WGraph<Pixel> g, double kvalue) {
        return null;
    }

    public static void main(String[] args) {

        final int gray = 0x202020;

        try {
          // the line that reads the image file

            BufferedImage image = ImageIO.read(new File(args[0]));
            WGraph<Pixel> g = imageToGraph(image, new PixelDistance());

            




            /*
            List<WEdge<Pixel>> res = segmenter(g, Double.parseDouble(args[1]));

            System.out.print("result =  " + res.size() + "\n");
            System.out.print("NSegments =  "
                             + (g.numVerts() - res.size()) + "\n");

            // make a background image to put a segment into
            for (int i = 0; i < image.getHeight(); i++) {
                for (int j = 0; j < image.getWidth(); j++) {
                    image.setRGB(j, i, gray);
                }
            }

            // After you have a spanning tree connected component x, 
            // you can generate an output image like this:
            List<GVertex<Pixel>> x = null;
            for (GVertex<Pixel> i: x)  {
                Pixel d = i.data();
                image.setRGB(d.col(), d.row(), d.value());
            }

            File f = new File("output.png");
            ImageIO.write(image, "png", f);

            // You'll need to do that for each connected component,
            // writing each one to a different file, clearing the
            // image buffer first
   */
        } catch (IOException e) {
            System.out.print("Missing File!\n");

            // log the exception
            // re-throw if desired
        }
     



    }

}
